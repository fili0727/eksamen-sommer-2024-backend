package kea.eksamen.backend.resultat;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.disciplin.Disciplin;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;


@Entity
public class TidsResultat extends Resultat{
    private String resultatType;
    private LocalDate dato;
    private Duration tid;


    public TidsResultat(String resultatType, LocalDate dato, Duration tid) {
        this.resultatType = resultatType;
        this.dato = dato;
        this.tid = tid;
    }

    public TidsResultat(){
        this.resultatType = "tid";
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
    public Duration getResultatVærdi() {
        return tid  ;
    }

    public void setTid(Duration tid) {
        this.tid = tid;
    }

}
