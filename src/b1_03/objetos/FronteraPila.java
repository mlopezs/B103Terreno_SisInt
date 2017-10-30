/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.objetos;

import java.util.Stack;

/**
 *
 * @author alf
 */
public class FronteraPila implements Frontera{
    
    private Stack<Nodo> s;

    @Override
    public void crearFrontera() {
        s = new Stack();
    }

    @Override
    public void insertar(Nodo b) {
        s.push(b);
    }

    @Override
    public Nodo eliminar(Nodo b) {
        return s.pop();
    }

    @Override
    public boolean esVacia() {
        return s.isEmpty();
    }    
    
}
