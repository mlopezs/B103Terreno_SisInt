/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.utilidades;

import b1_03.objetos.FronteraCola;
import b1_03.objetos.Nodo;
import b1_03.objetos.Terreno;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 *
 * @author Alfonso
 */
public class Resolucion {

    private HashMap<String, Terreno> ht = new HashMap<>();

    public String algoritmoDeBusqueda(Terreno tInicial) throws NoSuchAlgorithmException {// Algoritmo de busqueda de soluciones
        //Inicialización
        FronteraCola frontera = new FronteraCola();
        frontera.crearFrontera();
        Nodo inicial = new Nodo(tInicial.toHash(), 0, null, "", 0);
        frontera.insertar(inicial);
        boolean sol = false;
        Nodo actual;

        // Bucle de busqueda
        while (!sol && !frontera.esVacia()) {
            actual = new Nodo(frontera.eliminar());
            if (estadoObjetivo()) {
                sol = true;
            } else {
                //Lista de sucesores, guardar los sucesores de alguna manera
                //Crear una lista de nodos del arbol, con todos los sucesores poniendo de padre al nodo actual
                //Añadir sucesores a la frontera
            }
        }

        if (sol) {
            return crearSolucion();
        } else {
            return "No solucion";
        }

    }

    public boolean estadoObjetivo(String hash, int k) { //Comprobacion de que estamos en estado objetivo

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

    public String crearSolucion() {
        String solucion = "";
        /* Aquí a partir del nodo se haria un bucle sacando su padre, y 
        almacenado la acción en una pila, y vertiendola luego en el String para 
        que saliera la secuencia desde el nodo inicial hasta el nodo con el 
        estado objetivo*/
        return solucion;
    }

}
