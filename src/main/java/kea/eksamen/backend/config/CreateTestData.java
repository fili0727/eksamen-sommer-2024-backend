package kea.eksamen.backend.config;

import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.deltager.DeltagerRepository;
import kea.eksamen.backend.disciplin.Disciplin;
import kea.eksamen.backend.disciplin.DisciplinRepository;
import kea.eksamen.backend.enums.Klub;
import kea.eksamen.backend.enums.Køn;
import kea.eksamen.backend.resultat.Resultat;
import kea.eksamen.backend.resultat.ResultatEnum;
import kea.eksamen.backend.resultat.ResultatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class CreateTestData implements CommandLineRunner {
    private final DeltagerRepository deltagerRepository;
    private final DisciplinRepository disciplinRepository;
    private final ResultatRepository resultatRepository;

    public CreateTestData(DeltagerRepository deltagerRepository, DisciplinRepository disciplinRepository, ResultatRepository resultatRepository) {
        this.deltagerRepository = deltagerRepository;
        this.disciplinRepository = disciplinRepository;
        this.resultatRepository = resultatRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        lavTestData();
    }

    private void lavTestData() {
        Deltager deltager1 = new Deltager(1, "Mikkel", Køn.MAND, 25, Klub.DEN);
        Deltager deltager2 = new Deltager(2, "Daniel", Køn.MAND, 17, Klub.DEN);
        Deltager deltager3 = new Deltager(3, "Mads", Køn.MAND, 40, Klub.DEN);
        Deltager deltager4 = new Deltager(4, "Mette", Køn.KVINDE, 14, Klub.DEN);
        Deltager deltager5 = new Deltager(5, "Mia", Køn.KVINDE, 6, Klub.DEN);
        Deltager deltager6 = new Deltager(6, "Lucca", Køn.ANDEN, 20, Klub.DEN);
        Deltager deltager7 = new Deltager(7, "Steven", Køn.MAND, 24, Klub.SWE);
        Deltager deltager8 = new Deltager(8, "Sofia", Køn.KVINDE, 21, Klub.SWE);
        Deltager deltager9 = new Deltager(9, "Sara", Køn.KVINDE, 15, Klub.SWE);
        Deltager deltager10 = new Deltager(10, "Sven", Køn.ANDEN, 45, Klub.SWE);
        Deltager deltager11 = new Deltager(11, "Sofus", Køn.ANDEN, 18, Klub.SWE);
        Deltager deltager12 = new Deltager(12, "Karl", Køn.MAND, 14, Klub.SWE);

        List<Deltager> deltagerList = List.of(deltager1, deltager2, deltager3, deltager4, deltager5, deltager6, deltager7, deltager8, deltager9, deltager10, deltager11, deltager12);
        deltagerRepository.saveAll(deltagerList);

        Disciplin disciplin1 = new Disciplin("60 meter hækkeløb", ResultatEnum.TID);
        Disciplin disciplin2 = new Disciplin("Højdespring", ResultatEnum.HØJDE);
        Disciplin disciplin3 = new Disciplin("Længdespring", ResultatEnum.DISTANCE);
        Disciplin disciplin4 = new Disciplin("100 meter løb", ResultatEnum.TID);
        Disciplin disciplin5 = new Disciplin("Kuglestød", ResultatEnum.DISTANCE);
        Disciplin disciplin6 = new Disciplin("Bue skydning", ResultatEnum.POINT);

        List<Disciplin> disciplinList = List.of(disciplin1, disciplin2, disciplin3, disciplin4, disciplin5, disciplin6);
        disciplinRepository.saveAll(disciplinList);

        // Assign disciplines to participants
        deltager1.setDiscipliner(List.of(disciplin1, disciplin4));
        deltager2.setDiscipliner(List.of(disciplin2));
        deltager3.setDiscipliner(List.of(disciplin3));
        deltager4.setDiscipliner(List.of(disciplin4));
        deltager5.setDiscipliner(List.of(disciplin5));
        deltager6.setDiscipliner(List.of(disciplin6));
        deltagerRepository.saveAll(deltagerList); // Save again after setting disciplines

        // Creating and saving Resultat objects
        Resultat resultat1 = new Resultat(ResultatEnum.TID, LocalDate.now(), null, null, Duration.ofMinutes(8).plusSeconds(32), null);
        resultat1.setDeltager(deltager1);
        resultat1.setDisciplin(disciplin1);

        Resultat resultat2 = new Resultat(ResultatEnum.HØJDE, LocalDate.now(), null, null, null, 2.1);
        resultat2.setDeltager(deltager2);
        resultat2.setDisciplin(disciplin2);

        Resultat resultat3 = new Resultat(ResultatEnum.DISTANCE, LocalDate.now(), 6.5, null, null, null);
        resultat3.setDeltager(deltager3);
        resultat3.setDisciplin(disciplin3);

        Resultat resultat4 = new Resultat(ResultatEnum.TID, LocalDate.now(), null, null, Duration.ofMinutes(12).plusSeconds(15), null);
        resultat4.setDeltager(deltager4);
        resultat4.setDisciplin(disciplin4);

        Resultat resultat5 = new Resultat(ResultatEnum.DISTANCE, LocalDate.now(), 15.3, null, null, null);
        resultat5.setDeltager(deltager5);
        resultat5.setDisciplin(disciplin5);

        Resultat resultat6 = new Resultat(ResultatEnum.POINT, LocalDate.now(), null, 10, null, null);
        resultat6.setDeltager(deltager6);
        resultat6.setDisciplin(disciplin6);

        List<Resultat> resultatList = List.of(resultat1, resultat2, resultat3, resultat4, resultat5, resultat6);
        resultatRepository.saveAll(resultatList);
    }
}


