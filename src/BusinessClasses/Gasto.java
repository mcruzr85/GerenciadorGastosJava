package BusinessClasses;
import java.util.Date;

public class Gasto {
    //incrementa el contador cada vez que se crea una instancia de GastoReal
    private static Integer contador = 0;
    private Integer id;
    private Categoria categoria;
    private double valor;
    private String fecha;
    private String descripcion;//el profe no tiene esto



    //incrementa el contador cada vez que se crea una instancia de Gasto
    Gasto(){
        contador++;
    }



    public Gasto( Categoria categoria,String descripcion, double valor,  String fecha) {
        contador++;
        this.id = contador;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static int getContador() {
        return contador;
    }



    public double getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getGastoDescripcion(){
        return getDescripcion() + " por un valor de " + getValor() + " realizado " + getFecha();
    }
}
