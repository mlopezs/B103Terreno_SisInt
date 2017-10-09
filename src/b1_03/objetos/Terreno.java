package b1_03.objetos;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 * 
 * @version 1.0.0
 */

public class Terreno {
    
    private int [][] terr; // Cantidad de arena en cada celda
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

    
    public void mostrar() {
        
        System.out.println("Tractor(" + xt + "," + yt + ")\nTerreno:");
        
        for (int[] terr1 : terr) {
            for (int j = 0; j < terr[0].length; j++) {
                System.out.printf(" %d", terr1[j]);
            }
            System.out.print("\n");
        }
    }
    
    public void mvDcha(){
        this.xt++;
    }
    
    public void mvIzq(){
        this.xt--;
    }
    
    public void mvUp(){
        this.yt++;
    }
    
    public void mvDwn(){
        this.yt--;
    }

}
