package b1_03.utilidades;

import b1_03.objetos.Accion;
import b1_03.objetos.Terreno;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class Miscelanea {

    public static void generarAcciones(Terreno t, int k, int fs, int cs) {

        int[] movs = genMovs(t);
        LinkedList<int[]> distr
                = //generarDistribuciones(t, k, fs, cs, movs[4]);
                genDistros(t, k, movs[4]);

        LinkedList<Accion> act = new LinkedList<>();

        System.out.println("DISTRIBUCIONES");
        Iterator<int[]> it = distr.iterator();
        while (it.hasNext()) {
            System.out.println(Arrays.toString(it.next()));
        }

        System.out.println("MOVIMIENTOS:\n" + Arrays.toString(movs));

    }

    /**
     * genMovs() se encarga de generar los posibles movimientos que puede hacer
     * el tractor contenido en el terreno. Devuelve un vector de enteros donde las
     * cuatro primeras coordenadas son 1 o 0 dependiendo de si se puede mover:
     * primera coordenada es el movimiento a la izquierda, el segundo arriba, el
     * tercero abajo, el cuarto a la derecha, y el último es el número de adyacentes.
     * 
     * @param t
     * @return Vector de enteros
     */
    public static int[] genMovs(Terreno t) {

        int x = t.getXt(); // Posicion X del tractor
        int y = t.getYt(); // Posicion Y del tractor

        /*
        movs[0] -> Movimiento izquierda
        movs[1] -> Movimiento arriba
        movs[2] -> Movimiento abajo
        movs[3] -> Movimiento derecha
        movs[4] -> Numero adyacentes
         */
        int[] movs = {0, 0, 0, 0, 0};

        // Subir
        if ((y - 1) >= 0) {
            movs[1] = 1;
            movs[4]++;
        }

        // Bajar
        if ((y + 1) <= t.getTerr().length) {
            movs[2] = 1;
            movs[4]++;
        }

        // Derecha
        if ((x + 1) <= t.getTerr()[0].length) {
            movs[3] = 1;
            movs[4]++;
        }

        // Izquierda
        if ((x - 1) >= 0) {
            movs[0] = 1;
            movs[4]++;
        }

        return movs;
    }
    
    /**
     * genDistros() se encarga de generar todas las distribuciones posibles de 
     * arena partiendo del terreno que se pasa como argumento, y el número de
     * adyacentes previamente calculado.
     * 
     * @param t
     * @param k
     * @param fs
     * @param cs
     * @param nAdyac
     * @return LinkedList de vectores de enteros
     */
    public static LinkedList<int[]> genDistros(Terreno t, int k, int nAdyac) {

        LinkedList<int[]> distrs = new LinkedList<>(); // Lista de distribuciones
        
        int rep = t.getTerr()[t.getXt()][t.getYt()] - k; // Cantidad a repartir

        int[] vec = new int[nAdyac]; // Vector para asignación de cantidades

        for (int a = 0; a <= rep; a++) {
            vec[0] = a;
            if (nAdyac > 1) {
                for (int b = 0; b <= rep - a; b++) {
                    vec[1] = b;
                    if (nAdyac > 2) {
                        for (int c = 0; c <= rep - a - b; c++) {
                            vec[2] = c;
                            if (nAdyac > 3) {
                                for (int d = 0; d <= rep - a - b - c; d++) {
                                    vec[3] = d;
                                }
                                compAdd(vec, distrs, rep);
                            } else {
                                compAdd(vec, distrs, rep);
                            }
                        }
                    } else {
                        compAdd(vec, distrs, rep);
                    }
                }
            } else {
                compAdd(vec, distrs, rep);
            }
        }

        return distrs;
    }

    /**
     * compAdd() comprueba que la suma de elementos del vector sea k, y en ese
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
     * crearTerreno() crea un objeto de la clase Terreno partiendo de los
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
     * esValido() comprueba que el sumatorio de la cantidad de arena de cada celda
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
