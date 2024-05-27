package dao;

import metier.Taches;
import java.sql.SQLException;
import java.util.List;

public interface ITaches {
        Taches saveTaches(Taches t) throws SQLException;
        List<Taches> afficherTaches(int idProjec) throws SQLException;
        Taches getTaches(int id) throws SQLException;
        Taches updateTaches(Taches t) throws SQLException;
        void deleteTaches(int id_tach) throws SQLException;
        Taches getTacheById(int id) throws SQLException;
}
