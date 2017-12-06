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

    // Pila donde almacenar los nodos
    private Stack<Nodo> s;

    /**
     * Constructor.
     */
    public FronteraPila() {
        crearFrontera();
    }

    /**
     * Crea la forontera de tipo Stack.
     */
    @Override
    public void crearFrontera() {
        s = new Stack();
    }

    /**
     * Añade un objeto Nodo a la pila.
     *
     * @param b
     */
    @Override
    public void insertar(Nodo b) {
        s.push(b);
    }

    /**
     * Saca el primer (último) nodo de la pila.
     *
     * @return
     */
    @Override
    public Nodo eliminar() {
        return s.pop();
    }

    /**
     * Comprueba si la pila está vacía.
     *
     * @return
     */
    @Override
    public boolean esVacia() {
        return s.isEmpty();
    }

}
