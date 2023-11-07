package BusinessClasses;
import java.util.Date;

public abstract class Gasto {


    //private int id;
    private String descripcion;
    private double valor;
    private Date fecha;

    private Categoria categoria;

    public Gasto() {
    }



    public Gasto( Categoria categoria,String descripcion, double valor,  Date fecha) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public abstract String getGastoDescripcion();
}
