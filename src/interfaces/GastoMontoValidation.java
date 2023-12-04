package interfaces;

import exceptions.InvalidGastoMontoException;

@FunctionalInterface
public interface GastoMontoValidation {
 boolean nonValidMonto(double monto) throws InvalidGastoMontoException;
}


//interfaz funcional (tiene un solo metodo) que sea un validador
//la logica de validacion va a una interfaz separada
