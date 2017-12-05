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
    
    /**
     *
     */
    public FronteraCola() {
        crearFrontera();
    }

    /**
     *
     */
    @Override
    public void crearFrontera() {
        this.pq= new PriorityQueue<>(100);
    }

    /**
     *
     * @param b
     */
    @Override
    public void insertar(Nodo b) {
        pq.add(b);
    }

    /**
     *
     * @return
     */
    @Override
    public Nodo eliminar() {
        return pq.remove();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean esVacia() {
        return pq.isEmpty();
    }

}
