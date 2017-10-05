/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import b1_03.objetos.Terreno;
import excepciones.LecturaErronea;

/**
 *
 * @author pacog
 */
public class Lectura_de_archivos {

    private static final int NUMERO_ATRIBUTOS_TERRENO = 6;
    private static final int INDICE_C = 4;
    private static final int INDICE_F = 5;

    public static Terreno leer_archivo(String path) throws LecturaErronea {
        //Creamos una matriz para 
        Terreno t1 = new Terreno();
        String[] datos;
        String[] tabla_aux;
        BufferedReader br = null;
        FileReader fr = null;

        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String linea;

            if ((linea = br.readLine()) != null) {
                datos = linea.split(" ");
                if (datos.length != NUMERO_ATRIBUTOS_TERRENO) {
                    throw new LecturaErronea();
                }
                int[][] tabla = new int[Integer.parseInt(datos[INDICE_F])][Integer.parseInt(datos[INDICE_C])];

                for (int z = 0; (linea = br.readLine()) != null; z++) {
                    tabla_aux = linea.split(" ");

                    for (int i = 1; i < tabla_aux.length; i++) {
                        tabla[z][i - 1] = Integer.parseInt(tabla_aux[i]);

                    }
                   /* t1 = new Terreno(Integer.parseInt(datos[0]),
                            Integer.parseInt(datos[1]), Integer.parseInt(datos[2]),
                            Integer.parseInt(datos[3]), Integer.parseInt(datos[4]),
                            Integer.parseInt(datos[5]), tabla);
*/
                }

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
        return t1;
    }
}
