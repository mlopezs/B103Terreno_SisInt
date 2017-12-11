package b1_03.utilidades;

import b1_03.objetos.Accion;
import b1_03.objetos.FronteraCola;
import b1_03.objetos.Nodo;
import b1_03.objetos.Comunicador;
import b1_03.objetos.SubAccion;
import b1_03.objetos.Terreno;
import static b1_03.utilidades.GestorAcciones.generarAcciones;
import static b1_03.utilidades.Miscelanea.copiarMatrices;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Resolucion extends Thread {

    private final Terreno tInicial;
    private final int tipoAlgoritmo;
    //Cantidad objetivo
    private final int k;
    //Numero de filas
    private final int fs;
    //Numero de columnas
    private final int cs;
    //Maximo por casilla
    private final int max;
    //Profundidad maxima
    private final int profundidadMax;
    //Incremento de profundidad
    private final int incProf;
    //Comunicador entre hilos
    private final Comunicador com;
    //Indica si se requiere el algoritmo de profundidad iterativa
    private final boolean profundidadIterativa;

    public Resolucion(Terreno tInicial, int tipoAlgoritmo, int k, int fs, int cs, int max, int profundidadMax, int incProf, Comunicador persistencia, boolean profundidadIterativa) {
        this.tInicial = tInicial;
        this.tipoAlgoritmo = tipoAlgoritmo;
        this.k = k;
        this.fs = fs;
        this.cs = cs;
        this.max = max;
        this.profundidadMax = profundidadMax;
        this.incProf = incProf;
        this.com = persistencia;
        this.profundidadIterativa = profundidadIterativa;

    }

    /**
     * algoritmoDeBusqueda(..) pone en marcha el algoritmo de búsqueda. El tipo
     * de búsqueda vendrá dado por el parámetro tipoAlgoritmo, que influirá en
     * la valoración del nodo.
     *
     * @throws NoSuchAlgorithmException
     */
    public void algoritmoDeBusqueda() throws NoSuchAlgorithmException {// Algoritmo de busqueda de soluciones

        // Tabla hash md5(Terreno) - Terreno
        HashMap<String, Terreno> ht = new HashMap<>();
        ht.put(tInicial.toHash(), tInicial);

        // Terrenos auxiliares
        Terreno terrActual;
        Terreno terrAux;

        // Lista de sucesores para terrActual y su iterador
        LinkedList<Accion> sucesores;
        Iterator<Accion> it;

        // Frontera
        FronteraCola frontera = new FronteraCola();

        // Inserta nodo raiz en la frontera
        frontera.insertar(new Nodo(tInicial.toHash(), 0, null, "", 0, 0, 0));

        // Nodo actual
        Nodo nodoActual = null;

        // Solución
        boolean sol = false;

        // Algoritmo de búsqueda
        while (!sol && !frontera.esVacia() && !Thread.currentThread().isInterrupted()) {

            // Saca un nodo de la frontera
            nodoActual = frontera.eliminar();

            // Comprueba si es un nodo objetivo
            if (recuperarTerreno(ht, nodoActual.getEstado()).esObjetivo()) { // Si es objetivo

                sol = true;

            } else { // Si no es objetivo

                // Si se llega a la profundidad máxima sin tener aún solución, es que no lo hay
                if (nodoActual.getProfundidad() >= profundidadMax) {
                    com.setSolucion("No se ha encontrado la solución.");

                }

                // Se recupera el terreno al que representa el nodo
                terrActual = recuperarTerreno(ht, nodoActual.getEstado());

                // Se generan los sucesores para el terreno recuperado
                sucesores = generarAcciones(terrActual, k, fs, cs, max);

                // Se itera sobre los sucesores
                it = sucesores.iterator();

                while (it.hasNext() && !Thread.currentThread().isInterrupted()) {

                    // Se copia el terreno actual en terrCpy
                    Terreno terrCpy = new Terreno(terrActual.getTerr(), terrActual.getColumnaT(), terrActual.getFilaT(), k);

                    // Se selecciona la siguiente acción del iterador.
                    Accion accionActual = it.next();

                    // Se crea un terreno a partir de la acción anterior, y la copia del terreno actual
                    terrAux = crearTerrenoAPartirDeUnaAccion(accionActual, terrCpy, k);

                    // Se comprueba que el terreno exista o no en la hashtable
                    if (!ht.containsKey(terrAux.toHash())) { // Si no existe

                        // Se introduce un hash y su terreno en la tabla hash.
                        ht.put(terrAux.toHash(), terrAux);

                        // Se llama al método insertarNuevoNodo()
                        insertarNuevoNodo(terrAux, nodoActual, accionActual, profundidadMax, tipoAlgoritmo, frontera);

                    } else { // Si existe (no se mete)

                        // Comprueba que el nodo de la frontera tenga mayor valoración.
                        if (terrAux.getnCasillasNoObjetivo() < recuperarTerreno(ht, terrAux.toHash()).getnCasillasNoObjetivo()) {

                            // Se llama al método insertarNuevoNodo()
                            insertarNuevoNodo(terrAux, nodoActual, accionActual, profundidadMax, tipoAlgoritmo, frontera);

                        }
                    }
                }

            }
        }
        
        //Comprobamos si el hilo está interrumpido para limpiar el hashmap
        //y la frontera
        if (Thread.currentThread().isInterrupted()) {
            ht = new HashMap<>();
            frontera = new FronteraCola();
        } else {
            
            // Se comprueba si el flag de la solución está activado
            if (sol) { // Si está activado (hay solución)

                // Retornamos la solución tras transformarla en String
                crearSolucion(nodoActual, ht);

            } else { // Si es false (No hay solución)

                // Retornamos la siguiente cadena
                com.setSolucion("No se ha encontrado la solución.");

            }
        }

    }

    /**
     * Este método pone en marcha el algoritmo de profundidad iterativa.
     *
     * @throws NoSuchAlgorithmException
     */
    public void algoritmoProfundidadIterativa() throws NoSuchAlgorithmException {
        int profActual = incProf;

        while (com.getSolucion() == null && profActual <= profundidadMax) {
            algoritmoDeBusqueda();
            profActual += incProf;
        }

    }

    /**
     * Dada la tabla hash y una clave hash, extrae el objeto Terreno
     * correspondiente a dicha clave de la tabla hash. Si no existe, devuelve
     * null.
     *
     * @param ht
     * @param hash
     * @return
     */
    public Terreno recuperarTerreno(HashMap<String, Terreno> ht, String hash) {
        Terreno t = null;
        if (ht.containsKey(hash) == true) {
            t = ht.get(hash);
        }
        return t;
    }

    /**
     * crearSolucion(..) toma un nodo (que mete en la pila), y desde este
     * obtiene a su padre sucesivamente mientras los almacena en una pila, hasta
     * llegar al nodo raíz. Tras esto, los va sacando de la pila y colocando en
     * el String solucion la información necesaria de cada nodo. De esta manera
     * guardamos en un string (que se devuelve) todos los pasos en orden desde
     * el nodo raíz hasta el nodo con estado objetivo.
     *
     * @param n
     * @param ht
     */
    public void crearSolucion(Nodo n, HashMap<String, Terreno> ht) {

        String solucion = "Solución completa con todas las acciones:";
        String secuencia = "";
        Stack<Nodo> st = new Stack<>();
        Nodo nodo_aux = n;
        st.push(nodo_aux);

        while (nodo_aux.getPadre() != null) {
            nodo_aux = nodo_aux.getPadre();
            st.push(nodo_aux);
        }

        Nodo aux;
        while (!st.isEmpty()) {
            aux = st.pop();
            secuencia = secuencia + ("Acción: " + aux.getAccion());
            secuencia = secuencia + ("\r\n\r\nNodo solución -> " + aux.toString());
            secuencia = secuencia + ("\r\nTerreno -> " + recuperarTerreno(ht, aux.getEstado()));
            secuencia = secuencia + ("\r\n- - - - - - - - - - - - - - - - - - - "
                    + "- - - - - - - - - - - -\r\n\r\n");
            
            solucion = solucion + aux.getAccion() + "\r\n";

        }
        com.setSecuencia(secuencia);
        com.setSolucion(solucion);

    }

    /**
     * valorarNodo(..) se encarga de valorar el nodo según el tipo de algoritmo
     * que se está utilizando.
     *
     * @param tipoAlgoritmo
     * @param nodo
     * @param profundidadMax
     */
    public void valorarNodo(int tipoAlgoritmo, Nodo nodo, int profundidadMax) {
        switch (tipoAlgoritmo) {
            case 0: // Anchura
                nodo.setValoracion(nodo.getProfundidad());
                break;
            case 1: // Profundidad
                nodo.setValoracion(profundidadMax - nodo.getProfundidad());
                break;
            case 2: // Costo Uniforme
                nodo.setValoracion(nodo.getCosto());
                break;
            case 3: // A*
                nodo.setValoracion(nodo.getCosto() + nodo.getHeuristica());
                break;
            case 4: // Voraz
                nodo.setValoracion(nodo.getHeuristica());
                break;
        }
    }

    /**
     * crearTerrenoAPartirDeUnaAccion(..) recibe un terreno al que le aplica una
     * accion para generar un nuevo terreno, que devuelve.
     *
     * @param ac
     * @param original
     * @param k
     * @return
     */
    public Terreno crearTerrenoAPartirDeUnaAccion(Accion ac, Terreno original, int k) {
        int[][] t = copiarMatrices(original.getTerr());
        SubAccion[] sac = ac.getNodos();
        for (SubAccion sac1 : sac) {
            t[sac1.getPosx()][sac1.getPosy()] += sac1.getCantidad();
            t[original.getColumnaT()][original.getFilaT()] -= sac1.getCantidad();
        }
        return new Terreno(t, ac.getXt(), ac.getYt(), k);
    }

    /**
     * insertarNuevoNodo(..) crea un nuevo nodo partiendo de algunos de los
     * parámetros, lo valora según el tipo de búsqueda, y lo introduce en la
     * forontera.
     *
     * @param t
     * @param n
     * @param a
     * @param profMax
     * @param tipo
     * @param front
     * @throws NoSuchAlgorithmException
     */
    public void insertarNuevoNodo(Terreno t, Nodo n, Accion a, int profMax,
            int tipo, FronteraCola front) throws NoSuchAlgorithmException {

        // Se crea un nodo con el terreno generado teniendo en cuenta el nodo anterior (su padre)
        Nodo paraAgregarEnFrontera = new Nodo(t.toHash(), n.getProfundidad() + 1, n,
                a.toString(), a.getCosto() + n.getCosto(), 0, t.getnCasillasNoObjetivo());

        // Se valora el nodo según el tipo de algoritmo de búsqueda
        valorarNodo(tipo, paraAgregarEnFrontera, profMax);

        // Se inserta el nuevo nodo en la frontera
        front.insertar(paraAgregarEnFrontera);
    }

    @Override
    public void run() {
        if (profundidadIterativa) {
            try {
                algoritmoProfundidadIterativa();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Resolucion.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                algoritmoDeBusqueda();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Resolucion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
