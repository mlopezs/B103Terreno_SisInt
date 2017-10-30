package b1_03.objetos;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public interface Frontera {
    public void crearFrontera();
    public void insertar(Nodo b);
    public Object eliminar(Nodo b);
    public boolean esVacia();
}
