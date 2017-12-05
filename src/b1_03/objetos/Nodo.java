package b1_03.objetos;


/**
 *
 * @author pacog
 */
public class Nodo implements Comparable<Nodo>, Cloneable {
    private static int iden = 0; // Autoincremento nodo
    
    private final int id; // Id del nodo
    private final String estado; // Estado del nodo
    private int val; // Valor para ordenarlo en la frontera
    private final int costo; // Coste de la accion
    private final Nodo padre; // Nodo padre
    private final String accion; // Accion que ha llevado al estado
    private int profundidad;
    private final int heuristica;
    
    /**
     *
     * @param estado
     * @param prof
     * @param padre
     * @param accion
     * @param costo
     * @param val
     * @param heuristica
     */
    public Nodo(String estado, int prof, Nodo padre, String accion, int costo, int val, int heuristica) {
        this.id = iden++;
        this.estado = estado;
        this.val = val;
        this.padre = padre;
        this.accion = accion;
        this.costo = costo;
        this.profundidad = prof;
        this.heuristica = heuristica;
    }

    /**
     *
     * @return
     */
    public int getHeuristica() {
        return heuristica;
    }
    
    /**
     *
     * @return
     */
    public int getValoracion() {
        return val;
    }

    /**
     *
     * @param val
     */
    public void setValoracion(int val) {
        this.val = val;
    }
    
    /**
     *
     * @return
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     *
     * @param profundidad
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * @return
     */
    public int getVal() {
        return val;
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
    public Nodo getPadre() {
        return padre;
    }

    /**
     *
     * @return
     */
    public String getAccion() {
        return accion;
    }   

    @Override
    public String toString() {
        return "Nodo{" + "id=" + id + ", estado=" + estado + ", val=" + val + 
                ", costo=" + costo +", heuristica="+heuristica +", padre=" + padre + ", accion=" + accion + '}';
    }

    @Override
    public int compareTo(Nodo o) {
        int ret;
        if (this.val < o.getVal()) {
            ret = -1;
        } else if (this.val > o.getVal()) {
            ret = 1;
        } else {
            ret = 0;
        }
        return ret;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
   
}
