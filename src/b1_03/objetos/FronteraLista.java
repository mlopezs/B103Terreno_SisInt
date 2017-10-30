/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.objetos;
import java.util.LinkedList;

/**
 *
 * @author alf
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
