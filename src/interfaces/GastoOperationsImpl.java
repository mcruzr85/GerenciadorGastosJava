package interfaces;

import entities.Gasto;
import java.util.List;

public class GastoOperationsImpl implements GastoOperations{
    @Override
    public double calculateGasto(Gasto gasto) {
        return gasto.getValor();
    }

    @Override
    public double calculateTotalGastos(List<Gasto> gastos) {
        double total = 0;
           total =  (double) gastos.stream().count();
        return total;
    }
}
