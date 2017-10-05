package b1_03;

import b1_03.objetos.Terreno;
import excepciones.LecturaErronea;
import java.util.ArrayList;
import java.util.LinkedList;

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

        generatorDistros();

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

    /**
     * crearTerreno crea un objeto de la clase Terreno partiendo de los
     * parámetros que se piden. Después devuelve dicho objeto.
     *
     * @param a
     * @param fs
     * @param cs
     * @param xt
     * @param yt
     * @return Terreno
     */
    public static Terreno crearTerreno(int[] a, int fs, int cs, int xt, int yt) {
        return new Terreno(vec2mat(a, fs, cs), xt, yt);
    }

    /**
     * vec2mat convierte un vector lineal a un array conocidas las filas y
     * columnas
     *
     * @param aux
     * @param fs
     * @param cs
     * @return mt
     */
    public static int[][] vec2mat(int[] aux, int fs, int cs) {

        int[][] mt = new int[fs][cs]; // Matriz terreno a devolver

        int l = 0; // Posicion dentro de aux

        for (int i = 0; i < fs; i++) {
            for (int j = 0; j < cs; j++) {
                mt[i][j] = aux[l++];
            }
        }

        return mt;
    }

    /**
     * esValido comprueba que el sumatorio de la cantidad de arena de cada celda
     * sea igual que el volumen total de arena (fs*cs*k)
     *
     * @param fs
     * @param cs
     * @param k
     * @param can
     * @param max
     * @return true si la cantidad coincide, false si no
     */
    public static boolean esValido(int fs, int cs, int k, int[] can, int max) {
        int sum = 0;
        for (int a : can) {
            if (a > max) {
                return false;
            }
            sum += a;
        }
        return (sum == fs * cs * k);
    }

    public static void generatorDistros() {

        //// PARAMETROS DE ENTRADA /////
        int ac = 10;
        int fi = 2;
        int nAdy = 4;
        ////////////////////////////////

        int f = 0;
        int c = 0;

        LinkedList<int[]> a = new LinkedList<>();
        int k = ac - fi;
        int dstr = 0;

        int[] aux = {0, 0, 0, 0};

        for (int i = 0; i <= k; i++) {
            dstr = k - i;
            for (int ad = 0; ad < nAdy; ad++) {
                dstr = k - i;
                c = ad;
                for(int q = 0; q < aux.length; q++){
                    aux[q] = 0;
                }
                while (dstr >= 0) {
                    if (dstr > 0 && c < aux.length) {
                        aux[c] = dstr;
                        dstr = k - dstr;
                        c++;
                    } else if (c >= aux.length && dstr > 0) {
                        c = c - aux.length;
                        aux[c] = dstr;
                        dstr = k - dstr;
                        c++;
                    } else if (c < aux.length && dstr == 0) {
                        for (int n = c; n < aux.length; n++) {
                            aux[n] = 0;
                        }
                        a.add(aux);
                        break;
                    } else if (c >= aux.length && dstr == 0) {
                        for (int n = c; n < aux.length; n++) {
                            aux[n] = 0;
                        }
                        a.add(aux);
                        break;
                    }
                }
            }
        }

    }

}