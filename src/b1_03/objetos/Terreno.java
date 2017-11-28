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

    public Terreno(int[][] terr, int xt, int yt) {
        this.terr = terr;
        this.columnat = xt;
        this.filat = yt;
    }

    public int[][] getTerr() {
        return this.terr;
    }

    public void setTerr(int[][] terr) {
        this.terr = terr;
    }

    public int getColumnaT() {
        return columnat;
    }

    public void setColumnaT(int xt) {
        this.columnat = xt;
    }

    public int getFilaT() {
        return filat;
    }

    public void setFilaT(int yt) {
        this.filat = yt;
    }

    @Override
    public String toString()  {
        
        String salida = "(" + columnat + "," + filat + ")\n";
        
        for (int[] terr1 : terr) {
            for (int j = 0; j < terr[0].length; j++) {
                salida = salida + (terr1[j] + " ");
            }
            salida = salida + "\n";
        }
        
        return salida;
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
     * @param k
     * @return true si es objetivo, false si no
     */
    public boolean esObjetivo(int k){
       
        boolean obj = true;
     
        for (int[] terr1 : terr) {
            for (int j = 0; j < terr[0].length; j++) {
               if(terr1[j] != k){
                   obj = false;
                   break;
               }
            }           
        }
      
        return obj;
    }
    
}