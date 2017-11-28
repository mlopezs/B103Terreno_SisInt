/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.utilidades;

import b1_03.objetos.Accion;
import b1_03.objetos.FronteraCola;
import b1_03.objetos.Nodo;
import b1_03.objetos.SubAccion;
import b1_03.objetos.Terreno;
import static b1_03.utilidades.GestorAcciones.generarAcciones;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Alfonso
 */
public class Resolucion {

    public static String algoritmoDeBusqueda(Terreno tInicial, int tipoAlgoritmo, int k, int fs, int cs, int max, int profundidadMax) throws NoSuchAlgorithmException {// Algoritmo de busqueda de soluciones
        //Inicialización
        HashMap<String, Terreno> ht = new HashMap<>();
        ht.put(tInicial.toHash(), tInicial);
        Terreno temporal = null;
        GestorAcciones ga;
        LinkedList<Accion> sucesores;// = new LinkedList<>();
        Iterator<Accion> it;
        Terreno impostor;

        FronteraCola frontera = new FronteraCola();
        Nodo inicial = new Nodo(tInicial.toHash(), 0, null, "", 0, 0, 0);
        frontera.insertar(inicial);
        boolean sol = false;
        Nodo actual = null;
        Nodo nodoAuxiliar;

        // Bucle de busqueda
        while (!sol && !frontera.esVacia()) {
            actual = (frontera.eliminar());
            /*if (actual.getProfundidad() >= profundidadMax)
                return "no solucion";*/
            if (estadoObjetivo(ht, actual.getEstado(), k)) {
                sol = true;
                break;
            } else {

                temporal = recuperarTerreno(ht, actual.getEstado());
                sucesores = generarAcciones(temporal, k, fs, cs, max);
                it = sucesores.iterator();

                while (it.hasNext()) {
                    Terreno aux = null;
                    aux = new Terreno(temporal.getTerr(), temporal.getColumnaT(), temporal.getFilaT());
                    Accion accionActual = it.next();
                    impostor = crearTerrenoAPartirDeUnaAccion(accionActual, aux);
                    

                    if (!ht.containsKey(impostor.toHash())) {
                        ht.put(impostor.toHash(), impostor);
                        
                        if(actual.getPadre()!=null){
                            Nodo paraAgregarEnFrontera = new Nodo(impostor.toHash(), actual.getProfundidad() + 1, actual, accionActual.toString(), actual.getCosto() + accionActual.getCosto() + actual.getPadre().getCosto(), 0, 0);
                            valorarNodo(tipoAlgoritmo, paraAgregarEnFrontera, profundidadMax);
                            System.out.println("Nodo -> "+actual.toString());
                            frontera.insertar(paraAgregarEnFrontera);
                        }else{
                            Nodo paraAgregarEnFrontera = new Nodo(impostor.toHash(), actual.getProfundidad() + 1, actual, accionActual.toString(), actual.getCosto()+ accionActual.getCosto(), 0, 0);
                            valorarNodo(tipoAlgoritmo, paraAgregarEnFrontera, profundidadMax);
                            frontera.insertar(paraAgregarEnFrontera);
                            System.out.println("Nodo -> "+actual.toString());
                        }
                    
                        
                    }
                }
            }
        }

        if (sol) {
            return crearSolucion(actual,ht);
        } else {
            return "No solucion";
        }

    }

    public static boolean estadoObjetivo(HashMap<String, Terreno> ht, String hash, int k) { //Comprobacion de que estamos en estado objetivo

        /*Lo que vendría a hacer esta función, es recuperar el terreno como tal
        a partir del hash y la tabla hash, entonces le aplicas al terreno la
        función que trae el mismo, para saber si es objetivo*/
        boolean objetivo = false;
        Terreno t;
        if (ht.containsKey(hash) == true) {
            t = ht.get(hash);
            objetivo = t.esObjetivo(k);
        }

        return objetivo;
    }

    public static Terreno recuperarTerreno(HashMap<String, Terreno> ht, String hash) {
        Terreno t = null;

        if (ht.containsKey(hash) == true) {
            t = ht.get(hash);
        }

        return t;
    }

    public static String crearSolucion(Nodo n, HashMap<String, Terreno> ht) {

        /* Aquí a partir del nodo se haria un bucle sacando su padre, y
        almacenado la acción en una pila, y vertiendola luego en el String para
        que saliera la secuencia desde el nodo inicial hasta el nodo con el
        estado objetivo*/
        String solucion = "Solución completa con todas las acciones:";
        Stack<Nodo> st = new Stack<>();
        Nodo nodo_aux = n;
        st.push(nodo_aux);
        Nodo aux;
        
        while (nodo_aux.getPadre() != null) {
            nodo_aux = nodo_aux.getPadre();
            st.push(nodo_aux);
        }

        while (!st.isEmpty()) {
            aux = st.pop();
            System.out.println("Terreno -> "+recuperarTerreno(ht, aux.getEstado())+"\n"+aux.getAccion());
            solucion = solucion + aux.getAccion()+ "\r\n";
            
        
        }
        return solucion;
    }

    public static void valorarNodo(int tipoAlgoritmo, Nodo nodo, int profundidadMax) {

        switch (tipoAlgoritmo) {
            case 0: // Anchura
                nodo.setValoracion(nodo.getProfundidad());

            case 1: // Profundidad
                nodo.setValoracion(profundidadMax - nodo.getProfundidad());

            case 2: // Costo Uniforme
                nodo.setValoracion(nodo.getCosto());
            case 3: // A*
                //nodo.setValoracion();
            case 4: // Voraz
                //nodo.setValoracion();
        }
    }

    public static Terreno crearTerrenoAPartirDeUnaAccion(Accion ac, Terreno original) {

        int[][] t = Miscelanea.copiarMatrices(original.getTerr());
        SubAccion[] sac = ac.getNodos();

        for (int i = 0; i < sac.length; i++) {
            t[sac[i].getPosx()][sac[i].getPosy()] += sac[i].getCantidad();
            t[original.getColumnaT()][original.getFilaT()] -= sac[i].getCantidad();
        }
        Terreno nuevo = new Terreno(t, ac.getXt(), ac.getYt());
        return nuevo;
    }

    /* PODA -> Miramoes el estado del nodo
        Si no esta-> Se calcula la valoracion segun estrategia y se mete el nodo en la frontera
        Si esta
            Si la valoracion del que esta es <= (yo digo que el igual no pasa) que el del nuevo, no se añade el nuevo
            Si la valoracion del que esta es > que el del nuevo, se añade el nuevo*/

 /* HEURISTICA -> Nº de casillas diferentes al valor k

     */
}
