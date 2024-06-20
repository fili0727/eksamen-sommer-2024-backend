package kea.eksamen.backend.resultat;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.disciplin.Disciplin;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Entity
public class DistanceResultat extends Resultat {

    private String resultatType;
    private LocalDate dato;
    private double distance;


    public DistanceResultat(String resultatType, LocalDate dato, double distance) {
        this.resultatType = resultatType;
        this.dato = dato;
        this.distance = distance;
    }

    public DistanceResultat(){
        this.resultatType = "distance";
    }


    @Override
    public String getResultatType() {
        return resultatType;
    }

    @Override
    public LocalDate getDato() {
        return dato;
    }

    @Override
    public Double getResultatVærdi() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}