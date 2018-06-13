package Model;

public class Ordonnance {


    public int id;
    public int id_patient;
    public int id_medecin;
    public String Date;


    public Ordonnance(int id, int id_patient, int id_medecin, String date) {
        this.id = id;
        this.id_patient = id_patient;
        this.id_medecin = id_medecin;
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
