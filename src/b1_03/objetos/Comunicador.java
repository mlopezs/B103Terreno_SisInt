package b1_03.objetos;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */

public class Comunicador {

    //Permite comunicar los hilos y guardar la solución temporalmente
    private boolean ready; //El algoritmo ha terminado
    private String secuencia = "";
    private String solucion;

    public Comunicador() {
        ready = false;
    }

    /**
     * Informa de si el algoritmo ha terminado
     *
     * @return ready
     */
    public boolean isReady() {
        return ready;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * Retorna la solucion
     *
     * @return solucion
     */
    public String getSolucion() {
        return solucion;
    }
    
     /**
     * Permite establecer la solucion
     * @param solucion
     */
    public void setSolucion(String solucion) {
        this.solucion = solucion;
        this.ready = true;
    }
}
