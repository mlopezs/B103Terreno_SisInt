package b1_03.objetos;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class SubAccion {

    private final int cantidad; // Cantidad a mover
    private final int posx; // coord x donde mover
    private final int posy; // coord y donde mover

    /**
     * Constructor.
     *
     * @param cantidad
     * @param posx
     * @param posy
     */
    public SubAccion(int cantidad, int posx, int posy) {
        this.cantidad = cantidad;
        this.posx = posx;
        this.posy = posy;
    }

    /**
     *
     * @return
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     *
     * @return
     */
    public int getPosx() {
        return posx;
    }

    /**
     *
     * @return
     */
    public int getPosy() {
        return posy;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "(" + cantidad + "(" + posx + "," + posy + "))";
    }

}
