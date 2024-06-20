package kea.eksamen.backend.deltager;

import jakarta.persistence.*;
import kea.eksamen.backend.disciplin.Disciplin;
import kea.eksamen.backend.enums.Klub;
import kea.eksamen.backend.enums.Køn;
import kea.eksamen.backend.resultat.Resultat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Deltager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;
    @Enumerated(EnumType.STRING)
    private Køn køn;
    private int alder;
    @Enumerated(EnumType.STRING)
    private Klub klub;

    @ManyToMany
    private List<Disciplin> discipliner;

    @OneToMany(mappedBy = "deltager")
    private List<Resultat> resultater;

    public Deltager(String navn, Køn køn, int alder, Klub klub){
        this.navn = navn;
        this.køn = køn;
        this.alder = alder;
        this.klub = klub;
    }

    public Deltager(int id, String navn, Køn køn, int alder, Klub klub){
        this.id = id;
        this.navn = navn;
        this.køn = køn;
        this.alder = alder;
        this.klub = klub;
    }



}
