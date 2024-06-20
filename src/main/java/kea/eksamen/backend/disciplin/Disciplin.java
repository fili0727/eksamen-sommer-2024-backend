package kea.eksamen.backend.disciplin;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.resultat.Resultat;
import kea.eksamen.backend.resultat.ResultatEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Disciplin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String disciplinNavn;

    @Enumerated(EnumType.STRING)
    private ResultatEnum resultatEnum;

    @ManyToMany(mappedBy = "discipliner")
    private List<Deltager> deltagere;

    @OneToMany(mappedBy = "disciplin")
    private List<Resultat> resultater;

    public Disciplin(String disciplinNavn, ResultatEnum resultatEnum) {
        this.disciplinNavn = disciplinNavn;
        this.resultatEnum = resultatEnum;
    }

    public Disciplin(int id, String disciplinNavn, ResultatEnum resultatEnum) {
        this.id = id;
        this.disciplinNavn = disciplinNavn;
        this.resultatEnum = resultatEnum;
    }
}

