package b1_03.objetos;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Nodo implements Comparable<Nodo> {
    
    private int id;
    private String estado; // Estado del nodo
    private int val; // Valor para ordenarlo en la frontera
    private int costo; // Coste de la accion
    private Nodo padre; // Nodo padre
    private String accion; // Accion que ha llevado al estado
    
    private static int iden = 0; // Autoincremento nodo

    public Nodo(String estado, int prof, int cost, Nodo padre, String accion, int costo) {
        this.id = Nodo.iden++;
        this.estado = estado;
        this.val = (int) Math.floor(Math.random()*1000);
        this.padre = padre;
        this.accion = accion;
        this.costo = costo;
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
                ", costo=" + costo + ", padre=" + padre + ", accion=" + accion + '}';
    }

    @Override
    public int compareTo(Nodo o) {
        int ret = 0;

        if (this.val < o.getVal()) {
            ret = 1;
        } else if (this.val < o.getVal()) {
            ret = -1;
        } else {
            /*if (this.id < o.getId()) {
                ret = 1;
            } else {
                ret = -1;
            }*/
            ret = 0;
        }

        return ret;
    }
}
