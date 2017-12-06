package b1_03.utilidades;

import b1_03.objetos.Accion;
import b1_03.objetos.FronteraCola;
import b1_03.objetos.Nodo;
import b1_03.objetos.SubAccion;
import b1_03.objetos.Terreno;
import static b1_03.utilidades.GestorAcciones.generarAcciones;
import static b1_03.utilidades.Miscelanea.copiarMatrices;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Resolucion {

    /**
     * algoritmoDeBusqueda(..) pone en marcha el algoritmo de búsqueda. El tipo de
     * búsqueda vendrá dado por el parámetro tipoAlgoritmo, que influirá en la 
     * valoración del nodo.
     *
     * @param tInicial
     * @param tipoAlgoritmo
     * @param k
     * @param fs
     * @param cs
     * @param max
     * @param profundidadMax
     * @param salida
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String algoritmoDeBusqueda(Terreno tInicial, int tipoAlgoritmo, int k, int fs, int cs, int max, int profundidadMax, javax.swing.JTextArea salida) throws NoSuchAlgorithmException {// Algoritmo de busqueda de soluciones

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
        while (!sol && !frontera.esVacia()) {

            // Saca un nodo de la frontera
            nodoActual = frontera.eliminar();

            // Comprueba si es un nodo objetivo
            if (recuperarTerreno(ht, nodoActual.getEstado()).esObjetivo()) { // Si es objetivo

                sol = true;

            } else { // Si no es objetivo

                // Si se llega a la profundidad máxima sin tener aún solución, es que no lo hay
                if (nodoActual.getProfundidad() >= profundidadMax) {
                    return "No se ha encontrado la solución.";
                }

                // Se recupera el terreno al que representa el nodo
                terrActual = recuperarTerreno(ht, nodoActual.getEstado());

                // Se generan los sucesores para el terreno recuperado
                sucesores = generarAcciones(terrActual, k, fs, cs, max);

                // Se itera sobre los sucesores
                it = sucesores.iterator();

                while (it.hasNext()) {

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

                        // Se comprueba si el nodo tiene padre o es raíz
                        /*if (nodoActual.getPadre() != null) { // Si tiene padre
                            
                            // Se crea un nodo con el terreno generado teniendo en cuenta el nodo anterior (su padre)
                            Nodo paraAgregarEnFrontera = new Nodo(terrAux.toHash(), nodoActual.getProfundidad() + 1, nodoActual, 
                                    accionActual.toString(), accionActual.getCosto() + nodoActual.getCosto(), 0, terrAux.getnCasillasNoObjetivo());
                            
                            // Se valora el nodo según el tipo de algoritmo de búsqueda
                            valorarNodo(tipoAlgoritmo, paraAgregarEnFrontera, profundidadMax);
                            
                            // Se inserta el nuevo nodo en la frontera
                            frontera.insertar(paraAgregarEnFrontera);
                        
                        } else { // Si es raíz
                            
                            
                            Nodo paraAgregarEnFrontera = new Nodo(terrAux.toHash(), nodoActual.getProfundidad() + 1, nodoActual, 
                                    accionActual.toString(), nodoActual.getCosto() + accionActual.getCosto(), 0, terrAux.getnCasillasNoObjetivo());

                            // Se valora el nodo según el tipo de algoritmo de búsqueda
                            valorarNodo(tipoAlgoritmo, paraAgregarEnFrontera, profundidadMax);
                            
                            // Se inserta el nuevo nodo en la frontera
                            frontera.insertar(paraAgregarEnFrontera);
                            
                        }*/
                        
                        // Se crea un nodo con el terreno generado teniendo en cuenta el nodo anterior (su padre)
                        Nodo paraAgregarEnFrontera = new Nodo(terrAux.toHash(), nodoActual.getProfundidad() + 1, nodoActual,
                                accionActual.toString(), accionActual.getCosto() + nodoActual.getCosto(), 0, terrAux.getnCasillasNoObjetivo());

                        // Se valora el nodo según el tipo de algoritmo de búsqueda
                        valorarNodo(tipoAlgoritmo, paraAgregarEnFrontera, profundidadMax);

                        // Se inserta el nuevo nodo en la frontera
                        frontera.insertar(paraAgregarEnFrontera);

                    } else { // Si existe, no se mete

                        if (terrAux.getnCasillasNoObjetivo() < recuperarTerreno(ht, terrAux.toHash()).getnCasillasNoObjetivo()) {

                            // Se comprueba si el nodo tiene padre o es raíz
                            if (nodoActual.getPadre() != null) { // Si tiene padre
                                Nodo paraAgregarEnFrontera = new Nodo(terrAux.toHash(), nodoActual.getProfundidad() + 1, nodoActual,
                                        accionActual.toString(), accionActual.getCosto() + nodoActual.getCosto(), 0, terrAux.getnCasillasNoObjetivo());
                                valorarNodo(tipoAlgoritmo, paraAgregarEnFrontera, profundidadMax);
                                frontera.insertar(paraAgregarEnFrontera);
                            } else { // Si es raíz
                                Nodo paraAgregarEnFrontera = new Nodo(terrAux.toHash(), nodoActual.getProfundidad() + 1, nodoActual,
                                        accionActual.toString(), nodoActual.getCosto() + accionActual.getCosto(), 0, terrAux.getnCasillasNoObjetivo());
                                valorarNodo(tipoAlgoritmo, paraAgregarEnFrontera, profundidadMax);
                                frontera.insertar(paraAgregarEnFrontera);
                            }
                        }
                    }
                }
            }
        }

        if (sol) {
            return crearSolucion(nodoActual, ht, salida);
        } else {
            return "No se ha encontrado la solución.";
        }

    }

    /**
     * Este método pone en marcha el algoritmo de profundidad iterativa.
     *
     * @param t
     * @param tipoAlgoritmo
     * @param profMax
     * @param incProf
     * @param k
     * @param fs
     * @param cs
     * @param max
     * @param salida
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String algoritmoProfundidadIterativa(Terreno t, int tipoAlgoritmo, int profMax, int incProf, int k, int fs, int cs, int max, javax.swing.JTextArea salida) throws NoSuchAlgorithmException {
        int profActual = incProf;
        String solucion = null;
        while (solucion == null && profActual <= profMax) {
            solucion = algoritmoDeBusqueda(t, tipoAlgoritmo, k, fs, cs, max, profMax, salida);
            profActual += incProf;
        }
        return solucion;
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
    public static Terreno recuperarTerreno(HashMap<String, Terreno> ht, String hash) {
        Terreno t = null;
        if (ht.containsKey(hash) == true) {
            t = ht.get(hash);
        }
        return t;
    }

    /**
     * crearSolucion(..) toma un nodo (que mete en la pila), y desde este obtiene
     * a su padre sucesivamente mientras los almacena en una pila, hasta llegar al 
     * nodo raíz.
     * Tras esto, los va sacando de la pila y colocando en el String solucion la
     * información necesaria de cada nodo.
     * De esta manera guardamos en un string (que se devuelve) todos los pasos en
     * orden desde el nodo raíz hasta el nodo con estado objetivo.
     * 
     * @param n
     * @param ht
     * @param salida
     * @return
     */
    public static String crearSolucion(Nodo n, HashMap<String, Terreno> ht, 
            javax.swing.JTextArea salida) {
        
        String solucion = "Solución completa con todas las acciones:";
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
            salida.append("Nodo solución -> " + aux.toString() + "\r\n");
            salida.append("Terreno -> " + recuperarTerreno(ht, aux.getEstado()) 
                    + "\r\n" + aux.getAccion() + "\r\n\r\n\r\n");
            solucion = solucion + aux.getAccion() + "\r\n";

        }
        return solucion;
    }

    /**
     * valorarNodo(..) se encarga de valorar el nodo según el tipo de algoritmo 
     * que se está utilizando.
     *
     * @param tipoAlgoritmo
     * @param nodo
     * @param profundidadMax
     */
    public static void valorarNodo(int tipoAlgoritmo, Nodo nodo, int profundidadMax) {
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
    public static Terreno crearTerrenoAPartirDeUnaAccion(Accion ac, Terreno original, int k) {
        int[][] t = copiarMatrices(original.getTerr());
        SubAccion[] sac = ac.getNodos();
        for (SubAccion sac1 : sac) {
            t[sac1.getPosx()][sac1.getPosy()] += sac1.getCantidad();
            t[original.getColumnaT()][original.getFilaT()] -= sac1.getCantidad();
        }
        return new Terreno(t, ac.getXt(), ac.getYt(), k);
    }
    
}
