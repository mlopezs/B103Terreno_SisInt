package b1_03.objetos;

import java.util.LinkedList;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class FronteraLista implements Frontera{

    // Lista donde almacenar nodos
    private LinkedList<Nodo> l;

    /**
     * Constructor.
     */
    public FronteraLista(){
        crearFrontera();
    }
    
    /**
     * Crea la forontera de tipo LinkedList.
     */
    @Override
    public void crearFrontera() {
        l = new LinkedList();
    }

    /**
     * Añade un objeto Nodo a la lista.
     *
     * @param b
     */
    @Override
    public void insertar(Nodo b) {
        l.add(b);
    }

    /**
     * Saca el primer nodo de la lista.
     *
     * @return
     */
    @Override
    public Nodo eliminar() {
        return l.getFirst();
    }

    /**
     * Comprueba si la lista está vacía.
     *
     * @return
     */
    @Override
    public boolean esVacia() {
        return l.isEmpty();
    }  
    
}
