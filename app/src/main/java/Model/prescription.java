package Model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class prescription{


    private int duree;
    private int heureMatin;
    private int heureMidi;
    private int heureSoir;
    private int  id_medicament;
    private int id_ordonnance;


    public prescription(int duree, int heureMatin, int heureMidi, int heureSoir, int id_medicament, int id_ordonnance) {
        this.duree = duree;
        this.heureMatin = heureMatin;
        this.heureMidi = heureMidi;
        this.heureSoir = heureSoir;
        this.id_medicament = id_medicament;
        this.id_ordonnance = id_ordonnance;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getHeureMatin() {
        return heureMatin;
    }

    public void setHeureMatin(int heureMatin) {
        this.heureMatin = heureMatin;
    }

    public int getHeureMidi() {
        return heureMidi;
    }

    public void setHeureMidi(int heureMidi) {
        this.heureMidi = heureMidi;
    }

    public int getHeureSoir() {
        return heureSoir;
    }

    public void setHeureSoir(int heureSoir) {
        this.heureSoir = heureSoir;
    }

    public int getId_medicament() {
        return id_medicament;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public int getId_ordonnance() {
        return id_ordonnance;
    }

    public void setId_ordonnance(int id_ordonnance) {
        this.id_ordonnance = id_ordonnance;
    }
}
