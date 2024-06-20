package kea.eksamen.backend.disciplin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.resultat.Resultat;
import kea.eksamen.backend.enums.ResultatEnum;
import lombok.AllArgsConstructor;
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
    private String disciplinNavn;

    @Enumerated(EnumType.STRING)
    private ResultatEnum resultatEnum;


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
