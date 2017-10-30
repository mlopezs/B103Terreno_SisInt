/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.objetos;

import java.util.PriorityQueue;

/**
 *
 * @author alf
 */
public class FronteraCola implements Frontera {

    private PriorityQueue<Nodo> pq;

    public FronteraCola() {
        crearFrontera();
    }

    @Override
    public void crearFrontera() {
        pq = new PriorityQueue();
    }

    @Override
    public void insertar(Nodo b) {
        pq.add(b);
    }

    @Override
    public Nodo eliminar(Nodo b) {
        return pq.remove();
    }

    @Override
    public boolean esVacia() {
        return pq.isEmpty();
    }

}
