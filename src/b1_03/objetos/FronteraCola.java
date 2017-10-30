package b1_03.objetos;

import java.util.PriorityQueue;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class FronteraCola implements Frontera {

    private PriorityQueue<Nodo> pq; // Cola de prioridad donde almacenar nodos
    
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
