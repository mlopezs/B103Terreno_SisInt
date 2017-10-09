package b1_03;

import b1_03.objetos.Terreno;
import excepciones.LecturaErronea;
import java.util.LinkedList;
import static b1_03.utilidades.Miscelanea.crearTerreno;
import static b1_03.utilidades.Miscelanea.esValido;
import static b1_03.utilidades.Miscelanea.seur;


/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Distribuidor {

    /**
     * main() es el núcleo del programa, desde donde se llama a los distintos
     * métodos en los que se realizan las funciones requeridas
     *
     * @param args
     * @throws LecturaErronea
     */
      public static void main(String[] args) throws LecturaErronea {

        seur();

        // Lista donde iremos guardando los terrenos generados tras cada acción
        LinkedList<Terreno> lt = new LinkedList<>();

        Terreno t; // Terreno inicial

        // Vector resultado de la lectura del fichero
        //int[] datos = /*Lecuta.leerFichero*/ new int[15];  
        int[] datos = {0, 2, 5, 8, 3, 3, 6, 8, 5, 6, 8, 2, 2, 0, 8};

        int k = datos[2]; // Cantidad objetivo de arena
        int max = datos[3]; // Cantidad máxima de arena

        int fs = datos[4]; // Número de filas
        int cs = datos[5]; // Número de columnas

        // Vector auxiliar donde posteriormente copiamos el segmento del vector datos
        // que se corresponde con la cantidad inicial de arena en cada casilla
        int[] aux = new int[fs * cs];
        System.arraycopy(datos, 6, aux, 0, aux.length);

        // Comprobamos validez del terreno, y si lo es, lo creamos.
        if (!esValido(fs, cs, k, aux, max)) {
            System.out.println("ERROR: Los datos son incorrectos.");
        } else {

            t = crearTerreno(aux, fs, cs, datos[0], datos[1]);

            lt.add(t); // Añadimos el terreno inicial a la lista

            // MUESTRA DE LOS DATOS RECOGIDOS Y PROCESADOS
            System.out.printf("\nk: %d, max: %d, fs: %d, cs: %d\n", k, max, fs, cs);
            t.mostrar();

            /**
             *
             *
             *
             * TO DO
             *
             *
             */
        }

    }
    
}
