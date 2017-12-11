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

    private PriorityQueue<Nodo> pq;     
      
    public FronteraCola() {
        crearFrontera();
    }

    @Override
    public void crearFrontera() {
        this.pq = new PriorityQueue<>(100);
    }

    @Override
    public void insertar(Nodo b) {
        pq.add(b);
    }

    @Override
    public Nodo eliminar() {
        return pq.remove();
    }
    
    @Override
    public boolean esVacia() {
        return pq.isEmpty();
    }
}
