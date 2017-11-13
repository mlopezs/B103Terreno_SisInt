/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.utilidades;

import b1_03.objetos.Accion;
import b1_03.objetos.FronteraCola;
import b1_03.objetos.Nodo;
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

     

    public String algoritmoDeBusqueda(Terreno tInicial, int k, int fs, int cs, int max) throws NoSuchAlgorithmException {// Algoritmo de busqueda de soluciones
        //Inicialización
        HashMap<String, Terreno> ht = new HashMap<>();
        ht.put(tInicial.toHash(),tInicial);
        Terreno temporal = null;
        GestorAcciones ga;
        LinkedList<Accion> sucesores = null; 
        Iterator<Accion> it = sucesores.iterator();
        
        FronteraCola frontera = new FronteraCola();
        frontera.crearFrontera();
        Nodo inicial = new Nodo(tInicial.toHash(), 0, null, "", 0);
        frontera.insertar(inicial);
        boolean sol = false;
        Nodo actual = null;

        // Bucle de busqueda
        while (!sol && !frontera.esVacia()) {
            actual = new Nodo(frontera.eliminar());
            if (estadoObjetivo(ht, actual.getEstado(), k)) {
                sol = true;
            } else {
                temporal = recuperarTerreno(ht, actual.getEstado());
                sucesores = generarAcciones(temporal, k, fs, cs, max);
                
                while(it.hasNext()){
                    Terreno impostor = null;
                    Accion accionActual = it.next();
                    impostor = impostor.crearTerrenoAPartirDeUnaAccion(accionActual);
                    //Falta metodo para añadir hash a tabla hash, una vez esto ya estaria
                    Nodo paraAgregarEnFrontera = new Nodo(impostor.toHash(),actual.getProfundidad()+1, actual, accionActual.toString(), actual.getCosto());
                    frontera.insertar(paraAgregarEnFrontera);
                }
            }
        }

        if (sol) {
            return crearSolucion(actual);
        } else {
            return "No solucion";
        }

    }

    public boolean estadoObjetivo(HashMap<String, Terreno> ht,String hash, int k) { //Comprobacion de que estamos en estado objetivo

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
    
    public Terreno recuperarTerreno(HashMap<String, Terreno> ht,String hash){
        Terreno t = null;
        
        if(ht.containsKey(hash) == true){
        t = ht.get(hash);
        }
        
        return t;
    }

    public String crearSolucion(Nodo n) {
        
        /* Aquí a partir del nodo se haria un bucle sacando su padre, y 
        almacenado la acción en una pila, y vertiendola luego en el String para 
        que saliera la secuencia desde el nodo inicial hasta el nodo con el 
        estado objetivo*/
        String solucion="";
        Stack<Nodo> st=new Stack<>();
        Nodo nodo_aux=n;
        st.push(nodo_aux);
        
        while(nodo_aux.getPadre()!=null){
            nodo_aux=nodo_aux.getPadre();
            st.push(nodo_aux);   
        }
        
        while((nodo_aux=st.pop())!=null){
            solucion=solucion+nodo_aux.getAccion().toString()+"\r\n";
        }
        return solucion;
    }

}
