package BusinessClasses;

public class Categoria {
    private static int contadorCategoria = 0;
    private int id;
    private String nombre;

    public Categoria() {
        contadorCategoria++;
    }

    public Categoria(String nombre) {
        contadorCategoria++;
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
}
