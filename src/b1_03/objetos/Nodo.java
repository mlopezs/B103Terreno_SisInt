package b1_03.objetos;

import java.util.Comparator;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Nodo implements Comparator<Nodo> {
    
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
    public int compare(Nodo o1, Nodo o2) {
       
        int retorno = 0;
        
        if(o2.getId()<o1.getId()){
            retorno = 1;
        }else if(o1.getId()<o2.getId()){
            retorno = -1;
        }
        
        return retorno;
    }
}
