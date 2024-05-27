package web;

import dao.ITaches;
import dao.ITachesImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Taches;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

 public class servlet_tach extends HttpServlet {
    private ITaches tacheDao;

    @Override
    public void init() throws ServletException {
        tacheDao = new ITachesImpl();
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
                case "/tacheHome":
                    tacheHome(req, resp);
                    break;
                case "/viewTasks":
                    viewTasks(req, resp);
                case "/AjouterTacheForm":
                    ajouterTacheForm(req, resp);
                    break;
                case "/AjouterTache":
                    ajouterTache(req, resp);
                    break;
                case "/deleteTache":
                    deleteTache(req, resp);
                    break;
                case "/updateTacheForm":
                    updateTacheForm(req, resp);
                    break;
                case "/ModifierTache":
                    updateTache(req, resp);
                    break;
                default:
                    tacheHome(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

     private void tacheHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // Retrieve project ID from request parameter
         int projectId = Integer.parseInt(req.getParameter("projectId"));

         try {
             // Retrieve tasks for the specified project ID
             List<Taches> tasks = tacheDao.afficherTaches(projectId);

             // Set tasks as request attribute
             req.setAttribute("tasks", tasks);
         } catch (SQLException e) {
             e.printStackTrace();
             // Handle the exception properly, maybe redirect to an error page
         }

         // Forward the request to view_tasks.jsp for rendering
         req.getRequestDispatcher("/WEB-INF/view_tasks.jsp").forward(req, resp);
     }

     private void ajouterTacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward the request to the JSP page for adding a task
        req.getRequestDispatcher("/WEB-INF/ajout_tache.jsp").forward(req, resp);
    }

    private void ajouterTache(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Retrieve task details from the request parameters
        String t_name = req.getParameter("t_name");
        String t_Description = req.getParameter("t_Description");
        Date t_datedebut = Date.valueOf(req.getParameter("t_datedebut"));
        Date tdatefin = Date.valueOf(req.getParameter("tdatefin"));
        String status = req.getParameter("status");
        int projectId = Integer.parseInt(req.getParameter("projectId"));

        // Create a new task object
        Taches newTache = new Taches(0, status, tdatefin, t_datedebut, t_Description, t_name, projectId);

        try {
            // Save the task to the database
            tacheDao.saveTaches(newTache);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception properly, maybe redirect to an error page
        }

        // Redirect to the task list page for the project
        resp.sendRedirect(req.getContextPath() + "/tacheHome?projectId=" + projectId);
    }

    private void deleteTache(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Retrieve task ID from request parameter
        int taskId = Integer.parseInt(req.getParameter("taskId"));

        try {
            // Delete the task from the database
            tacheDao.deleteTaches(taskId);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception properly, maybe redirect to an error page
        }

        // Redirect to the task list page for the project
        resp.sendRedirect(req.getHeader("referer")); // Redirect back to the previous page
    }

    private void updateTacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve task ID from request parameter
        int taskId = Integer.parseInt(req.getParameter("taskId"));

        try {
            // Retrieve task details by ID
            Taches task = tacheDao.getTacheById(taskId);

            // Set task as request attribute
            req.setAttribute("task", task);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception properly, maybe redirect to an error page
        }

        // Forward the request to the JSP page for updating a task
        req.getRequestDispatcher("/WEB-INF/modifier_tache.jsp").forward(req, resp);
    }

    private void updateTache(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Retrieve task details from the request parameters
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        String t_name = req.getParameter("t_name");
        String t_Description = req.getParameter("t_Description");
        Date t_datedebut = Date.valueOf(req.getParameter("t_datedebut"));
        Date tdatefin = Date.valueOf(req.getParameter("tdatefin"));
        String status = req.getParameter("status");
        int projectId = Integer.parseInt(req.getParameter("projectId"));

        // Create a new task object with updated details
        Taches updatedTache = new Taches(taskId, status, tdatefin, t_datedebut, t_Description, t_name, projectId);

        try {
            // Update the task in the database
            tacheDao.updateTaches(updatedTache);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception properly, maybe redirect to an error page
        }

        // Redirect to the task list page for the project
        resp.sendRedirect(req.getContextPath() + "/tacheHome?projectId=" + projectId);
    }
     private void viewTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // Retrieve project ID from request parameter
         int projectId = Integer.parseInt(req.getParameter("projectId"));

         try {
             // Retrieve tasks for the specified project ID
             List<Taches> tasks = tacheDao.afficherTaches(projectId);

             // Set tasks and project ID as request attributes
             req.setAttribute("tasks", tasks);
             req.setAttribute("projectId", projectId);
         } catch (SQLException e) {
             e.printStackTrace();
             // Handle the exception properly, maybe redirect to an error page
         }

         // Forward the request to the JSP page for viewing tasks
         req.getRequestDispatcher("/WEB-INF/view_tasks.jsp").forward(req, resp);
     }
 }

