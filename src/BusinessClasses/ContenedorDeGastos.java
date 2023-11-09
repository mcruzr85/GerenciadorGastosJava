package BusinessClasses;

import Interfaces.GastoOperations;

import java.util.ArrayList;
import java.util.List;

public class ContenedorDeGastos implements GastoOperations {

    private List<GastoReal> gastosList = new ArrayList<>();

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
        this.gastosList.add(gasto);
    }

    //para mostrar todos los gastos de la lista de gastos
    @Override
    public void showGastos() {
        for(GastoReal gasto: this.gastosList){
            System.out.println("metodo showGastos, mostrando los gastos adicionados");
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
