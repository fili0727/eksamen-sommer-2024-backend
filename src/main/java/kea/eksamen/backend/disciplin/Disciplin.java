package kea.eksamen.backend.disciplin;

import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.resultat.Resultat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Disciplin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String disciplin_navn;


    @ManyToMany(mappedBy = "discipliner")
    private List<Deltager> deltagere;

    @OneToMany(mappedBy = "disciplin")
    private List<Resultat> resultater;

    public Disciplin(String disciplin_navn){
        this.disciplin_navn = disciplin_navn;
    }

}
