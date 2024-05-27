package dao;
import db.Dbconnexion;
import db.TestDbConnexion;
import metier.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class IProjetImpl implements IProjet{

    @Override
    public Project saveProject(Project p) {
        String insertSQL = "INSERT INTO project (nom, description, datedebut, datefin, budget) VALUES (?,?,?,?,?)";
        String selectSQL = "SELECT MAX(id_project) AS MAX_ID FROM project";

        try (Connection connection = Dbconnexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertSQL);
             PreparedStatement ps2 = connection.prepareStatement(selectSQL)) {

            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setDate(3, p.getDateDebut());
            ps.setDate(4, p.getDateFin());
            ps.setDouble(5, p.getBudget());
            ps.executeUpdate();

            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                p.setIdProject(rs.getInt("MAX_ID"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }


    @Override
    public List<Project> afficherProject() {
        List<Project> projects = new ArrayList<>();
        String selectSQL = "SELECT * FROM project";

        try (Connection connection = Dbconnexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectSQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Project project = new Project();
                project.setIdProject(rs.getInt("id_project"));
                project.setNom(rs.getString("nom"));
                project.setDescription(rs.getString("description"));
                project.setDateDebut(rs.getDate("datedebut"));
                project.setDateFin(rs.getDate("datefin"));
                project.setBudget(rs.getDouble("budget"));
                projects.add(project);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
    @Override
    public Project getProject(int id) {
        return null;
    }

    @Override
    public Project updateProject(int id,Project p) {
        String updateSQL = "UPDATE project SET nom = ?, description = ?, datedebut = ?, datefin = ?, budget = ? WHERE id_project = ?";

        try (
                Connection connection = Dbconnexion.getConnection();
                PreparedStatement ps = connection.prepareStatement(updateSQL))

        {

            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());
            ps.setDate(3, p.getDateDebut());
            ps.setDate(4, p.getDateFin());
            ps.setDouble(5, p.getBudget());
            ps.setInt(6, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void deleteProject(int projet_id) {
        String deleteSQL = "DELETE FROM project WHERE id_project = ?";

        try (Connection connection = Dbconnexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteSQL)) {

            ps.setInt(1, projet_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}