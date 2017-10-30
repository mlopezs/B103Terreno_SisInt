package b1_03;

import b1_03.objetos.FronteraCola;
import b1_03.objetos.FronteraLista;
import b1_03.objetos.FronteraPila;
import b1_03.objetos.Nodo;
import java.util.LinkedList;
import b1_03.objetos.Terreno;
import excepciones.LecturaErronea;
import static b1_03.utilidades.ES_de_archivos.leer_archivo;
import b1_03.utilidades.GestorAcciones;
import static b1_03.utilidades.Miscelanea.crearTerreno;
import static b1_03.utilidades.Miscelanea.esValido;

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

        // Lista donde iremos guardando los terrenos generados tras cada acción
        LinkedList<Terreno> lt = new LinkedList<>();

        Terreno t; // Terreno inicial

        // Vector resultado de la lectura del fichero
        int[] datos = leer_archivo("terreno_1.txt");

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

            t = crearTerreno(aux, fs, cs, datos[1], datos[0]);

            lt.add(t); // Añadimos el terreno inicial a la lista

            // MUESTRA DE LOS DATOS RECOGIDOS Y PROCESADOS
            System.out.printf("\nk: %d, max: %d, fs: %d, cs: %d\n", k, max, fs, cs);
            System.out.println(t.toString());

            GestorAcciones.generarAcciones(t, k, fs, cs, max);

        }
        
        pruebas();
        
    }
    
    public static void pruebas() throws Error{
        
        FronteraLista frontL = new FronteraLista();
        FronteraCola frontC = new FronteraCola();
        FronteraPila frontP = new FronteraPila();

        Nodo a = new Nodo("hfskskdhf", 5, 3, null, "8292898", 3);
        Nodo b = new Nodo("hfskskdhf", 5, 3, a, "8292898", 3);
        
        long cl = 0;
        try {
            while (true) {
                frontL.insertar(b);
                cl++;
            }
        } catch (Exception e) {
            System.out.println("Lista" + cl);
        }
        
        long cc = 0;
        try{
            while(true){
                frontC.insertar(a);
                frontC.insertar(b);
                cc++;
            }
        }catch (Exception e){
            System.out.println("Cola" + cc);
        }
        
        long cs1 = 0;
        try{
            while(true){
                frontP.insertar(a);
                cs1++;
            }
        }catch (Exception e){
            System.out.println("Cola" + cs1);
        }
    }

}
