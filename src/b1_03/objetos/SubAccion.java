/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.objetos;

/**
 *
 * @author paco
 */
public class SubAccion {

    

    private final int cantidad;
    private final int posx;
    private final int posy;

    /**
     *
     * @param cantidad
     * @param posx
     * @param posy
     */
    public SubAccion(int cantidad, int posx, int posy) {
        this.cantidad = cantidad;
        this.posx = posx;
        this.posy = posy;

    }

    /**
     *
     * @return
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     *
     * @return
     */
    public int getPosx() {
        return posx;
    }

    /**
     *
     * @return
     */
    public int getPosy() {
        return posy;
    }

    @Override
    public String toString() {
        return "(" + cantidad + "(" + posx + "," + posy + "))";
    }

}
