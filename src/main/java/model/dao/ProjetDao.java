package model.dao;

import model.entities.Projet;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ProjetDao implements IprojetDao{
    @Override
    public List<Projet> getALLProjets() {
        Connection cn = Dbconnexion.getConnection();
        PreparedStatement ps;
        List<Projet> listProjects = new ArrayList<Projet>();
        try {
            ps = cn.prepareStatement("SELECT * FROM Project ");
           ResultSet rs = ps.executeQuery();

           while (rs.next()) {
               Projet p = new Projet();
               p.setId(rs.getInt("id_project"));
               p.setNom(rs.getString("nom"));
               p.setDescription(rs.getString("description"));
               p.setBudget(rs.getDouble("budget"));
               p.setDateDebut(rs.getDate("dateDebut"));
               p.setDateFin(rs.getDate("dateFin"));
               listProjects.add(p);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listProjects;
    }

    @Override
    public List<Projet> getProjetsParMc(String mc) {
        Connection cn = Dbconnexion.getConnection();
        PreparedStatement ps;
        List<Projet> listProjects = new ArrayList<Projet>();
        try {
            ps = cn.prepareStatement("SELECT * FROM Projets WHERE nom LIKE ? ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Projet p = new Projet();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDescription(rs.getString("description"));
                p.setBudget(rs.getDouble("budget"));
                p.setDateDebut(rs.getDate("dateDebut"));
                p.setDateFin(rs.getDate("dateFin"));
                listProjects.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listProjects;
    }

    @Override
    public Projet ajouterProjet(Projet p) {
        Connection cn = Dbconnexion.getConnection();
        PreparedStatement ps;
        try {
            ps = cn.prepareStatement("INSERT INTO project (nom,description,dateDebut,dateFin,budget)VALUES(?,?,?,?,?)");
            ps.setString(1, p.getNom());
            ps.setString(2, p.getDescription());

            ps.setDate(3, new java.sql.Date(p.getDateDebut().getTime()));
            ps.setDate(4, new java.sql.Date(p.getDateFin().getTime()));
            ps.setDouble(5, p.getBudget());
            ps.executeUpdate();
            PreparedStatement ps2 = cn.prepareStatement("SELECT MAX(id_project) FROM Project");
            ResultSet rs=ps2.executeQuery();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return p ;
    }

    @Override
    public void supprimerProjet(int id) {
        Connection cn = Dbconnexion.getConnection();
        PreparedStatement ps;
        try {
            ps = cn.prepareStatement("DELLETE FROM Project WHERE id_project=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public void modifierProjet(Projet p) {

    }
}
