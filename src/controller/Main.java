package controller;

import dao.GastoDao;

import dao.GastoDaoImplH2;
import dao.dto.GastoDto;
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

       // Helper miHelper = new Helper();
        Scanner scanner = new Scanner(System.in);

        //se llama desde la interfaz y se instancia la implementacion
        GastoMontoValidation gastoMontoValidation = new GastoMontoValidationImpl();
        GastoOperations gastoOperations = new GastoOperationsImpl();

        // Configurar los parámetros de conexión


        try{
            System.out.println("Comenzando mi proyecto Gestor de gastos!");

            //creando el objeto DaoImpl de H2 para realizar operaciones en la BD de H2

            GastoDao gastoDao = new GastoDaoImplH2();

            //Creando el objeto Dto para guardar los datos del usuario

            GastoDto gastoDto = new GastoDto();
            gastoDto.setDescripcion("Viaje a Molly Park de Maringa");
            gastoDto.setFecha("04/08/2024");
            gastoDto.setCategoria(new Categoria(1,"transportacion"));
            gastoDto.setValor(5000);

          //  gastoDao.insertar(gastoDto);

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
        catch(Exception ex)//InputMismatchException
        {
            System.out.println("Se ha producido un error inesperado: " + ex.getMessage());

        }
        finally{
            System.out.println("Gracias por participar de Mi 'Gerenciador de Gastos'. Hecho por Meybis Cruz Rodriguez");
        }
    }
}