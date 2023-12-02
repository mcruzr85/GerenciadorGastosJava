package BusinessClasses;

import Interfaces.GastoOperations;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ContenedorDeGastos{

    private List<Gasto> gastosList = new ArrayList<>();
    private Map<String, Integer> categoriasMap = new HashMap<>();


    public ContenedorDeGastos(){

    }
    public ContenedorDeGastos(List<Gasto> gastosList, Map<String, Integer> categoriasMap) {
        this.gastosList = gastosList;
        this.categoriasMap = categoriasMap;
    }

    public List<Gasto> getGastosList() {
        return gastosList;
    }

    public void setGastosList(List<Gasto> gastosList) {
        this.gastosList = gastosList;
    }

    //adicionando un nuevo gasto a la lista de gastos

    public Map<String, Integer> getCategoriasMap() {
        return categoriasMap;
    }

    public void setCategoriasMap(Map<String, Integer> categoriasMap) {
        this.categoriasMap = categoriasMap;
    }


    public void addGasto(Gasto gasto) {
        System.out.println("dentro de addGasto inicio");
        String catName = gasto.getCategoria().getNombre();
        System.out.println("dentro de addGasto obtuve catName: " + catName);
        /*Set<String> categoriasKeySet = this.categoriasMap.keySet(); //obteniendo una lista con las categorias


        boolean existeGastoConEsaCategoria = categoriasKeySet.stream().anyMatch( e -> Objects.equals(e, catName));*/

        OptionalInt cantCat = this.categoriasMap.entrySet().stream()
                .filter(e -> e.getKey().equals(catName))
                .mapToInt(Map.Entry::getValue)
                .findFirst();

        if(cantCat.isPresent()){
            int cantidad = cantCat.getAsInt();
            System.out.println("cant antes de insertar es: " + cantidad );
            this.categoriasMap.put(catName, ++cantidad);
            System.out.println("ahora hay " + this.categoriasMap.get(catName) + " gastos en esa categoria");
        }
        else{
            this.categoriasMap.put(catName, 1);
            System.out.println("adicionado primer gasto a la categoria " + catName);
        }

        //System.out.println("mostrando cant de gastos de la categoria, antes de adicionar el gasto actual es: " + ca);

        this.gastosList.add(gasto);
        }



    //para mostrar todos los gastos de la lista de gastos

    public void showGastos() {
        System.out.println("Relatorio de gastos actuales");
        for(Gasto gasto: this.gastosList){
            System.out.println("Gasto: " + gasto.getGastoDescripcion());
        }
    }

    public void showCategoriasCantidades(){
        System.out.println("mostrando el map actual de categorias y cantidades");
         this.categoriasMap.entrySet().stream()
                .forEach(e -> System.out.println("la cat " + e.getKey() + " tiene " + e.getValue() + "gastos"));
    }

/*
    @Override
    public void checkAmountGasto() {

    }

    @Override
    public void checkDateGasto() {

    }

     @Override
    public void showGastosFromCategory() {

    }*/
}
