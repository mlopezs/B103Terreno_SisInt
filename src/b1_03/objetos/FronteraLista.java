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
    
    private LinkedList<Nodo> l;

    public FronteraLista(){
        crearFrontera();
    }
    
    @Override
    public void crearFrontera() {
        l = new LinkedList();
    }

    @Override
    public void insertar(Nodo b) {
        l.add(b);
    }

    @Override
    public Nodo eliminar(Nodo b) {
        return l.getFirst();
    }

    @Override
    public boolean esVacia() {
        return l.isEmpty();
    }
    
    
    
}
