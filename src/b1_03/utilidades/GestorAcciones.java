package b1_03.utilidades;

import b1_03.objetos.Terreno;
import java.util.Iterator;
import java.util.LinkedList;
import static b1_03.utilidades.Miscelanea.compMovs;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 *
 * @version 1.0.0
 */
public class GestorAcciones {

    /**
     * generarAcciones(..) genera las acciones dado el conjunto de distribuciones
     * proveniente del terreno, y el conjunto de movimientos posibles del tractor.
     * 
     * @param t
     * @param k
     * @param fs
     * @param cs
     * @param max 
     */
    public static void generarAcciones(Terreno t, int k, int fs, int cs, int max) {

        int[] movs = genMovs(t); // Movimientos
        LinkedList<int[]> posmovs = new LinkedList<>(); // Lista movimientos posibles
        compMovs(movs, posmovs);

        LinkedList<int[]> distr = genDistros(t, k, movs[4]); // Distribuciones (Todas)

        LinkedList<String> act = new LinkedList<>(); // Lista de acciones

        // Se combinan movimientos y distribuciones para formar acciones.
        Iterator<int[]> itm = posmovs.iterator();
        Iterator<int[]> itd;
        while (itm.hasNext()) {
            int[] actualMov = itm.next();
            itd = distr.iterator();
            while (itd.hasNext()) {
                String w = crearAccion(itd.next(), actualMov, movs[4], t.getXt(),
                        t.getYt(), fs, cs, t, max);
                if (!w.equals("")) {
                    act.add(w);
                }
            }
        }

        // Mostramos las acciones
        Iterator<String> itact = act.iterator();
        while (itact.hasNext()) {
            System.out.println(itact.next());
        }

    }

    /**
     * crearAcciones(..), como su propio nombre indica, crea las acciones para una
     * distribución y movimiento dados, teniendo en cuenta el terreno y sus
     * características.
     *
     * @param dstr
     * @param coord
     * @param ady
     * @param x
     * @param y
     * @param fs
     * @param cs
     * @param t
     * @param max
     * @return String que describe la accion con formato específico.
     */
    public static String crearAccion(int[] dstr, int[] coord, int ady, int x,
            int y, int fs, int cs, Terreno t, int max) {

        // Vector para que no vuelva a entrar en el mismo if
        boolean a[] = {true, true, true, true};

        // Futura posición del tractor
        int nx = x + coord[0];
        int ny = y + coord[1];

        // String de devolución
        String s = "((" + nx + "," + ny + "), [";

        for (int i = 0; i < ady; i++) {

            s += "(" + dstr[i];

            if (isAdy(y, x + 1, fs, cs, t.getTerr(), dstr[i], max) && a[0]) {
                s += "(" + (x + 1) + "," + y + ")";
                a[0] = false;
            } else if (isAdy(y, x - 1, fs, cs, t.getTerr(), dstr[i], max) && a[1]) {
                s += "(" + (x - 1) + "," + y + ")";
                a[1] = false;
            } else if (isAdy(y + 1, x, fs, cs, t.getTerr(), dstr[i], max) && a[2]) {
                s += "(" + x + "," + (y + 1) + ")";
                a[2] = false;
            } else if (isAdy(y - 1, x, fs, cs, t.getTerr(), dstr[i], max) && a[3]) {
                s += "(" + x + "," + (y - 1) + ")";
                a[3] = false;
            } else {
                s = "";
                break;
            }

            s += "), ";
        }

        if (!s.equals("")) {
            s = s.substring(0, s.length() - 2);
            s += "], 1)";
        }

        return s;
    }

    /**
     * isAdy(..) comprueba que la acción sea factible, es decir, que se pueda
     * mover la cantidad de arena a la casilla correspondiente.
     *
     * @param x
     * @param y
     * @param fs
     * @param cs
     * @param matrix
     * @param cantidad
     * @param max
     * @return true si es posible, false si no lo es.
     */
    public static boolean isAdy(int x, int y, int fs, int cs, int[][] matrix, int cantidad, int max) {
        return ((x < cs && x >= 0 && y >= 0 && y < fs) && (matrix[x][y] + cantidad) <= max);
    }

    /**
     * genMovs(..) se encarga de generar los posibles movimientos que puede hacer
     * el tractor contenido en el terreno. Devuelve un vector de enteros donde
     * las cuatro primeras coordenadas son 1 o 0 dependiendo de si se puede
     * mover: primera coordenada es el movimiento a la izquierda, el segundo
     * arriba, el tercero abajo, el cuarto a la derecha, y el último es el
     * número de adyacentes.
     *
     * @param t
     * @return Vector de enteros con los movimientos posibles del tractor.
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
        if ((x - 1) >= 0) {
            movs[1] = 1;
            movs[4]++;
        }
        // Bajar
        if ((x + 1) <= t.getTerr().length) {
            movs[2] = 1;
            movs[4]++;
        }
        // Derecha
        if ((y + 1) <= t.getTerr()[0].length) {
            movs[3] = 1;
            movs[4]++;
        }
        // Izquierda
        if ((y - 1) >= 0) {
            movs[0] = 1;
            movs[4]++;
        }
        return movs;
    }

    /**
     * genDistros(..) se encarga de generar todas las distribuciones posibles de
     * arena partiendo del terreno que se pasa como argumento, y el número de
     * adyacentes previamente calculado.
     *
     * @param t
     * @param k
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
                                Miscelanea.compAdd(vec, distrs, rep);
                            } else {
                                Miscelanea.compAdd(vec, distrs, rep);
                            }
                        }
                    } else {
                        Miscelanea.compAdd(vec, distrs, rep);
                    }
                }
            } else {
                Miscelanea.compAdd(vec, distrs, rep);
            }
        }
        return distrs;
    }

}
