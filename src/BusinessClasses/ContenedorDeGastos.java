package BusinessClasses;

import Interfaces.GastoOperations;

import java.util.*;

import java.util.stream.Collectors;

public class ContenedorDeGastos implements GastoOperations {

    private List<GastoReal> gastosList = new ArrayList<>();
    Map<String, Integer> categoriasMap = new HashMap<>();


    public ContenedorDeGastos(){

    }
    public ContenedorDeGastos(List<GastoReal> gastosList) {
        this.gastosList = gastosList;
    }

    public List<GastoReal> getGastosList() {
        return gastosList;
    }

    public void setGastosList(List<GastoReal> gastosList) {
        this.gastosList = gastosList;
    }

    //adicionando un nuevo gasto a la lista de gastos

    @Override
    public void addGasto(GastoReal gasto) {
        String catName = gasto.getCategoria().getNombre();
        /*Set<String> categoriasKeySet = this.categoriasMap.keySet(); //obteniendo una lista con las categorias


        boolean existeGastoConEsaCategoria = categoriasKeySet.stream().anyMatch( e -> Objects.equals(e, catName));*/

    List<Integer> cantidadList = this.categoriasMap.entrySet().stream()
                .filter(e -> e.getKey().equals(catName))
                .map(Map.Entry::getValue)
                 .collect(Collectors.toList());
        Integer cantActual = cantidadList.get(0);

        if(cantActual>0){
            this.categoriasMap.put(catName, cantActual++);
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
