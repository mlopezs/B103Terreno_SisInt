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

    // Cola de prioridad donde almacenar nodos
    private PriorityQueue<Nodo> pq;     
    
    /**
     * Constructor.
     */
    public FronteraCola() {
        crearFrontera();
    }

    /**
     * Crea la forontera de tipo PriorityQueue con un tamaño para 100 nodos.
     */
    @Override
    public void crearFrontera() {
        this.pq = new PriorityQueue<>(100);
    }

    /**
     * Añade un objeto Nodo a la cola.
     * 
     * @param b
     */
    @Override
    public void insertar(Nodo b) {
        pq.add(b);
    }

    /**
     * Saca el primer nodo de la cola.
     * 
     * @return
     */
    @Override
    public Nodo eliminar() {
        return pq.remove();
    }

    /**
     * Comprueba si la cola está vacía.
     * 
     * @return
     */
    @Override
    public boolean esVacia() {
        return pq.isEmpty();
    }

}
