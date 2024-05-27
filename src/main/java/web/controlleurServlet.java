package web;

import dao.IProjet;
import dao.IProjetImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Project;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class controlleurServlet extends HttpServlet {
    private IProjet metier;

    @Override
    public void init() throws ServletException {
        metier = new IProjetImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        try {
            switch (path) {
                case "/home":
                    Home(req, resp);
                    break;
                case "/AjouterForm":
                    AjouterForm(req, resp);
                    break;
                case "/Ajouter":
                    Ajouter(req, resp);
                    break;
                case "/delete":
                    Delete(req, resp);
                    break;
                case "/updateForm":
                    UpdateForm(req, resp);
                    break;
                case "/Modifier":
                    Update(req, resp);
                    break;
                default:
                    Home(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void Home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projects = metier.afficherProject();
        req.setAttribute("model", projects);
        req.getRequestDispatcher("/WEB-INF/Project.jsp").forward(req, resp);
    }

    private void AjouterForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/ajout_projet.jsp").forward(req, resp);
    }

    private void Ajouter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        double budget = Double.parseDouble(req.getParameter("budget"));

        Project newProject = new Project(nom, description, dateDebut, dateFin, budget);
        metier.saveProject(newProject);
        resp.sendRedirect(req.getContextPath() + "/home");
    }

    private void Delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        metier.deleteProject(id);
        resp.sendRedirect(req.getContextPath() + "/home");
    }
    private void UpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Project project = metier.getProject(id);
        req.setAttribute("project", project);
        req.getRequestDispatcher("/WEB-INF/modifier_projet.jsp").forward(req, resp);
    }

    private void Update(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        double budget = Double.parseDouble(req.getParameter("budget"));

        Project updatedProject = new Project(nom, description, dateDebut, dateFin, budget);
        metier.updateProject(id,updatedProject);
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}



