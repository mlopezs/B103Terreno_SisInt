/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.objetos;

/**
 *
 * @author pacog
 */
public class Tractor {

    private int coord_x;
    private int coord_y;

    public Tractor(int coord_x, int coord_y) {
        this.coord_x = coord_x;
        this.coord_y = coord_y;
    }
    
    public void movimiento(int coord_x, int coord_y){
        this.coord_x = coord_x;
        this.coord_y = coord_y;
    }
    public int get_x(){
        return coord_x;
    }
    public int get_y(){
        return coord_y;
    }
}
