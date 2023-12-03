package interfaces;

import exceptions.InvalidGastoMontoException;

@FunctionalInterface
public interface GastoMontoValidation {
    //interfaz funcional que sea un validador
    //la logica de validacion va a una interfaz separada


 boolean validateMonto(double monto) throws InvalidGastoMontoException;


}

