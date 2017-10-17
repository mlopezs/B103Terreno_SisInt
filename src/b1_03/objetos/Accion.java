package b1_03.objetos;

public class Accion {
    
    private int[] dstr;
    private int[] movs;

    public Accion(int[] dstr, int[] movs) {
        this.dstr = dstr;
        this.movs = movs;
    }

    @Override
    public String toString() {
        return "Accion{" + "dstr=" + dstr + ", movs=" + movs + '}';
    }
}
