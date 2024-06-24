package kea.eksamen.backend.resultat;

import java.time.LocalDate;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.disciplin.Disciplin;
import kea.eksamen.backend.enums.ResultatEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    private Integer tidSekunder;
    private Double højde;

    public Resultat() {
    }

    public Resultat(Deltager deltager, Disciplin disciplin, ResultatEnum resultatEnum, LocalDate dato, double distance, int point, int tidSekunder, double højde) {
        this.deltager = deltager;
        this.disciplin = disciplin;
        this.resultatEnum = resultatEnum;
        this.dato = dato;
        this.distance = distance;
        this.point = point;
        this.tidSekunder = tidSekunder;
        this.højde = højde;
    }

    public Resultat(ResultatEnum resultatEnum, LocalDate dato, Double distance, Integer point, Integer tidSekunder,Double højde) {
        this.resultatEnum = resultatEnum;
        this.dato = dato;
        this.distance = distance;
        this.point = point;
        this.tidSekunder = tidSekunder;
        this.højde = højde;
    }


}
