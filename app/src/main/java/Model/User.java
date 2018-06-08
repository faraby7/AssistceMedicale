package Model;

public class User{

    protected int id;
    protected String Nom;
    protected String Prenom;
    protected String Email;
    protected String Telephone;
    protected String UserName;
    protected String Password;
    protected String Adresse;



    public User(){}
    public User(int id, String nom, String prenom, String email, String telephone, String userName, String password, String adresse) {
        this.id = id;
        Nom = nom;
        Prenom = prenom;
        Email = email;
        Telephone = telephone;
        UserName = userName;
        Password = password;
        Adresse = adresse;
    }


    public int getId(){

        return id;
    }
    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }
}
