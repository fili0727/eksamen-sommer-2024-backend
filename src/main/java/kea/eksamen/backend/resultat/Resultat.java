package kea.eksamen.backend.resultat;

import java.time.Duration;
import java.time.LocalDate;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.disciplin.Disciplin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "deltager_id")
    private Deltager deltager;

    @ManyToOne
    @JoinColumn(name = "disciplin_id")
    private Disciplin disciplin;

    @Enumerated(EnumType.STRING)
    private ResultatEnum resultatEnum;

    private LocalDate dato;
    private Double distance;
    private Integer point;
    private Duration tid;
    private Double højde;

    public Resultat() {
    }

    public Resultat(ResultatEnum resultatEnum, LocalDate dato, Double distance, Integer point, Duration tid,Double højde) {
        this.resultatEnum = resultatEnum;
        this.dato = dato;
        this.distance = distance;
        this.point = point;
        this.tid = tid;
        this.højde = højde;
    }



    public Object getResultatVærdi() {
        switch (resultatEnum) {
            case DISTANCE:
                return distance;
            case POINT:
                return point;
            case TID:
                return tid;
            case HØJDE:
                return højde;
            default:
                throw new IllegalArgumentException("ukendt resultat type");
        }
    }

    public void setResultatVærdi(Object værdi) {
        switch (resultatEnum) {
            case DISTANCE:
                this.distance = (Double) værdi;
                break;
            case POINT:
                this.point = (Integer) værdi;
                break;
            case TID:
                this.tid = (Duration) værdi;
                break;
            case HØJDE:
                this.højde = (Double) værdi;
                break;
            default:
                throw new IllegalArgumentException("ukendt resultat type");
        }
    }
    public void setDisciplin(Disciplin disciplin) {
        if (disciplin.getResultatEnum() != this.resultatEnum) {
            throw new IllegalArgumentException("Resultat type matcher ikke disciplinens forventede resultat type.");
        }
        if (this.deltager != null && !this.deltager.getDiscipliner().contains(disciplin)) {
            throw new IllegalArgumentException("Deltager har ikke den givne Disciplin.");
        }
        this.disciplin = disciplin;
    }
}
