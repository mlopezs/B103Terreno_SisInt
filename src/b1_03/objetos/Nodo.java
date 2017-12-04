package b1_03.objetos;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Nodo implements Comparable<Nodo>, Cloneable {
    
    private int id; // Id del nodo
    private String estado; // Estado del nodo
    private int val; // Valor para ordenarlo en la frontera
    private int costo; // Coste de la accion
    private Nodo padre; // Nodo padre
    private String accion; // Accion que ha llevado al estado
    private int profundidad;
    private int heuristica;
    
    private static int iden = 0; // Autoincremento nodo

    public Nodo(String estado, int prof, Nodo padre, String accion, int costo, int val, int heuristica) {
        this.id = Nodo.iden++;
        this.estado = estado;
        this.val = val;
        this.padre = padre;
        this.accion = accion;
        this.costo = costo;
        this.profundidad = prof;
        this.heuristica = heuristica;
    }

    public int getHeuristica() {
        return heuristica;
    }
    
    public int getValoracion() {
        return val;
    }

    public void setValoracion(int val) {
        this.val = val;
    }
    
    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public int getVal() {
        return val;
    }

    public int getCosto() {
        return costo;
    }

    public Nodo getPadre() {
        return padre;
    }

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
