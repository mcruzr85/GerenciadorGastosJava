package interfaces;

import dao.dto.GastoDto;
import entities.Gasto;
import java.util.List;

public class GastoOperationsImpl implements GastoOperations{
    @Override
    public double calculateGasto(Gasto gasto) {
        return gasto.getValor();
    }

    @Override
    public double calculateTotalGastos(List<GastoDto> gastos) {
        double total = 0;
         for(GastoDto gastoDto : gastos){
             total += gastoDto.getValor();
         }
         return total;
    }
}
