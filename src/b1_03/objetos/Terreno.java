package b1_03.objetos;

import static b1_03.utilidades.Hash.md5;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Terreno implements Cloneable {

    private int[][] terr; // Cantidad de arena en cada celda
    private int columnat; // Posición en el eje x del tractor
    private int filat; // Posición en el eje y del tractor
    private final int nCasillasNoObjetivo; // Casillas que aún no son objetivo

    /**
     * Constructor.
     *
     * @param terr
     * @param xt
     * @param yt
     * @param k
     */
    public Terreno(int[][] terr, int xt, int yt, int k) {
        this.terr = terr;
        this.columnat = xt;
        this.filat = yt;
        this.nCasillasNoObjetivo = contarCasillasNoObjetivo(this.terr, k);
    }

    /**
     *
     * @return
     */
    public int getnCasillasNoObjetivo() {
        return nCasillasNoObjetivo;
    }

    /**
     *
     * @return
     */
    public int[][] getTerr() {
        return this.terr;
    }

    /**
     *
     * @param terr
     */
    public void setTerr(int[][] terr) {
        this.terr = terr;
    }

    /**
     *
     * @return
     */
    public int getColumnaT() {
        return columnat;
    }

    /**
     *
     * @param xt
     */
    public void setColumnaT(int xt) {
        this.columnat = xt;
    }

    /**
     *
     * @return
     */
    public int getFilaT() {
        return filat;
    }

    /**
     *
     * @param yt
     */
    public void setFilaT(int yt) {
        this.filat = yt;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString()  {
        
        String salida = "(" + columnat + "," + filat + ")\n";
        
        for (int[] terr1 : terr) {
            for (int j = 0; j < terr[0].length; j++) {
                salida += (terr1[j] + " ");
            }
            salida += "\n";
        }
        
        return salida;
    }

    /**
     * contarCasillasNoObjetivo(..) cuenta el número de casillas que quedan para 
     * que la arena del terreno esté bien distribuida.
     *
     * @param terreno
     * @param k
     * @return
     */
    public int contarCasillasNoObjetivo(int[][] terreno, int k){
        int nCasillasNoObjetivo = 0;        
        for (int[] terreno1 : terreno) {
            for (int j = 0; j < terreno1.length; j++) {
                if (terreno1[j] != k) {
                    nCasillasNoObjetivo++;
                }
            }
        }
        return nCasillasNoObjetivo;
    }
    
    /**
     * toHash() se encarga de aplicar una función hash al String del terreno
     * @return String resultante de un algoritmo md5
     * @throws NoSuchAlgorithmException 
     */
    public String toHash() throws NoSuchAlgorithmException{
        return md5(this.toString());
    }
    
    /**
     * esObjetivo(..) comprueba que un terreno sea un terreno objetivo
     * @return
     */
    public boolean esObjetivo(){
        boolean obj = true;     
        if(nCasillasNoObjetivo != 0) {     
            obj = false;
        }     
        return obj;
    }

    /**
     * 
     * @return
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}
