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
    private int nCasillasNoObjetivo;

    public Terreno(int[][] terr, int xt, int yt, int k) {
        this.terr = terr;
        this.columnat = xt;
        this.filat = yt;
        this.nCasillasNoObjetivo = contarCasillasNoObjetivo(this.terr, k);
    }

    public int getnCasillasNoObjetivo() {
        return nCasillasNoObjetivo;
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

    public int contarCasillasNoObjetivo(int[][] terreno, int k){
        int nCasillasNoObjetivo = 0;
        
        for(int i = 0; i < terreno.length; i++){
            for(int j = 0; j < terreno[i].length;j++){
                if(terreno[i][j] != k){
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
     * @param k
     * @return true si es objetivo, false si no
     */
    public boolean esObjetivo(){
       
        boolean obj = true;
     /*
        for (int[] terr1 : terr) {
            for (int j = 0; j < terr[0].length; j++) {
               if(terr1[j] != k){
                   obj = false;
                   break;
               }
            }           
        }
      */
     
        if(nCasillasNoObjetivo != 0) obj = false;     
        return obj;
    }
    
}