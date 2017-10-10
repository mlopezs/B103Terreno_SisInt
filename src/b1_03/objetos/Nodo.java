package b1_03.objetos;

/**
 *
 * @author mlspo
 */
public class Nodo {
    
    private Terreno estado;
    private int id;
    private int val; // Valor para ordenarlo en la frontera
    private int prof; // Profundidad
    private int cost; // Coste
    private Nodo padre;
    
    private static int iden = 0; // Autoincremento nodo

    public Nodo(Terreno estado, int val, int prof, int cost, Nodo padre) {
        this.id = Nodo.iden++;
        this.estado = estado;
        this.val = val;
        this.prof = prof;
        this.cost = cost;
        this.padre = padre;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Terreno getEstado() {
        return estado;
    }

    public void setEstado(Terreno estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getProf() {
        return prof;
    }

    public void setProf(int prof) {
        this.prof = prof;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    @Override
    public String toString() {
        return "NODO:\n-----\n" + estado.toString() + "\n{estado=" + estado + ", id=" + 
                id + ", val=" + val + ", prof=" + prof + ", cost=" + cost + '}';
    }
    
    
}
