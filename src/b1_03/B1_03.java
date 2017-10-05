package b1_03;

import b1_03.objetos.Terreno;
import excepciones.LecturaErronea;
import java.util.LinkedList;

/**
 * @author Alfonso Barragán
 * @author Francisco Manuel García
 * @author Marcos López
 * 
 * @version 1.0.0
 */

public class B1_03 {

    public static void main(String[] args) throws LecturaErronea {
                
        // Lista donde iremos guardando los terrenos generados tras cada acción
        LinkedList<Terreno> lt = new LinkedList<>();
        
        Terreno t0 = new Terreno(); // Terreno inicial
        
        // Vector resultado de la lectura del fichero
        //int[] datos = /*Lecuta.leerFichero*/ new int[15];  
        int[] datos = {0,2,5,8,3,3,6,8,5,6,8,2,2,0,8};
        
        t0.setXt(datos[0]); // PosX del tractor
        t0.setYt(datos[1]); // PosY del tractor
        
        int k = datos[2]; // Cantidad objetivo de arena
        int max = datos[3]; // Cantidad máxima de arena
        
        int fs = datos[4]; // Número de filas
        int cs = datos[5]; // Número de columnas
        
        // Vector auxiliar donde posteriormente copiamos el segmento del vector datos
        // que se corresponde con la cantidad inicial de arena en cada casilla
        int[] aux = new int[fs*cs];
        System.arraycopy(datos, 6, aux, 0, aux.length);
        
        // Comprobamos validez del terreno
        if(!esValido(fs, cs, k, aux)){
            System.out.println("ERROR EXIT");
            System.exit(1); /***********************ARREGLAR********************/
        }
        
        t0.setTerr(vec2mat(aux, fs, cs)); // Pasamos de vector a matriz
        
        lt.add(t0); // Añadimos el terreno inicial a la lista
        
        // MUESTRA DE LOS DATOS RECOGIDOS Y PROCESADOS
        System.out.printf("\nk: %d, max: %d, fs: %d, cs: %d\n", k, max, fs, cs);
        t0.mostrar();
    
        /**
         * 
         * 
         * 
         * TO DO
         * 
         * 
        */
        
    }
    
    /**
     * vec2mat convierte un vector lineal a un array conocidas las filas y columnas
     * 
     * @param aux
     * @param fs
     * @param cs
     * @return mt
     */
    public static int[][] vec2mat(int[] aux, int fs, int cs){
        
        int[][] mt = new int[fs][cs]; // Matriz terreno a devolver
        
        int l = 0; // Posicion dentro de aux
        
        for(int i = 0; i < fs; i++){
            for(int j = 0; j < cs; j++){
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
     * @return true si la cantidad coincide, false si no
     */
    public static boolean esValido(int fs, int cs, int k, int[] can){
        int sum = 0;        
        for(int a : can) sum += a;        
        return (sum == fs*cs*k);        
    }
    
}
