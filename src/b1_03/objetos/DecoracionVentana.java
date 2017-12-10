/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.objetos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pacog
 */
public class DecoracionVentana extends Thread {

    private javax.swing.JTextArea txtSalida;
    private Persistencia pers;
    private javax.swing.JButton btnGuardarSol,btnIniciar,btnCancelar,btnCargarTerreno;
 //DecoracionVentana(txtSalida, persistencia,btnGuardarSol,btnIniciar,btnCancelar,btnCargarTerreno);
    @Override
    public void run() {
        decorar();
    }

    public DecoracionVentana(javax.swing.JTextArea txtSalida, Persistencia pers,javax.swing.JButton btnGuardarSol,javax.swing.JButton btnIniciar, javax.swing.JButton btnCancelar,javax.swing.JButton btnCargarTerreno) {
        this.txtSalida = txtSalida;
        this.pers = pers;
        this.btnGuardarSol=btnGuardarSol;
        this.btnIniciar=btnIniciar;
        this.btnCancelar=btnCancelar;
        this.btnCargarTerreno=btnCargarTerreno;
    }

    private void decorar() {
        int frame = 1;
        btnGuardarSol.setEnabled(false);
        btnIniciar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnCargarTerreno.setEnabled(false);

        do {
            if (frame > 4) {
                frame = 1;
            }
            switch (frame) {
                case 1:
                    txtSalida.setText("Procesando -> -");
                    break;
                case 2:
                    txtSalida.setText("Procesando -> \\");
                    break;
                case 3:
                    txtSalida.setText("Procesando -> |");
                    break;
                case 4:
                    txtSalida.setText("Procesando -> /");
                    break;

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {

            }
            frame++;
        } while (!pers.isReady());

        txtSalida.setText(pers.getSecuencia());
        txtSalida.append(pers.getSolucion());
        btnGuardarSol.setEnabled(true);
        btnIniciar.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnCargarTerreno.setEnabled(true);

    }
}
