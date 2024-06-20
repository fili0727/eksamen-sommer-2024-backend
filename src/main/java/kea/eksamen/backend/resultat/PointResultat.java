package kea.eksamen.backend.resultat;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.disciplin.Disciplin;

import java.time.LocalDate;

@Entity
public class PointResultat extends Resultat {

    private String resultatType;
    private LocalDate dato;
    private int point;


    public PointResultat() {
        this.resultatType = "point";
    }

    public PointResultat(LocalDate dato, int point) {
        this.resultatType = "point";
        this.dato = dato;
        this.point = point;
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
    public Integer getResultatVærdi() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
