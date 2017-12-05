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
public class Accion {

    private int xt;
    private int yt;
    private int costo;
    private SubAccion[] nodos;

    public Accion(int xt, int yt, int costo, SubAccion[] nodos) {
        this.xt = xt;
        this.yt = yt;
        this.costo = costo;
        this.nodos = nodos;
    }

    @Override
    public String toString() {
        String s = "((" + yt + "," + xt + "), [";
        for (SubAccion a : nodos) {
            if (a != null) {
                s += a.toString() + ", ";
            }
        }
        s = s.substring(0, s.length() - 2);
        s += "], " + costo + ")";
        return s;
    }

    public int getXt() {
        return xt;
    }

    public int getYt() {
        return yt;
    }

    public int getCosto() {
        return costo;
    }

    public SubAccion[] getNodos() {
        return nodos;
    }
}
