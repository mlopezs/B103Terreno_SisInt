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
    private int xt; // Posición en el eje x del tractor
    private int yt; // Posición en el eje y del tractor

    public Terreno() {
    }

    public Terreno(int[][] terr, int xt, int yt) {
        this.terr = terr;
        this.xt = xt;
        this.yt = yt;
    }

    public int[][] getTerr() {
        return terr;
    }

    public void setTerr(int[][] terr) {
        this.terr = terr;
    }

    public int getXt() {
        return xt;
    }

    public void setXt(int xt) {
        this.xt = xt;
    }

    public int getYt() {
        return yt;
    }

    public void setYt(int yt) {
        this.yt = yt;
    }

    public void mvDcha() {
        this.xt++;
    }

    public void mvIzq() {
        this.xt--;
    }

    public void mvUp() {
        this.yt++;
    }

    public void mvDwn() {
        this.yt--;
    }

    @Override
    public String toString()  {
        
        String salida = "(" + xt + "," + yt + ")\n";
        
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

    public Terreno crearTerrenoAPartirDeUnaAccion(Accion ac){
        
        int[][] t = this.terr.clone();
        SubAccion[] sac = ac.getNodos();
        
        for(int i = 0; i < sac.length; i++){
            t[sac[i].getPosy()][sac[i].getPosx()]+=sac[i].getCantidad();
        }
        
        return new Terreno(t, ac.getXt(), ac.getYt());
    }
    
}