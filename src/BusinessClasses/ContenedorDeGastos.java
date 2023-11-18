package BusinessClasses;

import Interfaces.GastoOperations;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        System.out.println("dentro de addGasto obtuve catName: " + catName);
        /*Set<String> categoriasKeySet = this.categoriasMap.keySet(); //obteniendo una lista con las categorias


        boolean existeGastoConEsaCategoria = categoriasKeySet.stream().anyMatch( e -> Objects.equals(e, catName));*/
//Podemos usar el método keySet() para obtener un conjunto de claves
        HashMap<String, Integer>  map = this.categoriasMap.entrySet().stream()
                .filter(e -> e.getKey().equals(catName))
                .findFirst().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if(map.isEmpty()){
            this.categoriasMap.put(catName, cantidadCat++);
            System.out.println("ahora hay " + cantidadCat + " gastos en esa categoria");
        }



               /* .map(Map.Entry::getValue)
                 .toList();*/
        System.out.println("mostrando cant de gastos de la categoria, antes de adicionar el gasto actual es: " + cantidadCat);

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

    public void showCategoriasCantidades(){
        System.out.println("mostrando el map actual de categorias y cantidades");
         this.categoriasMap.entrySet().stream()
                .forEach(e -> System.out.println("la cat " + e.getKey() + " tiene " + e.getValue() + "gastos"));
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
