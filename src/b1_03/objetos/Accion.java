package b1_03.objetos;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Accion {

    private final int xt; // x del tractor
    private final int yt; // y del tractor
    private final int costo; // coste de la accion
    private final SubAccion[] nodos; // Vector de sub-acciones

    /**
     * 
     * @param xt
     * @param yt
     * @param costo
     * @param nodos
     */
    public Accion(int xt, int yt, int costo, SubAccion[] nodos) {
        this.xt = xt;
        this.yt = yt;
        this.costo = costo;
        this.nodos = nodos;
    }

    /**
     *
     * @return 
     */
    @Override
    public String toString() {
        String s = "((" + yt + "," + xt + "), [";
        for (SubAccion a : nodos) {
            if (a != null) {
                s += a.toString() + ", ";
            }
        }
        s = s.substring(0, s.length() - 2);
        s += "], " + costo + ")";
        return s;
    }

    /**
     * 
     * @return
     */
    public int getXt() {
        return xt;
    }

    /**
     *
     * @return
     */
    public int getYt() {
        return yt;
    }

    /**
     *
     * @return
     */
    public int getCosto() {
        return costo;
    }

    /**
     *
     * @return
     */
    public SubAccion[] getNodos() {
        return nodos;
    }
    
}