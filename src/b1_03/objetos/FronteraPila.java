package b1_03.objetos;

import java.util.Stack;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class FronteraPila implements Frontera {

    private Stack<Nodo> s;

    public FronteraPila() {
        crearFrontera();
    }

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
