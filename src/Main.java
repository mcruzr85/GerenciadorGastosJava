import BusinessClasses.Categoria;
import BusinessClasses.ContenedorDeCategorias;
import BusinessClasses.ContenedorDeGastos;
import BusinessClasses.GastoReal;
import Exceptions.MinorQueZeroException;
import Utiles.Helper;

import java.util.Scanner;
import java.util.Date;

public class Main {
//ArrayList, LinkedList


    public static void main(String[] args) {

        Helper miHelper = new Helper();

        try{


            System.out.println("Comenzando mi proyecto Gestor de gastos!");

 //creando el scanner para leer entrada del usuario
            Scanner scanner = new Scanner(System.in);

            System.out.println("Agregue una categoría de gastos");
            String valor = scanner.nextLine();

            //creo el ArrayLista para guardar las categorias
            ContenedorDeCategorias contenedorCat = new ContenedorDeCategorias();

            //agregando categorias hasta que el usuario escriba "X"
            while(!(valor.equals("X") || valor.equals("x"))){
                    contenedorCat.addCategoria(new Categoria(valor));
                    System.out.println("Categoria " + valor + " creada con éxito. Agregue un nueva categoria o escriba X para terminar" );
                    valor = scanner.nextLine();
            }


            System.out.println("Lista de categorias creada con exito: " );

            //mostrando todas las categorias adicionadas
            contenedorCat.showCategories();

            //GastoReal(Categoria categoria,String descripcion, int valor,  String fecha){

            System.out.println("Si desea agregar un gasto escriba Si");
            String decision = scanner.nextLine();
            if(decision.equalsIgnoreCase("Si")){
                ContenedorDeGastos contenedorGastos = new ContenedorDeGastos();

                while(decision.equalsIgnoreCase("si")){

                    System.out.println("Agregue la descripción del gasto que desea agregar");
                    String descrip = scanner.nextLine();

                    System.out.println("Agregue el valor del gasto");
                    double monto = scanner.nextDouble();
                    //llamada a validar monto de la clase Helper
                    miHelper.validarMonto(monto);

                    Date fechaActual = new Date();

                    scanner.nextLine(); //vaciando el buffer de entrada despues de la lectura del valor entero
                    // y antes de la lectura del valor de tipo String

                    System.out.println("Agregue la categoria del gasto que desea agregar");
                    String categoriaGastoActual = scanner.nextLine();


                 Categoria cat = contenedorCat.getCategoryFromDescription(categoriaGastoActual);
                   if(cat.getNombre().equalsIgnoreCase(categoriaGastoActual)){
                       contenedorGastos.addGasto(new GastoReal(cat, descrip, monto, fechaActual));
                   }else{
                       System.out.println("Categoria no válida");
                   }

                    System.out.println("Gasto nro: " + GastoReal.getContador() + " " + descrip + " creado con éxito");
                    System.out.println("Si desea agregar un nuevo gasto escriba Si");

                    decision = scanner.nextLine();
                }

                //finalmente muestro todos los gastos creados
                contenedorGastos.showGastos();
                contenedorGastos.showCategoriasCantidades();
            }else{
                System.out.println("Gracias, será en otra ocasión");
            }

        }
        catch(Exception ex)//InputMismatchException
        {
            System.out.println("Se ha producido un error inesperado: " + ex.getMessage());

        }
        finally{
            System.out.println("Gracias por participar de Mi 'Gerenciador de Gastos'. Hecho por Meybis Cruz Rodriguez");
        }
    }
}