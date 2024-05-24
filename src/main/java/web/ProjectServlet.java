package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.IprojetDao;
import model.dao.ProjetDao;
import model.entities.Projet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

// Import statements...

@WebServlet("/projects")
public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IprojetDao entities;

    @Override
    public void init() throws ServletException {
        entities = new ProjetDao(); // Replace ProjetDao with your DAO implementation
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("addForm".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/add_project_form.jsp").forward(request, response);
        } else {
            try {
                List<Projet> listProjects = entities.getALLProjets();
                request.setAttribute("listProjects", listProjects);
                request.getRequestDispatcher("/WEB-INF/Project.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error fetching projects from the database", e);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            ajouterProjet(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/projects");
        }
    }

    private void ajouterProjet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            Date dateDebut = Date.valueOf(request.getParameter("datedebut"));
            Date dateFin = Date.valueOf(request.getParameter("datefin"));
            double budget = Double.parseDouble(request.getParameter("budget"));

            Projet newProject = new Projet(nom, description, dateDebut, dateFin, budget);
            entities.ajouterProjet(newProject);
            response.sendRedirect(request.getContextPath() + "/projects");

    }
}

