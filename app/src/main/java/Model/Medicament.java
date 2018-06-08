package Model;

import java.sql.Blob;

public class Medicament {

    private int id;
    private String nom;
    private String type;
    private String description;

    public Medicament(int id, String nom, String type, String description) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Medicament(int id, String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
