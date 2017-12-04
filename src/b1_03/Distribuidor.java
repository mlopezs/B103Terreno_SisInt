package b1_03;

import b1_03.objetos.Accion;
import java.util.LinkedList;
import b1_03.objetos.Terreno;
import excepciones.LecturaErronea;
import static b1_03.utilidades.ES_de_archivos.leer_archivo;
import b1_03.utilidades.GestorAcciones;
import static b1_03.utilidades.Miscelanea.crearTerreno;
import static b1_03.utilidades.Miscelanea.esValido;
import b1_03.utilidades.Resolucion;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Distribuidor {

    static final int PQ_SIZE = 100; // Tamaño inicial de la frontera

    /**
     * main() es el núcleo del programa, desde donde se llama a los distintos
     * métodos en los que se realizan las funciones requeridas
     *
     * @param args
     * @throws LecturaErronea
     */
    public static void main(String[] args) throws LecturaErronea, NoSuchAlgorithmException {

     MainWindow w1=new MainWindow();
     w1.setVisible(true);
    }

}
