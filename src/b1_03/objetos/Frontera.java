package b1_03.objetos;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public interface Frontera {

    /**
     *
     */
    public void crearFrontera();

    /**
     *
     * @param b
     */
    public void insertar(Nodo b);

    /**
     *
     * @return
     */
    public Object eliminar();

    /**
     *
     * @return
     */
    public boolean esVacia();
}
