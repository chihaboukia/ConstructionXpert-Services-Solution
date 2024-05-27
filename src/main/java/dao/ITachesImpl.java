package dao;

import db.Dbconnexion;
import metier.Taches;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ITachesImpl implements ITaches {

    @Override
    public Taches saveTaches(Taches t) throws SQLException {
        String insertSQL = "INSERT INTO taches (id_project, t_name, t_Description, t_datedebut, tdatefin, status) VALUES (?,?,?,?,?,?)";
        String selectSQL = "SELECT MAX(id_tach) AS MAX_ID FROM taches";

        try (Connection connection = Dbconnexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertSQL);
             PreparedStatement ps2 = connection.prepareStatement(selectSQL)) {

            ps.setInt(1, t.getId_project());
            ps.setString(2, t.getT_name());
            ps.setString(3, t.getT_Description());
            ps.setDate(4, t.getT_datedebut());
            ps.setDate(5, t.getTdatefin());
            ps.setString(6, t.getStatus());
            ps.executeUpdate();

            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                t.setId_tach(rs.getInt("MAX_ID"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error saving task: " + e.getMessage());
        }
        return t;
    }

    @Override
    public List<Taches> afficherTaches(int projectId) throws SQLException {
        List<Taches> taches = new ArrayList<>();
        String selectSQL = "SELECT * FROM taches WHERE id_project = ?"; // Add WHERE clause to filter tasks by project ID

        try (Connection connection = Dbconnexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectSQL)) {

            ps.setInt(1, projectId); // Set the project ID parameter

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Taches tache = new Taches(
                            rs.getInt("id_tach"),
                            rs.getString("status"),
                            rs.getDate("tdatefin"),
                            rs.getDate("t_datedebut"),
                            rs.getString("t_Description"),
                            rs.getString("t_name"),
                            rs.getInt("id_project")
                    );
                    taches.add(tache);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching tasks: " + e.getMessage());
        }
        return taches;
    }


    @Override
    public Taches getTaches(int id) throws SQLException {
        Taches tache = null;
        String selectSQL = "SELECT * FROM taches WHERE id_tach = ?";

        try (Connection connection = Dbconnexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectSQL)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tache = new Taches(
                        rs.getInt("id_tach"),
                        rs.getString("status"),
                        rs.getDate("tdatefin"),
                        rs.getDate("t_datedebut"),
                        rs.getString("t_Description"),
                        rs.getString("t_name"),
                        rs.getInt("id_project")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching task: " + e.getMessage());
        }
        return tache;
    }

    @Override
    public Taches updateTaches( Taches t) throws SQLException {
        String updateSQL = "UPDATE taches SET id_project = ?, t_name = ?, t_Description = ?, t_datedebut = ?, tdatefin = ?, status = ? WHERE id_tach = ?";

        try (Connection connection = Dbconnexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateSQL)) {

            ps.setInt(1, t.getId_project());
            ps.setString(2, t.getT_name());
            ps.setString(3, t.getT_Description());
            ps.setDate(4, t.getT_datedebut());
            ps.setDate(5, t.getTdatefin());
            ps.setString(6, t.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating task: " + e.getMessage());
        }
        return t;
    }

    @Override
    public void deleteTaches(int id_tach) throws SQLException {
        String deleteSQL = "DELETE FROM taches WHERE id_tach = ?";

        try (Connection connection = Dbconnexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteSQL)) {

            ps.setInt(1, id_tach);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting task: " + e.getMessage());
        }
    }

    @Override
    public Taches getTacheById(int id) throws SQLException {
            Taches tache = null;
            String selectSQL = "SELECT * FROM taches WHERE id_tach = ?";

            try (Connection connection = Dbconnexion.getConnection();
                 PreparedStatement ps = connection.prepareStatement(selectSQL)) {

                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tache = new Taches(
                                rs.getInt("id_tach"),
                                rs.getString("status"),
                                rs.getDate("tdatefin"),
                                rs.getDate("t_datedebut"),
                                rs.getString("t_Description"),
                                rs.getString("t_name"),
                                rs.getInt("id_project")
                        );
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Error fetching task: " + e.getMessage());
            }
            return tache;
        }
    }



