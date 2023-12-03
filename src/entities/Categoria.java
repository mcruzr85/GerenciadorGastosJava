package entities;

public class Categoria {
    private static int contadorCategoria = 0;
    private int id;
    private String nombre;

    public Categoria() {
        contadorCategoria+=contadorCategoria;
        this.id = contadorCategoria;
    }

    public Categoria(String nombre) {
        contadorCategoria+=contadorCategoria;
        this.id = contadorCategoria;
        this.nombre = nombre;
    }



    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDataCategoria(){
        return "Categoria: " + this.id + " " +  this.nombre;
    }
}
