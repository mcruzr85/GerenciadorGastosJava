package BusinessClasses;

import Interfaces.GastoOperations;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContenedorDeGastos implements GastoOperations {

    private List<GastoReal> gastosList = new ArrayList<>();
    private Map<String, Integer> categoriasMap = new HashMap<>();


    public ContenedorDeGastos(){

    }
    public ContenedorDeGastos(List<GastoReal> gastosList, Map<String, Integer> categoriasMap) {
        this.gastosList = gastosList;
        this.categoriasMap = categoriasMap;
    }

    public List<GastoReal> getGastosList() {
        return gastosList;
    }

    public void setGastosList(List<GastoReal> gastosList) {
        this.gastosList = gastosList;
    }

    //adicionando un nuevo gasto a la lista de gastos

    public Map<String, Integer> getCategoriasMap() {
        return categoriasMap;
    }

    public void setCategoriasMap(Map<String, Integer> categoriasMap) {
        this.categoriasMap = categoriasMap;
    }

    @Override
    public void addGasto(GastoReal gasto) {
        System.out.println("dentro de addGasto inicio");
        String catName = gasto.getCategoria().getNombre();
        System.out.println("dentro de addGasto obtuve catName" + catName);
        /*Set<String> categoriasKeySet = this.categoriasMap.keySet(); //obteniendo una lista con las categorias


        boolean existeGastoConEsaCategoria = categoriasKeySet.stream().anyMatch( e -> Objects.equals(e, catName));*/

        int cantidadCat;
        cantidadCat = this.categoriasMap.entrySet().stream()
                .filter(e -> e.getKey().equals(catName))
                .mapToInt(e -> e.getValue()).sum();


               /* .map(Map.Entry::getValue)
                 .toList();*/
        System.out.println("mostrando cantCat" + cantidadCat);

        if(cantidadCat != 0){
            System.out.println("dentro de adicionar gasto" + " cantidad de elementos en esa categoria de gasto es " + cantidadCat);
            this.categoriasMap.put(catName, cantidadCat++);
        }else{
            this.categoriasMap.put(catName,1);
        }


        this.gastosList.add(gasto);
    }

    //para mostrar todos los gastos de la lista de gastos
    @Override
    public void showGastos() {
        System.out.println("Relatorio de gastos actuales");
        for(GastoReal gasto: this.gastosList){
            System.out.println("Gasto: " + gasto.getGastoDescripcion());
        }
    }

    @Override
    public void checkAmountGasto() {

    }

    @Override
    public void checkDateGasto() {

    }

     @Override
    public void showGastosFromCategory() {

    }
}
