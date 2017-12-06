package b1_03.objetos;

/**
 * Interfaz implementada por otras clases
 * 
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public interface Frontera {

    /**
     * Crea la frontera
     */
    public void crearFrontera();

    /**
     * Inserta elementos en la frontera
     *
     * @param b
     */
    public void insertar(Nodo b);

    /**
     * Elimina el elemento correspondiente de la forontera.
     * 
     * @return
     */
    public Object eliminar();

    /**
     * Comprueba si la frontera está vacía.
     *
     * @return
     */
    public boolean esVacia();
}
