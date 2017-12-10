/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.objetos;

/**
 *
 * @author pacog
 */
public class Persistencia {

    private boolean ready;
    private String secuencia="";
    private String solucion;

    public Persistencia() {
        ready=false;
    }

    public boolean isReady() {
        return ready;
    }

   /* public void setReady(boolean ready) {
        this.ready = ready;
    }*/

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
        this.ready = true;
    }
}
