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
    private int xt,yt,costo;
    private NodoAccion[]nodos=new NodoAccion[4];
    
    public Accion(int xt,int yt, int costo,  NodoAccion[]nodos){
        this.xt=xt;
        this.yt=yt;
        this.costo=costo;
        this.nodos=nodos;
    }

    @Override
    public String toString() {
        return "Accion{" + "xt=" + xt + ", yt=" + yt + ", costo=" + costo + ", nodos=" + nodos + '}';
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

    public NodoAccion[] getNodos() {
        return nodos;
    }
}
