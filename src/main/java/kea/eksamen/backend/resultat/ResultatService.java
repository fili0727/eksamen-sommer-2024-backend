package kea.eksamen.backend.resultat;

import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.deltager.DeltagerService;
import kea.eksamen.backend.disciplin.Disciplin;
import kea.eksamen.backend.disciplin.DisciplinService;
import kea.eksamen.backend.enums.ResultatEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultatService {
    private final ResultatRepository resultatRepository;
    private final DeltagerService deltagerService;
    private final DisciplinService disciplinService;

    public ResultatService(ResultatRepository resultatRepository, DeltagerService deltagerService, DisciplinService disciplinService) {
        this.resultatRepository = resultatRepository;
        this.deltagerService = deltagerService;
        this.disciplinService = disciplinService;
    }


    public List<Resultat> findAlleResultater() {
        return resultatRepository.findAll();
    }

    public List<Resultat> findResultaterMedDeltagerId(int deltagerId) {
        return resultatRepository.findByDeltagerId(deltagerId);
    }

    public Optional<Resultat> findResultatMedId(int id) {
        return resultatRepository.findById(id);
    }

public Resultat opretResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }


    public Resultat redigerResultat(int id, Resultat resultat) {
        Optional<Resultat> valgteResultat = resultatRepository.findById(id);

        if (valgteResultat.isEmpty()) {
            return null;
        }

        Resultat resultatTilRedigering = valgteResultat.get();
        resultatTilRedigering.setDeltager(resultat.getDeltager());
        resultatTilRedigering.setDisciplin(resultat.getDisciplin());
        resultatTilRedigering.setDato(resultat.getDato());
        resultatTilRedigering.setDistance(resultat.getDistance());
        resultatTilRedigering.setPoint(resultat.getPoint());
        resultatTilRedigering.setTidSekunder(resultat.getTidSekunder());
        resultatTilRedigering.setHøjde(resultat.getHøjde());

        resultatRepository.save(resultatTilRedigering);

        return resultatTilRedigering;
    }
//Co pilot/ chatgpt start
public ResultatDTO opretResultat(ResultatDTO resultatDTO) {
    // Convert the DTO to a Resultat object
    Resultat resultat = fraDTO(resultatDTO);

    // Save the Resultat object to the repository
    Resultat savedResultat = resultatRepository.save(resultat);

    // Convert the saved Resultat back to a DTO and return it
    return tilDTO(savedResultat);
}

    //co pilot/chat gpt slut


    public void sletResultat(int id) {
        resultatRepository.deleteById(id);
    }

    public ResultatDTO tilDTO(Resultat resultat) {
        return new ResultatDTO(
                resultat.getDato(),
                resultat.getDistance()  != null ? resultat.getDistance() : 0,
                resultat.getPoint()  != null ? resultat.getPoint() : 0,
                resultat.getTidSekunder()  != null ? resultat.getTidSekunder() : 0,
                resultat.getHøjde()  != null ? resultat.getHøjde() : 0,
                resultat.getResultatEnum(),
                resultat.getDeltager() != null ? resultat.getDeltager().getId() : 0,
                resultat.getDisciplin() != null ? resultat.getDisciplin().getId() : 0
        );
    }

    public Resultat fraDTO(ResultatDTO resultatDTO) {
        Deltager deltager = deltagerService.findDeltagerMedId(resultatDTO.deltagerId()).orElse(null);
        Disciplin disciplin = disciplinService.findDisciplinMedId(resultatDTO.disciplinId()).orElse(null);

        return new Resultat(
                deltager,
                disciplin,
                resultatDTO.resultatEnum(),
                resultatDTO.dato(),
                resultatDTO.distance(),
                resultatDTO.point(),
                resultatDTO.tidSekunder(),
                resultatDTO.højde()
        );
    }
}
