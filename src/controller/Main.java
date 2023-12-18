package controller;

import config.JdbcConfiguration;
import dao.CategoriaDao;
import dao.CategoriaDaoImplH2;
import dao.GastoDao;

import dao.GastoDaoImplH2;
import dao.dto.CategoriaDto;
import dao.dto.GastoDto;
import entities.Categoria;
import entities.ContenedorDeCategorias;
import entities.ContenedorDeGastos;
import entities.Gasto;
import exceptions.DAOException;
import exceptions.InvalidGastoMontoException;
import interfaces.GastoMontoValidation;
import interfaces.GastoMontoValidationImpl;
import interfaces.GastoOperations;
import interfaces.GastoOperationsImpl;
import utiles.Helper;

import java.util.Scanner;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Main {
//ArrayList, LinkedList

    //variable global fuera del metodo principal
public static int counterGastos = 1;
    public static void main(String[] args) throws InvalidGastoMontoException {

        System.out.println("Comenzando mi proyecto Gestor de gastos!");

       // Helper miHelper = new Helper();
        Scanner scanner = new Scanner(System.in);


        //se llama desde la interfaz y se instancia la implementacion
        GastoMontoValidation gastoMontoValidation = new GastoMontoValidationImpl();
        GastoOperations gastoOperations = new GastoOperationsImpl();

        // Configurar los parámetros de conexión

       //voy a usar un try catch con recurso
        try(Connection con = JdbcConfiguration.getDBConnection()){ //genero una clase de constructor-mejor performance

            //creando los objetos DaoImpl de H2 para realizar operaciones en la BD de H2
            GastoDao gastoDao = new GastoDaoImplH2(con);
            CategoriaDao categoriaDao = new CategoriaDaoImplH2(con);

            System.out.println("Agregando una categoria...");
            String categoryName = scanner.nextLine();

            CategoriaDto categoriaDto = new CategoriaDto();
            categoriaDto.setNombre(categoryName);

            categoriaDao.insert(categoriaDto);

            //Agregando los gastos

            System.out.println("Si desea agregar un gasto escriba Si");
            String decision = scanner.nextLine();

            if(decision.equalsIgnoreCase("Si")){

                while(decision.equalsIgnoreCase("si")){
                    //creo el objeto gastoDto
                    GastoDto gastoDto = new GastoDto();

                    System.out.println("Agregue la descripción del gasto que desea agregar: ");
                    //lo convierto a minuscula y elimino los espacios, asi el valor es limpio y mas robusto
                    String descrip = scanner.nextLine().toLowerCase().trim();
                    gastoDto.setDescripcion(descrip);



                    System.out.print("Agregue el valor del gasto: ");
                    double monto = scanner.nextDouble();
                    //llamada a validar monto de la clase Helper
                    // miHelper.validarMonto(monto);


                    //aqui uso el obj de la clase que implementa la interfaz seria como un helper
                    if(!gastoMontoValidation.nonValidMonto(monto)){
                        System.out.println("El valor del gasto es válido");
                    }
                    gastoDto.setValor(monto);


                    scanner.nextLine(); //vaciando el buffer de entrada despues de la lectura del valor entero
                    // y antes de la lectura del valor de tipo String


                    System.out.print("Agregue la fecha: (dd/mm/aaaa) ");
                    String fechaActual =  scanner.nextLine().toLowerCase().trim();
                    gastoDto.setFecha(fechaActual);

                    System.out.println("Agregue la categoria del gasto que desea agregar: ");
                    //aqui podri mostrar todas las categorias de la BD
                    String categoriaName = scanner.nextLine();

                    //a partir de lo q selecciona el usuario obtengo el id de la cat
                    Integer catId = categoriaDao.getCategoryByName(categoriaName).getId();
                    System.out.println("id de la cat es : " + catId);
                    //mostrar si no esta la cat insertada

                    //la asigno al gastoDto
                    gastoDto.setCategoriaId(catId);//de momento asigno 1


                    //creo el gasto a partir de la entrada de los usuarios y lo agrego a la bd
                    gastoDao.insert(gastoDto);

                    System.out.println("Gasto inserido con exito!!");
                    System.out.println("Si desea agregar un nuevo gasto escriba Si");
                    decision = scanner.nextLine();
                }



                /*System.out.println(" Ahora mostrando la lista usando la interfaz");
                gastoOperations.calculateTotalGastos(contenedorGastos);*/



            }else{
                System.out.println("Gracias, será en otra ocasión");
            }


            //Creando el objeto Dto para guardar los datos del usuario

           /* GastoDto gastoDto = new GastoDto();
            gastoDto.setDescripcion("Viaje a Molly Park de Maringa");
            gastoDto.setFecha("04/08/2024");
            gastoDto.setCategoria(new Categoria(1,"transportacion"));
            gastoDto.setValor(5000);*/

          /*  GastoDto gastoDto = new GastoDto();
            gastoDto.setId(2);
            gastoDto.setDescripcion("Comida de fin de ano");
            gastoDto.setFecha("24/12/2023");
           // gastoDto.setCategoria(new Categoria(2,"comida"));
            gastoDto.setValor(600);

          //  gastoDao.insertar(gastoDto);
            gastoDao.update(gastoDto);*/

            //gastoDao.delete(3);

            System.out.println("Mostrando todos los gastos registrados");

            List<GastoDto> listaGastosDto = gastoDao.getAll();
            System.out.println("Lista de gastos en la Base de Datos:");
            for(GastoDto gastoDto1 : listaGastosDto){
                System.out.println("Id: " + gastoDto1.getId());
                System.out.println("Descripcion: " + gastoDto1.getDescripcion());
                System.out.println("Valor del gasto: " + gastoDto1.getValor());
                System.out.println("Realizado: " + gastoDto1.getFecha());
                System.out.println("-----------------------------------------");
            }


        }
        catch(SQLException ex)//InputMismatchException
        {
            ex.printStackTrace();
            System.out.println("Se ha producido un error inesperado: " + ex.getMessage());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        } finally{
            System.out.println("Gracias por participar de Mi 'Gerenciador de Gastos'. Hecho por Meybis Cruz Rodriguez");
        }
    }
}