package Interfaces;

import BusinessClasses.GastoReal;

public interface GastoOperations {
    public abstract void addGasto(GastoReal gasto);
    public abstract void showGastos();

    public default void checkAmountGasto() {

    }

    public abstract void checkDateGasto();
    public abstract void showGastosFromCategory();

}
