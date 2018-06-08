package Model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Ordonnance {

    private int duree;
    private int id;
    private Timestamp heureMatin;
    private Timestamp heureMidi;
    private Timestamp heureSoir;
    private Patient patient;
    private Medicament medicament;

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getHeureMatin() {
        return heureMatin;
    }

    public void setHeureMatin(Timestamp heureMatin) {
        this.heureMatin = heureMatin;
    }

    public Timestamp getHeureMidi() {
        return heureMidi;
    }

    public void setHeureMidi(Timestamp heureMidi) {
        this.heureMidi = heureMidi;
    }

    public Timestamp getHeureSoir() {
        return heureSoir;
    }

    public void setHeureSoir(Timestamp heureSoir) {
        this.heureSoir = heureSoir;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }



    public Ordonnance() {
    }

    public Ordonnance(int id,int duree, Timestamp heureMatin, Timestamp heureMidi, Timestamp heureSoir, Patient patient,Medicament medicament) {
        this.duree = duree;
        this.heureMatin = heureMatin;
        this.heureMidi = heureMidi;
        this.heureSoir = heureSoir;
        this.patient = patient;
        this.medicament = medicament;
    }



}
