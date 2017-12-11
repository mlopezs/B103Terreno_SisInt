package b1_03.Interfaz;

import b1_03.objetos.Comunicador;
import b1_03.objetos.Terreno;
import static b1_03.utilidades.ES_de_archivos.escribir_linea;
import static b1_03.utilidades.ES_de_archivos.leer_archivo;
import static b1_03.utilidades.Miscelanea.crearTerreno;
import static b1_03.utilidades.Miscelanea.esValido;
import b1_03.utilidades.Resolucion;
import b1_03.excepciones.ArchivoErroneo;
import b1_03.excepciones.EscrituraErronea;
import b1_03.excepciones.LecturaErronea;
import java.awt.Color;
import static java.awt.EventQueue.invokeLater;
import java.awt.HeadlessException;
import static java.lang.System.arraycopy;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.JFileChooser;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class MainWindow extends javax.swing.JFrame {

    //Ruta al terreno
    private String ruta;
    //Comunicador entre hilos
    private Comunicador com;
    //Hilo del algoritmo
    private Thread res;
    //Hilo del decorador de ventana
    private Thread deco;

    private final static int PROFMAX = 999999999;

    /**
     * Creates new form MainnWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        btnIniciar = new javax.swing.JButton();
        txtPath = new javax.swing.JTextField();
        cbAlgoritmo = new javax.swing.JComboBox<>();
        btnGuardarSol = new javax.swing.JButton();
        spnProfMax = new javax.swing.JSpinner();
        spnIncProf = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCargarTerreno = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Distribuidor");
        setResizable(false);

        txtSalida.setEditable(false);
        txtSalida.setColumns(20);
        txtSalida.setRows(5);
        jScrollPane1.setViewportView(txtSalida);

        btnIniciar.setBackground(new java.awt.Color(92, 225, 92));
        btnIniciar.setText("Iniciar");
        btnIniciar.setEnabled(false);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        txtPath.setEditable(false);

        cbAlgoritmo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anchura", "Profundidad", "C. Uniforme", "A*", "Voraz", "P. Iterativa" }));
        cbAlgoritmo.setEnabled(false);
        cbAlgoritmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlgoritmoActionPerformed(evt);
            }
        });

        btnGuardarSol.setText("Guardar solucion");
        btnGuardarSol.setEnabled(false);
        btnGuardarSol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSolActionPerformed(evt);
            }
        });

        spnProfMax.setModel(new javax.swing.SpinnerNumberModel(50, 50, null, 50));
        spnProfMax.setEnabled(false);
        spnProfMax.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnProfMaxStateChanged(evt);
            }
        });

        spnIncProf.setModel(new javax.swing.SpinnerNumberModel(10, 10, null, 15));
        spnIncProf.setEnabled(false);
        spnIncProf.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnIncProfStateChanged(evt);
            }
        });

        jLabel1.setText("Prof. Max.");

        jLabel2.setText("Inc. Prof");

        btnCargarTerreno.setText("Cargar terreno");
        btnCargarTerreno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTerrenoActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(208, 55, 55));
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Algoritmo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardarSol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(cbAlgoritmo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spnProfMax)
                    .addComponent(spnIncProf)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(btnCargarTerreno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addComponent(txtPath))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarTerreno))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnProfMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnIncProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btnGuardarSol, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * El siguiente método es el escuchador de la pulsación del botón Inicio.
     * Este método se encarga de arrancar todos los componentes necesarios para
     * la resolución del problema. Dentro de la clase se puede encontrar más
     * documentación.
     *
     * @param evt
     */
    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed

        //Limpiamos la salida
        txtSalida.setText("");
        txtSalida.setForeground(Color.BLACK);

        Terreno t; // Terreno inicial

        int[] datos; // Vector resultado de la lectura del fichero

        try {

            datos = leer_archivo(ruta); // Lectura del fichero

            int k = datos[2]; // Cantidad objetivo de arena
            int max = datos[3]; // Cantidad máxima de arena

            int fs = datos[4]; // Número de filas
            int cs = datos[5]; // Número de columnas

            // Vector auxiliar donde posteriormente copiamos el segmento del vector datos
            // que se corresponde con la cantidad inicial de arena en cada casilla.
            int[] aux = new int[fs * cs];
            arraycopy(datos, 6, aux, 0, aux.length);

            // Comprobamos validez del terreno
            if (!esValido(fs, cs, k, aux, max)) {

                txtSalida.setText("ERROR: El terreno cargado es incorrecto.");
                txtSalida.setForeground(Color.RED);

            } else {

                // Creamos el terreno
                t = crearTerreno(aux, fs, cs, datos[1], datos[0], k);

                // Resolucion(Terreno tInicial, int tipoAlgoritmo, int k, int fs, int cs, int max, int profundidadMax, int incProf, Persistencia persistencia) {
                // Comprobamos el tipo de algoritmo elegido la ComboBox
                if (cbAlgoritmo.getSelectedIndex() != 5) {
                    //Iniciamos el comunicador
                    com = new Comunicador();
                    //Creamos el hilo del algoritmo
                    res = new Resolucion(t, cbAlgoritmo.getSelectedIndex(), k, fs, cs, max, PROFMAX, 0, com, false);
                    //Lanzamos el hilo
                    res.start();

                } else {

                    //Iniciamos el comunicador
                    com = new Comunicador();
                    //Creamos el hilo del algoritmo
                    res = new Resolucion(t, 1, k, fs, cs, max, (int) spnProfMax.getValue(), (int) spnIncProf.getValue(), com, true);
                    //Lanzamos el hilo
                    res.start();
                }
                //Creamos el decorador 
                deco = new DecoracionVentana(txtSalida, com, btnGuardarSol, btnIniciar, btnCancelar, btnCargarTerreno,cbAlgoritmo);
                //Lanzamos el decorador
                deco.start();
            }

        } catch (LecturaErronea ex) {
            txtSalida.setText("Archivo no encontrado-> " + ruta);
            txtSalida.setForeground(Color.RED);
        } catch (ArchivoErroneo ex) {
            txtSalida.setText("El archivo no es valido-> " + ruta);
            txtSalida.setForeground(Color.RED);
        } catch (Exception ex) {
            txtSalida.setText("El archivo no es valido-> " + ruta);
            txtSalida.setForeground(Color.RED);
        }

    }//GEN-LAST:event_btnIniciarActionPerformed

    /**
     * Escuchador del botón GuardarSolución. Lanza una ventana para seleccionar
     * un directorio y nombrar el archivo donde guardar las acciones (solucion).
     *
     * @param evt
     */
    private void btnGuardarSolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSolActionPerformed
        String path = "";
        JFileChooser jfch = new JFileChooser();
        try {
            if (jfch.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                path = jfch.getSelectedFile().getAbsolutePath();
                escribir_linea(path, true, com.getSolucion());
            }
        } catch (EscrituraErronea | HeadlessException ex) {
            txtSalida.append("Error al guardar fichero -> " + path);
            txtSalida.setForeground(Color.RED);
        }
    }//GEN-LAST:event_btnGuardarSolActionPerformed

    /**
     * Escuchador del ComboBox. Si se elige la opción 5 (P.Iterativa), se
     * desbloquean los spinners para seleccionar la profundidad máxima y el
     * incremento de la profundidad para ese algoritmo.
     *
     * @param evt
     */
    private void cbAlgoritmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlgoritmoActionPerformed
        if (cbAlgoritmo.getSelectedIndex() == 5) {
            spnIncProf.setEnabled(true);
            spnProfMax.setEnabled(true);
        } else {
            spnIncProf.setEnabled(false);
            spnProfMax.setEnabled(false);
        }
    }//GEN-LAST:event_cbAlgoritmoActionPerformed

    /**
     * Escuchador del botón CargarTerreno. Lanza una ventana para seleccionar un
     * archivo desde el que leer el terreno inicial.
     *
     * @param evt
     */
    private void btnCargarTerrenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTerrenoActionPerformed
        JFileChooser jfch = new JFileChooser();
        try {
            if (jfch.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                ruta = jfch.getSelectedFile().getAbsolutePath();
                txtPath.setText(ruta.substring(ruta.lastIndexOf("\\") + 1, ruta.length()));
                cbAlgoritmo.setEnabled(true);
                btnIniciar.setEnabled(true);
                txtSalida.setText("");
            }
        } catch (HeadlessException ex) {
            txtSalida.append("Error al cargar fichero -> " + ruta);
            txtSalida.setForeground(Color.RED);
        }
        btnGuardarSol.setEnabled(false);

    }//GEN-LAST:event_btnCargarTerrenoActionPerformed
    /**
     * Escuchador del botón Cancelar. Permite cancelar la ejecucion del
     * algoritmo.
     *
     * @param evt
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Si el hilo del algoritmo no es null
        if (res != null || deco != null) {
            //Si el hilo del algoritmo esta vivo
            if (res.isAlive() || deco.isAlive()) {
                //Suspendemos el hilo del algoritmo y del decorador
                //(por alguna razón interrumpirlos no funcionaba)
                res.suspend();
                deco.suspend();

                //Ponemos los hilos y el comunicador a null para que el recolector
                //de basuras los elimine.
                res = null;
                deco = null;
                com = null;

                //Informamos que la operación ha sido cancelada
                txtSalida.setForeground(Color.RED);
                txtSalida.setText("Operacion cancelada por el usuario.");
                btnCargarTerreno.setEnabled(true);
                btnCancelar.setEnabled(false);
                btnIniciar.setEnabled(true);
                btnGuardarSol.setEnabled(false);
                cbAlgoritmo.setEnabled(true);
            }
        }


    }//GEN-LAST:event_btnCancelarActionPerformed

    private void spnProfMaxStateChanged(javax.swing.event.ChangeEvent evt) {
        //Aqui no hay nada
    }
    private void spnIncProfStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnIncProfStateChanged
        //Comprobamos que el incremento de profundidad no sea mayor que la
        //profundidad maxima
        if ((int) spnProfMax.getValue() < (int) spnIncProf.getValue()) {
            spnIncProf.setValue(spnProfMax.getValue());
        }

    }//GEN-LAST:event_spnIncProfStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            getLogger(MainWindow.class.getName()).log(SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCargarTerreno;
    private javax.swing.JButton btnGuardarSol;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JComboBox<String> cbAlgoritmo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnIncProf;
    private javax.swing.JSpinner spnProfMax;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}
