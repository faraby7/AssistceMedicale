package Model;

public class Medecin extends User {


    private String Specialite;

    public Medecin(int id, String nom, String prenom, String email, String telephone, String userName, String password, String adresse, String specialite) {
        super(id, nom, prenom, email, telephone, userName, password, adresse);
        Specialite = specialite;
    }

    public String getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(String specialite) {
        Specialite = specialite;
    }


}
