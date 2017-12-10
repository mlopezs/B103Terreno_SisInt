package b1_03.Interfaz;

import b1_03.objetos.Comunicador;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class DecoracionVentana extends Thread {
    //Genera un hilo que imprime en la salida de texto la solucion una vez que
    //termina el algoritmo

    //Salida de texto
    private final javax.swing.JTextArea txtSalida;
    //Comunicador entre hilos
    private final Comunicador com;
    //Botones de la ventana
    private final javax.swing.JButton btnGuardarSol;
    private final javax.swing.JButton btnIniciar;
    private final javax.swing.JButton btnCancelar;
    private final javax.swing.JButton btnCargarTerreno;

    @Override
    public void run() {
        decorar();
    }

    public DecoracionVentana(javax.swing.JTextArea txtSalida, Comunicador pers, javax.swing.JButton btnGuardarSol, javax.swing.JButton btnIniciar, javax.swing.JButton btnCancelar, javax.swing.JButton btnCargarTerreno) {
        this.txtSalida = txtSalida;
        this.com = pers;
        this.btnGuardarSol = btnGuardarSol;
        this.btnIniciar = btnIniciar;
        this.btnCancelar = btnCancelar;
        this.btnCargarTerreno = btnCargarTerreno;
    }

    /**
     * El siguiente método decora la ventana según el estado de ejecucion del
     * algoritmo
     */
    private void decorar() {
        int frame = 1;

        //Ponemos los botones en su estado correcto
        btnGuardarSol.setEnabled(false);
        btnIniciar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnCargarTerreno.setEnabled(false);

        //Bucle que comprueba el estado del algoritmo
        do {
            if (frame > 4) {
                frame = 1;
            }

            //Animacion de espera
            switch (frame) {
                case 1:
                    txtSalida.setText("[Procesando] -");
                    break;
                case 2:
                    txtSalida.setText("[Procesando] \\");
                    break;
                case 3:
                    txtSalida.setText("[Procesando] |");
                    break;
                case 4:
                    txtSalida.setText("[Procesando] /");
                    break;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {

            }
            frame++;
        } while (!com.isReady());

        //Imprimimos la solución
        txtSalida.setText(com.getSecuencia());
        txtSalida.append(com.getSolucion());
        //Ponemos los botones en su estado correcto
        btnGuardarSol.setEnabled(true);
        btnIniciar.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnCargarTerreno.setEnabled(true);

    }
}
