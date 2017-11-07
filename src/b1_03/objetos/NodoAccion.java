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
public class NodoAccion {

    private int cantidad;
    private int posx;
    private int posy;

    public NodoAccion(int cantidad, int posx, int posy) {
        this.cantidad = cantidad;
        this.posx = posx;
        this.posy = posy;

    }

    public int getCantidad() {
        return cantidad;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    @Override
    public String toString() {
        return "(" + cantidad + "(" + posx + "," + posy + "))";
    }

}
