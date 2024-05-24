package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.Dbconnexion;
import model.dao.IprojetDao;
import model.dao.ProjetDao;
import model.entities.Projet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(urlPatterns = {"/home", "/AjouterForm", "/Ajouter", "/delete"})
//public class webServlet extends HttpServlet {
//    private IprojetDao entities;
//
//    @Override
//    public void init() throws ServletException {
//        entities = new ProjetDao();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        processRequest(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        processRequest(req, resp);
//    }
//
//    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String path = req.getServletPath();
//        try {
//            switch (path) {
//                case "/home":
//                    Home(req, resp);
//                    break;
//                case "/AjouterForm":
//                    AjouterForm(req, resp);
//                    break;
//                case "/Ajouter":
//                    Ajouter(req, resp);
//                    break;
//                case "/delete":
//                    Delete(req, resp);
//                    break;
//                default:
//                    Home(req, resp);
//                    break;
//            }
//        } catch (Exception e) {
//            throw new ServletException(e);
//        }
//    }
//
//    private void Home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Projet> projects = entities.getALLProjets();
//        req.setAttribute("model", projects);
//        req.getRequestDispatcher("/WEB-INF/Project.jsp").forward(req, resp);
//    }
//
//    private void AjouterForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/ajout_projet.jsp").forward(req, resp);
//    }
//
//    private void Ajouter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String nom = req.getParameter("nom");
//        String description = req.getParameter("description");
//        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
//        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
//        double budget = Double.parseDouble(req.getParameter("budget"));
//
//        Projet newProject = new Projet(nom, description, dateDebut, dateFin, budget);
//        entities.ajouterProjet(newProject);
//        resp.sendRedirect(req.getContextPath() + "/home");
//    }
//
//    private void Delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        int id = Integer.parseInt(req.getParameter("id"));
//        entities.supprimerProjet(id);
//        resp.sendRedirect(req.getContextPath() + "/home");
//    }
//}





@WebServlet("/projects")
public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Projet> listProjects = getALLProjets();
        request.setAttribute("listProjects", listProjects);
        request.getRequestDispatcher("Project.jsp").forward(request, response);
    }

    private List<Projet> getALLProjets() {
        // Your existing method to fetch all projects
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
}
