import BusinessClasses.Categoria;
import BusinessClasses.ContenedorDeCategorias;
import BusinessClasses.ContenedorDeGastos;
import BusinessClasses.GastoReal;
import Exceptions.MinorQueZeroException;
import Utiles.Helper;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Main {
//ArrayList, LinkedList


    public static void main(String[] args) {

        Helper miHelper = new Helper();

        try{


            System.out.println("Comenzando mi proyecto Gestor de gastos!");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Agregue una categoría de gastos");
            String valor = scanner.nextLine();
            ContenedorDeCategorias contenedorCat = new ContenedorDeCategorias();

            while(!valor.equals("X")){
                    contenedorCat.addCategoria(new Categoria(valor));
                    //Categoria nuevaCategoria = new Categoria(valor);
                    System.out.println("Categoria " + valor + " creada con éxito. Agregue un nueva categoria o escriba X para terminar" );
                    valor = scanner.nextLine();
            }


            System.out.println("Lista de categorias creada con exito: " );
            contenedorCat.showCategories();

            //GastoReal(Categoria categoria,String descripcion, int valor,  String fecha){

            System.out.println("Agregue un gasto");
            String descrip = scanner.nextLine();

            System.out.println("Agregue el valor del gasto");
            double monto = scanner.nextDouble();

            miHelper.validarMonto( monto );

            Date fechaActual = new Date();

            GastoReal miGasto = new GastoReal(contenedorCat.getCategoryList().get(0), descrip, monto, fechaActual);

            System.out.println("Gasto creado con exito: " + miGasto.getGastoDescripcion());
            System.out.println("Gasto nro: " + GastoReal.getContador());

            ContenedorDeGastos contenedor = new ContenedorDeGastos();
            contenedor.addGasto(miGasto);
            contenedor.showGastos();

        }
        catch(Exception ex)//InputMismatchException
        {
            System.out.println("Se ha producido un error inesperado: " + ex.getMessage());

        }
        finally{
            System.out.println("Gracias por participar de Mi Gerenciador de Gastos. Meybis Cruz Rodriguez");
        }




    }
}