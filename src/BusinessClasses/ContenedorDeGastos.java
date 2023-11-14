package BusinessClasses;

import Interfaces.GastoOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContenedorDeGastos implements GastoOperations {

    private List<GastoReal> gastosList = new ArrayList<>();
    Map<String, Integer> contadorDeCategoriasPorNombre = new HashMap<>();

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
      Map<String, Integer> clavequebusco;
        clavequebusco = contadorDeCategoriasPorNombre.stream()
                .filter(elemento -> elemento.getKey() == catName)
                .collect(Collectors.toMap(<String, Integer>));

        this.gastosList.add(gasto);


        this.contadorDeCategoriasPorNombre.put( )
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
