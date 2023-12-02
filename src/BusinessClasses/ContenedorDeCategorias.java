package BusinessClasses;

import java.util.*;
import java.util.stream.Collectors;

public class ContenedorDeCategorias {
    List<Categoria> categoryList = new ArrayList<>();


    public ContenedorDeCategorias() {

    }
    public ContenedorDeCategorias(List<Categoria> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Categoria> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Categoria> categoryList) {
        this.categoryList = categoryList;
    }
    public void addCategoria(Categoria cat) {
        this.categoryList.add(cat);
    }

    public void showCategories() {
        for (Categoria cat : this.categoryList) {
            System.out.println(cat.getId() + " " +  cat.getNombre());
        }
    }

    public Categoria getCategoryFromDescription(String descrip){
        System.out.println("mostrando la descripcion a buscar en la lista de categorias");
        System.out.println(descrip);
       /* Optional<Categoria> cat = this.categoryList.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(descrip))
                .findFirst();

        */
        Categoria categ= new Categoria();
        for(Categoria cat : this.categoryList){
            if(cat.getNombre().equalsIgnoreCase(descrip)){
               return cat;
            }
        }
        return categ;
    }

}