package Model;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient extends User {


    private Date DateNaissance;
    private Medecin medecin;

    public Patient(){
        super();
    }
    public Patient(int id, String nom, String prenom, String email, String telephone, String userName, String password, String adresse, String dateNaissance, Medecin medecin) {
        super(id, nom, prenom, email, telephone, userName, password, adresse);

        try {

            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd");
            DateNaissance = (Date) dt.parse(dateNaissance);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.medecin = medecin;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        DateNaissance = dateNaissance;
    }

}
