package kea.eksamen.backend.config;

import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.deltager.DeltagerRepository;
import kea.eksamen.backend.enums.Klub;
import kea.eksamen.backend.enums.Køn;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateTestData implements CommandLineRunner {
    private final DeltagerRepository deltagerRepository;

    public CreateTestData(DeltagerRepository deltagerRepository) {
        this.deltagerRepository = deltagerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        lavTestDeltagere();
    }

private void lavTestDeltagere(){
        Deltager deltager1 = new Deltager("Mikkel",  Køn.MAND, 25, Klub.DEN);
        Deltager deltager2 = new Deltager("Daniel", Køn.MAND, 17, Klub.DEN);
        Deltager deltager3 = new Deltager("Mads",   Køn.MAND, 40, Klub.DEN);
        Deltager deltager4 = new Deltager("Mette",  Køn.KVINDE, 14, Klub.DEN);
        Deltager deltager5 = new Deltager("Mia",    Køn.KVINDE, 6, Klub.DEN);
        Deltager deltager6 = new Deltager("Lucca", Køn.ANDEN, 20, Klub.DEN);

        Deltager deltager7 = new Deltager("Steven",  Køn.MAND, 24, Klub.SWE);
        Deltager deltager8 = new Deltager("Sofia",   Køn.KVINDE, 21, Klub.SWE);
        Deltager deltager9 = new Deltager("Sara",    Køn.KVINDE, 15, Klub.SWE);
        Deltager deltager10 = new Deltager("Sven", Køn.ANDEN, 45, Klub.SWE);
        Deltager deltager11 = new Deltager("Sofus", Køn.ANDEN, 18, Klub.SWE);
        Deltager deltager12 = new Deltager("Karl", Køn.MAND,14,Klub.SWE);

    List<Deltager> deltagerList = List.of(deltager1, deltager2, deltager3, deltager4, deltager5, deltager6, deltager7, deltager8, deltager9, deltager10, deltager11, deltager12);

        deltagerRepository.saveAll(deltagerList);
}

private void lavTestDiscipliner(){


}

}
