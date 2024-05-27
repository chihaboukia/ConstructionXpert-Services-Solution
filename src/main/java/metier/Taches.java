package metier;
import java.sql.Date;




public class Taches {
    private int id_tach;
    private int idProject;
    private String t_name;
    private String t_Description;
    private Date t_datedebut;
    private Date tdatefin;
    private String status;

    public Taches(int id_tach, String status, Date tdatefin, Date t_datedebut,
                  String t_Description, String t_name) {
        this.id_tach = id_tach;
        this.status = status;
        this.tdatefin = tdatefin;
        this.t_datedebut = t_datedebut;
        this.t_Description = t_Description;
        this.t_name = t_name;

    }

    public int getId_tach() {
        return id_tach;
    }

    public void setId_tach(int id_tach) {
        this.id_tach = id_tach;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTdatefin() {
        return tdatefin;
    }

    public void setTdatefin(Date tdatefin) {
        this.tdatefin = tdatefin;
    }

    public Date getT_datedebut() {
        return t_datedebut;
    }

    public void setT_datedebut(Date t_datedebut) {
        this.t_datedebut = t_datedebut;
    }

    public String getT_Description() {
        return t_Description;
    }

    public void setT_Description(String t_Description) {
        this.t_Description = t_Description;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public Taches(int idProject) {
        this.idProject = idProject;
    }
}