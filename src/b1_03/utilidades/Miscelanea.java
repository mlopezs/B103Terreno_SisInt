package b1_03.utilidades;

import b1_03.objetos.Terreno;
import java.util.LinkedList;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Miscelanea {

    private static void limpiarVector(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
    }

    public static void seur() {
        //// PARAMETROS DE ENTRADA /////
        int ac = 12;
        int fi = 2;
        int nAdy = 4;
        ////////////////////////////////

        int f = 0;
        int c = 0;

        LinkedList<int[]> a = new LinkedList<>();
        int k = ac - fi;
        int dstr = 0;
        int dstrAux = 0;
        int[] aux = {0, 0, 0, 0};

        int nAdyacentes = 4;
        int aRepartir = k;
        int nCachos = 0;

        for (int i = 0; i < k; i++) {
            aRepartir = k;
            dstr = k - i;
            for (int j = 0; j < nAdyacentes; j++) {
                c = j;
                aRepartir = k;
                limpiarVector(aux);
                while (aRepartir != 0) {
                    dstrAux = aRepartir - dstr;
                    if (dstrAux >= 0) {
                        aux[c] = aux[c] + dstr;
                        aRepartir -= dstr;
                    } else {
                        aux[c] = aux[c] + 1;
                        aRepartir--;
                    }

                    c++;
                    if (c >= aux.length) {
                        c = c - aux.length;
                    }
                }
                a.add(aux);
                for (int z = 0; z < aux.length; z++) {
                    System.out.println(aux[z] + " -> " + z);
                }
            }
        }
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
        int dstrAux = 0;

        int[] aux = {0, 0, 0, 0};

        for (int i = 0; i <= k; i++) {
            dstr = k - i;
            for (int ad = 0; ad < nAdy; ad++) {
                dstr = k - i;
                dstrAux = k;
                c = ad;
                for (int q = 0; q < aux.length; q++) {
                    aux[q] = 0;
                }
                while (dstrAux >= 0) {
                    if (dstrAux > 0 && c < aux.length) {
                        if (dstrAux > 0) {
                            aux[c] = dstr;
                            dstr = dstrAux - dstr;
                            dstrAux = dstr;
                            c++;
                        } else {
                            aux[c] = dstr;
                            dstr = 0;
                            c++;
                        }

                    } else if (c >= aux.length && dstr > 0) {
                        c = c - aux.length;

                        if (dstrAux > 0) {
                            aux[c] = dstr;
                            dstr = dstrAux - dstr;
                            dstrAux = dstr;
                            c++;
                        } else {
                            aux[c] = dstr;
                            dstr = 0;
                            c++;
                        }
                    } else if (c < aux.length && dstr == 0) {
                        for (int n = c; n < aux.length; n++) {
                            aux[n] = 0;
                        }
                        a.add(aux);
                        for (int done = 0; done < aux.length; done++) {
                            System.out.println(aux[done] + " tierra en coordenada -> " + done);
                        }
                        break;
                    } else if (c >= aux.length && dstr == 0) {
                        for (int n = c; n < aux.length; n++) {
                            aux[n] = 0;
                        }
                        a.add(aux);
                        for (int done = 0; done < aux.length; done++) {
                            System.out.println(aux[done] + " tierra en coordenada -> " + done);
                        }
                        break;
                    }
                }
            }
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

}
