package controller;

import entities.Categoria;
import entities.ContenedorDeCategorias;
import entities.ContenedorDeGastos;
import entities.Gasto;
import exceptions.InvalidGastoMontoException;
import interfaces.GastoMontoValidation;
import interfaces.GastoMontoValidationImpl;
import interfaces.GastoOperations;
import interfaces.GastoOperationsImpl;
import utiles.Helper;

import java.util.Scanner;

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

       // Helper miHelper = new Helper();
        Scanner scanner = new Scanner(System.in);

        //se llama desde la interfaz y se instancia la implementacion
        GastoMontoValidation gastoMontoValidation = new GastoMontoValidationImpl();
        GastoOperations gastoOperations = new GastoOperationsImpl();

        // Configurar los parámetros de conexión

        String url = "jdbc:h2:file:D:\\h2\\h2dbmey\\expenses"; // URL de conexión a la base de datos H2
     //   String url1 ="jdbc:h2:tcp://localhost/server~/test/gestion_gastosdb";
        String username = "admin"; // Nombre de usuario de la base de datos
        String password = "123"; // Contraseña de la base de datos

        try{


            System.out.println("Comenzando mi proyecto Gestor de gastos!");

          //creando el scanner para leer entrada del usuario


         /*   System.out.print("Agregue una categoría de gastos: ");
            String categoryName = scanner.nextLine();

            //creo el ArrayLista para guardar las categorias
            ContenedorDeCategorias contenedorCat = new ContenedorDeCategorias();

            //agregando categorias hasta que el usuario escriba "X"
            while(!(categoryName.equals("X") || categoryName.equals("x"))){
                    contenedorCat.addCategoria(new Categoria(categoryName));
                    System.out.println("Categoria " + categoryName + " creada con éxito. Agregue un nueva categoria o escriba X para terminar" );
                    categoryName = scanner.nextLine();
            }


            System.out.println("Lista de categorias creada con exito: " );

            //mostrando todas las categorias adicionadas
            contenedorCat.showCategories();
*/
            //Agregando los gastos

            System.out.println("Si desea agregar un gasto escriba Si");
            String decision = scanner.nextLine();

            if(decision.equalsIgnoreCase("Si")){
                ContenedorDeGastos contenedorGastos = new ContenedorDeGastos();

                while(decision.equalsIgnoreCase("si")){

                    System.out.println("Agregue la descripción del gasto que desea agregar: ");
                    //lo convierto a minuscula y elimino los espacios, asi el valor es limpio y mas robusto
                    String descrip = scanner.nextLine().toLowerCase().trim();



                    System.out.print("Agregue el valor del gasto: ");
                    double monto = scanner.nextDouble();
                    //llamada a validar monto de la clase Helper
                   // miHelper.validarMonto(monto);


                    //aqui uso el obj de la clase que implementa la interfaz seria como un helper
                    if(!gastoMontoValidation.nonValidMonto(monto)){
                        System.out.println("El valor del gasto es válido");
                    }


                    scanner.nextLine(); //vaciando el buffer de entrada despues de la lectura del valor entero
                    // y antes de la lectura del valor de tipo String


                    System.out.print("Agregue la fecha: (dd/mm/aaaa) ");
                    String fechaActual =  scanner.nextLine().toLowerCase().trim();

                    System.out.println("Agregue la categoria del gasto que desea agregar: ");
                    String categoriaGastoActual = scanner.nextLine().toLowerCase().trim();

                    //creando la  categoria
                    Categoria cat = new Categoria(categoriaGastoActual);

                //retorna una categoria dada una descripcion
                /* Categoria cat = contenedorCat.getCategoryFromDescription(categoriaGastoActual);
                   if(cat.getNombre().equalsIgnoreCase(categoriaGastoActual)){*/

                    //creo el gasto a partir de la entrada de los usuarios y lo agrego a la lista
                    contenedorGastos.addGasto(new Gasto(cat, descrip, monto, fechaActual));

                    System.out.println("Gasto nro: " + Gasto.getContador() + " " + descrip + " creado con éxito");


                    System.out.println("Si desea agregar un nuevo gasto escriba Si");
                    decision = scanner.nextLine();
                }

                //finalmente muestro todos los gastos creados
                contenedorGastos.showGastos();
                contenedorGastos.showCategoriasCantidades();

                System.out.println(" Ahora mostrando la lista usando la interfaz");
                gastoOperations.calculateTotalGastos(contenedorGastos);



            }else{
                System.out.println("Gracias, será en otra ocasión");
            }

            //***************************
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(url, username, password);

            // Crear una declaración SQL
            PreparedStatement ps = connection.prepareStatement("select * from expense");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString(2) + " " + rs.getString(3));
            }




           /* // Crear la tabla
            String createTableQuer
            y = "CREATE TABLE IF NOT EXISTS meybis (id INT PRIMARY KEY, nombre VARCHAR(50))";
            statement.executeUpdate(createTableQuery);*/

            // Insertar registros
            //String insertQuery = "INSERT INTO meybis VALUES (5, 'John Doe'), (6, 'Jane Smith')";
          //  String insertQuery = "INSERT INTO gestion_gastosdb.categories (nombre) VALUES ('aaaaaaaaaa'),('bbbbbbbbbb')";
        //    statement.executeUpdate(insertQuery);

            // Cerrar la conexión
            ps.close();
            connection.close();

            System.out.println("Registros insertados con éxito en la tabla 'usuarios'.");

            //*******************

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