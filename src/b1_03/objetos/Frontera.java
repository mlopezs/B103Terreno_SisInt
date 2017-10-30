/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b1_03.objetos;

/**
 *
 * @author alf
 */
public interface Frontera {
    public void crearFrontera();
    public void insertar(Nodo b);
    public Object eliminar(Nodo b);
    public boolean esVacia();
}
