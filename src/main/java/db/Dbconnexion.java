package db;
import jakarta.servlet.ServletException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Dbconnexion  {

    private static final String HOST = "localhost";
    private static final int PORT = 5432;
    private static final String DB_NAME = "ConstructionXpert";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "000014RM";







    public static Connection getConnection()
    {
        Connection connection =null ;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        try {
            connection = DriverManager.getConnection(String.format("jdbc:postgresql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD);
        } catch (SQLException se)
        {
            se.printStackTrace();

        }
        return connection;
    }

}
