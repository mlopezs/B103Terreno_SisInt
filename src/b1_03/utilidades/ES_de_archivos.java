package b1_03.utilidades;

import excepciones.EscrituraErronea;
import excepciones.LecturaErronea;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;

/**
 *
 * @author pacog
 */
public class ES_de_archivos {

    private static final int NUMERO_ATRIBUTOS_TERRENO = 6;
    private static final int INDICE_C = 4;
    private static final int INDICE_F = 5;

    /**
     * leer_archivo(..) lee un archivo de texto y devuelve un vector de enteros.
     *
     * @param path
     * @return int[]
     * @throws LecturaErronea
     */
    public static int[] leer_archivo(String path) throws LecturaErronea {

        int[] tabla;
        String[] tabla_string;
        BufferedReader br = null;
        FileReader fr = null;
        String linea;

        try {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            //Leemos la primera linea
            if ((linea = br.readLine()) != null) {
                tabla_string = linea.split(" ");

                //Si la longitud de la primera linea no es la que se expera
                //la lectura es erronea
                if (tabla_string.length != NUMERO_ATRIBUTOS_TERRENO) {
                    throw new LecturaErronea();
                }

                //Calculamos el tamaño del vector
                tabla = new int[(parseInt(tabla_string[INDICE_F])
                        * parseInt(tabla_string[INDICE_C])) + NUMERO_ATRIBUTOS_TERRENO];

                //Pasamos la primera linea al vector
                for (int i = 0; i < NUMERO_ATRIBUTOS_TERRENO; i++) {
                    tabla[i] = parseInt(tabla_string[i]);
                }

                //Variable para saber donde estamos en el vector
                int z = NUMERO_ATRIBUTOS_TERRENO;
                while ((linea = br.readLine()) != null) {

                    tabla_string = linea.split(" ");

                    //i empieza en 1 porque el primer elemento es un espacio 
                    for (int i = 1; i < tabla_string.length; i++) {
                        tabla[z] = parseInt(tabla_string[i]);
                        z++;
                    }

                }

            } else {
                throw new LecturaErronea();
            }

        } catch (IOException e) {

            throw new LecturaErronea();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                throw new LecturaErronea();

            }

        }
        return tabla;
    }

    /**
     * escribir_archivo(..) recibe un path y un vector de enteros y escribe un
     * archivo.
     *
     * @param path
     * @param tabla
     * @throws EscrituraErronea
     */
    public static void escribir_archivo(String path, int[] tabla) throws EscrituraErronea {
        String cadena = "";
        FileWriter writer = null;
        try {
            //Formateamos la cadena
            for (int i = 0; i < NUMERO_ATRIBUTOS_TERRENO; i++) {
                cadena += Integer.toString(tabla[i]);
                if (i != NUMERO_ATRIBUTOS_TERRENO - 1) {
                    cadena += " ";
                }
            }
            cadena += "\r\n";
            int z = NUMERO_ATRIBUTOS_TERRENO;
            for (int i = 0; i < tabla[INDICE_F]; i++) {
                for (int j = 0; j < tabla[INDICE_C]; j++) {
                    cadena = cadena + " " + Integer.toString(tabla[z]);
                    z++;
                }
                if (i != tabla[INDICE_F] - 1) {
                    cadena += "\r\n";
                }

            }
            //Escribimos la cadena
            writer = new FileWriter(path, false);
            writer.write(cadena);

        } catch (IOException e) {
            throw new EscrituraErronea();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new EscrituraErronea();
            }
        }

    }

    /**
     *
     * @param path
     * @param borrar_archivo
     * @param linea
     * @throws EscrituraErronea
     */
    public static void escribir_linea(String path, boolean borrar_archivo, String linea) throws EscrituraErronea {

        FileWriter writer = null;

        try {

            if (borrar_archivo == true) {
                writer = new FileWriter(path, false);
            } else {
                writer = new FileWriter(path, true);
            }

            writer.write(linea + "\r\n");

        } catch (IOException e) {
            throw new EscrituraErronea();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new EscrituraErronea();
            }
        }

    }

    private ES_de_archivos() {
    }
}
