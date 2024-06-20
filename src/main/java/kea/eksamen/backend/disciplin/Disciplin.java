package kea.eksamen.backend.disciplin;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.resultat.Resultat;

import java.util.List;

@Entity
public class Disciplin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String disciplin_navn;


    @ManyToMany(mappedBy = "discipliner")
    private List<Deltager> deltagere;

    @OneToMany(mappedBy = "disciplin")
    private List<Resultat> resultater;
}
