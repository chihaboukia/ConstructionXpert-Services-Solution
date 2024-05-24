package model.dao;

import model.entities.Projet;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ProjetDao projectDao = new ProjetDao();
        List<Projet> project = projectDao.getALLProjets();

        if (project.isEmpty()) {
            System.out.println("No projects found.");
        } else {
            System.out.println("List of Projects:");
            for (Projet projects : project) {
                System.out.println("id_projet: " + projects.getId());
                System.out.println("nom: " + projects.getNom());
                System.out.println("Description: " + projects.getDescription());
                System.out.println("Start Date: " + projects.getDateDebut());
                System.out.println("End Date: " + projects.getDateFin());
                System.out.println("Budget: " + projects.getBudget());
                System.out.println("---------------------------");
            }
        }
    }
}
