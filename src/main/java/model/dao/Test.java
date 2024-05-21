package model.dao;

import model.entities.Projet;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Projet projet = new Projet();
        projet.setNom("ENAA");
        projet.setDescription("Description");
        projet.setDateDebut(new java.util.Date()); // assuming you want to use current date for start
        projet.setDateFin(new java.util.Date(System.currentTimeMillis() + 86400000L)); // 1 day later
        projet.setBudget(90000.00);

        ProjetDao projetDao = new ProjetDao();
        try {
            projetDao.ajouterProjet(projet);
            System.out.println("Project saved successfully!");
        } catch (RuntimeException e) {
            System.out.println("Failed to save the project: " + e.getMessage());
        }
    }
}
