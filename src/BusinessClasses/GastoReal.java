package BusinessClasses;

import java.util.Date;


public class GastoReal extends Gasto{
    private static int contador = 0;
    private int id;

    //incrementa el contador cada vez que se crea una instancia de GastoReal
    GastoReal(){
        contador++;
    }
    public GastoReal(Categoria categoria, String descripcion, double valor, Date fecha){
        super( categoria, descripcion, valor, fecha);
        contador++;
        this.id = contador;
    }

    public static int getContador() {
        return contador;
    }

    public String getGastoDescripcion(){
        return getDescripcion() + " por un valor de " + getValor() + " realizado " + getFecha();
    }
}
