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

    /**
     * compMovs(..) compruba los movimientos válidos, y si lo son, los añade a la
     * lista de válidos.
     * 
     * @param ini
     * @param pos 
     */
    public static void compMovs(int[] ini, LinkedList<int[]> pos){
        
        /*if (ini[0] == 1) {
            int[] a = {-1, 0};
            pos.add(a);
        }
        if (ini[1] == 1) {
            int[] a = {0, -1};
            pos.add(a);
        }
        if (ini[2] == 1) {
            int[] a = {0, 1};
            pos.add(a);
        }
        if (ini[3] == 1) {
            int[] a = {1, 0};
            pos.add(a);
        }*/
        
        if (ini[0] == 1) {
            int[] a = {0, -1};
            pos.add(a);
        }
        if (ini[1] == 1) {
            int[] a = {-1, 0};
            pos.add(a);
        }
        if (ini[2] == 1) {
            int[] a = {1, 0};
            pos.add(a);
        }
        if (ini[3] == 1) {
            int[] a = {0, 1};
            pos.add(a);
        }
    }
    
    /**
     * compAdd(..) comprueba que la suma de elementos del vector sea k, y en ese
     * caso lo añade a la lista.
     * 
     * @param vec
     * @param lvi
     * @param k 
     */
    public static void compAdd(int[] vec, LinkedList<int[]> lvi, int k) {
        int sum = 0;
        for (int a : vec) sum += a;
        if (sum == k) lvi.add(vec.clone());
    }

    /**
     * crearTerreno(..) crea un objeto de la clase Terreno partiendo de los
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
     * vec2mat(..) convierte un vector lineal a un array conocidas las filas y
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
     * esValido(..) comprueba que el sumatorio de la cantidad de arena de cada celda
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
    
    public static int[][] copiarMatrices(int[][] source){
        int[][] destiny = new int[source.length][source[0].length];
            for(int i = 0; i < source.length; i++){
                System.arraycopy(source[i], 0, destiny[i], 0, source.length);
            }
            
        return destiny;
    }

}
