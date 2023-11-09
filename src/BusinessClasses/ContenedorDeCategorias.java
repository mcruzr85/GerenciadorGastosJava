package BusinessClasses;

import java.util.ArrayList;
import java.util.List;

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

}