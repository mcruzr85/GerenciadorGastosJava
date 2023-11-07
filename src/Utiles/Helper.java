package Utiles;

import Exceptions.MinorQueZeroException;

public class Helper {
    public Helper(){
    }

    public void validarMonto(double valor) throws MinorQueZeroException{
        if(valor < 0) {
            throw new MinorQueZeroException( valor, "Es imposible salvar un gasto con un valor menor o igual a cero, valor introducido: " + valor );
        }
    }//esto es una prueba para github
}
