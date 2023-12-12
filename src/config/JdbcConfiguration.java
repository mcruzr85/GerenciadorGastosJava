package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfiguration {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/productdbname";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "123";

    public static Connection getDBConnection(){
        Connection connection = null;
        try{
            Class.forName(DB_DRIVER); //para inicializar el driver
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

        }catch(ClassNotFoundException | SQLException e){
            //ClassNotFoundException por si cuando tratamos de inicializar el driver no se encuentra la clases apropiada
            e.printStackTrace();
        }

        return connection;
    }


}
