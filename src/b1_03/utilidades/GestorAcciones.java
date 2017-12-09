package b1_03.utilidades;

import b1_03.objetos.Accion;
import b1_03.objetos.SubAccion;
import b1_03.objetos.Terreno;
import static b1_03.utilidades.Miscelanea.compAdd;
import static b1_03.utilidades.Miscelanea.compMovs;
import java.util.Iterator;
import java.util.LinkedList;

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
     * @return  
     */
    public static LinkedList<Accion> generarAcciones(Terreno t, int k, int fs, int cs, int max) {
        
        // Lista para almacenar las acciones
        LinkedList<Accion> act = new LinkedList<>();
        
        // Movimientos
        int[] movs = genMovs(t);
        
        // Movimientos posibles
        LinkedList<int[]> posmovs = new LinkedList<>();
        compMovs(movs, posmovs);

        // Distribuciones
        LinkedList<int[]> distr = genDistros(t, k, movs[4]);
        
        // Se combinan movimientos y distribuciones para formar acciones.
        Iterator<int[]> itm = posmovs.iterator();
        Iterator<int[]> itd;
        while (itm.hasNext()) {
            int[] actualMov = itm.next();
            itd = distr.iterator();
            while (itd.hasNext()) {
                Accion acc = crearAccion(itd.next(), actualMov, movs[4], t.getColumnaT(),
                        t.getFilaT(), fs, cs, t, max);
                if(acc.getNodos() != null) {
                    act.add(acc);
                }
            }
        }
        return act;        
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
     * @return String que describe la acción con formato específico.
     */
    public static Accion crearAccion(int[] dstr, int[] coord, int ady, int x,
            int y, int fs, int cs, Terreno t, int max) {

        // Vector para que no vuelva a entrar en el mismo if
        boolean a[] = {true, true, true, true};

        // Posición final del tractor
        int nx = x + coord[1];
        int ny = y + coord[0];
        
        // Costo de la acción (Arena desplazada más uno)
        int costo = 1;
        for(int d : dstr){
            costo += d;
        }
        
        // Subacciones de la accion
        SubAccion[] na = new SubAccion[ady];

        // Creación de las subacciones
        for (int i = 0; i < ady; i++) {            
            if (isAdy(y, x + 1, fs, cs, t.getTerr(), dstr[i], max) && a[0]) {
                na[i] = new SubAccion(dstr[i], (x+1), y);
                a[0] = false;
            } else if (isAdy(y, x - 1, fs, cs, t.getTerr(), dstr[i], max) && a[1]) {
                na[i] = new SubAccion(dstr[i], (x-1), y);
                a[1] = false;
            } else if (isAdy(y + 1, x, fs, cs, t.getTerr(), dstr[i], max) && a[2]) {
                na[i] = new SubAccion(dstr[i], x, (y + 1));
                a[2] = false;
            } else if (isAdy(y - 1, x, fs, cs, t.getTerr(), dstr[i], max) && a[3]) {
                na[i] = new SubAccion(dstr[i], x, (y - 1));
                a[3] = false;
            } else {
                na = null;
                break;
            }
        }
        return new Accion(nx, ny, costo, na);
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
        return ((x < cs && x >= 0 && y >= 0 && y < fs) && ((matrix[x][y] + cantidad) <= max));
    }

    /**
     * genMovs(..) se encarga de generar los posibles movimientos que puede hacer
     * el tractor contenido en el terreno. Devuelve un vector de enteros:
     *   movs[0] - Movimiento izquierda - {0, 1}
     *   movs[1] - Movimiento arriba - {0, 1}
     *   movs[2] - Movimiento abajo - {0, 1}
     *   movs[3] - Movimiento derecha - {0, 1}
     *   movs[4] - Número adyacentes
     *
     * @param t
     * @return
     */
    public static int[] genMovs(Terreno t) {
        int x = t.getColumnaT(); // Posicion X del tractor
        int y = t.getFilaT(); // Posicion Y del tractor
        /*
        movs[0] -> Movimiento izquierda
        movs[1] -> Movimiento arriba
        movs[2] -> Movimiento abajo
        movs[3] -> Movimiento derecha
        movs[4] -> Numero adyacentes
         */
        int[] movs = {0, 0, 0, 0, 0};
        
        if ((x - 1) >= 0) {
            movs[0] = 1;
            movs[4]++;
        }
        
        if ((x + 1) < t.getTerr()[0].length) {
            movs[3] = 1;
            movs[4]++;
        }
        if ((y + 1) < t.getTerr().length) {
            movs[2] = 1;
            movs[4]++;
        }
        
        if ((y - 1) >= 0) {
            movs[1] = 1;
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
        
        // Lista de distribuciones
        LinkedList<int[]> distrs = new LinkedList<>();
        
        // Cantidad a repartir
        int rep = t.getTerr()[t.getColumnaT()][t.getFilaT()] - k;
        
        // Vector para asignación de cantidades
        int[] vec = new int[nAdyac]; 
        
        if(rep <= 0){
            
            for(int i = 0; i < nAdyac; i++){
                vec[i] = 0;
            }
            
            compAdd(vec, distrs, 0);
        }
        
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

}
