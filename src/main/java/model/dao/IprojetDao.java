package model.dao;


import model.entities.Projet;

import java.util.List;

public interface IprojetDao {
    List<Projet> getALLProjets();
    List<Projet> getProjetsParMc(String mc);
    Projet ajouterProjet(Projet p);
    void supprimerProjet(int id);
    void modifierProjet(Projet p);

}

