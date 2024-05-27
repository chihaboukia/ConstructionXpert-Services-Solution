package db;

import dao.IProjetImpl;
import db.Dbconnexion;
import metier.Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDbConnexion {

    public static void main(String[] args) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = Dbconnexion.getConnection();
//            if (connection != null) {
//                String insertSQL = "INSERT INTO Project (nom, description, dateDebut, dateFin, budget) VALUES (?, ?, ?, ?, ?)";
//                preparedStatement = connection.prepareStatement(insertSQL);
//                preparedStatement.setString(1, "Test Project");
//                preparedStatement.setString(2, "This is a test project");
//                preparedStatement.setDate(3, java.sql.Date.valueOf("2024-01-01"));
//                preparedStatement.setDate(4, java.sql.Date.valueOf("2024-12-31"));
//                preparedStatement.setDouble(5, 100000.00);
//
//                int rowsInserted = preparedStatement.executeUpdate();
//                if (rowsInserted > 0) {
//                    System.out.println("A new project was inserted successfully!");
//                }
//            } else {
//                System.out.println("Failed to make connection!");
//            }
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
        IProjetImpl dao = new IProjetImpl();
//        Date date = new Date(2024,06,20);
//        Project p = dao.saveProject(new Project("test1","this is a description",date,date,10000));
        List<Project> projects = dao.afficherProject();
        for (Project p: projects){
            System.out.println(p.toString());
        }
    }
}
