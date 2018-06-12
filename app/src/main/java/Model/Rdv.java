package Model;



public class Rdv {

    private String date;
    private Patient patient;
    private String description;

    public Rdv( String description,String date, Patient patient) {
        this.date = date;
        this.patient = patient;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Rdv{" +
                "date=" + date +
                ", patient=" + patient +
                ", description='" + description + '\'' +
                '}';
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
