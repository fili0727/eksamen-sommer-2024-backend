package kea.eksamen.backend.resultat;

import java.time.LocalDate;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.disciplin.Disciplin;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "deltager_id")
    private Deltager deltager;

    @ManyToOne
    @JoinColumn(name = "disciplin_id")
    private Disciplin disciplin;

    public abstract String getResultatType();
    public abstract LocalDate getDato();
    public abstract Object getResultatVærdi();




}