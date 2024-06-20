package kea.eksamen.backend.resultat;

import kea.eksamen.backend.deltager.Deltager;
import kea.eksamen.backend.deltager.DeltagerService;
import kea.eksamen.backend.disciplin.Disciplin;
import kea.eksamen.backend.disciplin.DisciplinService;
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

    public Resultat opretResultatForDeltager(int deltagerId, int disciplinId, Resultat resultat) {
        Optional<Deltager> deltager = deltagerService.findDeltagerMedId(deltagerId);
        if (deltager.isEmpty()) {
            throw new IllegalArgumentException("No Deltager found with id: " + deltagerId);
        }
        resultat.setDeltager(deltager.get());

        Optional<Disciplin> disciplin = disciplinService.findDisciplinMedId(disciplinId);
        if (disciplin.isEmpty()) {
            throw new IllegalArgumentException("No Disciplin found with id: " + disciplinId);
        }
        resultat.setDisciplin(disciplin.get());

        return resultatRepository.save(resultat);
    }

//    public List<Resultat> opretResultaterForDeltagere(List<Integer> deltagerIds, List<Resultat> resultater) {
//        if (deltagerIds.size() != resultater.size()) {
//            throw new IllegalArgumentException("The number of Deltagere and Resultater must be the same");
//        }
//
//        List<Resultat> savedResultater = new ArrayList<>();
//        for (int i = 0; i < deltagerIds.size(); i++) {
//            int deltagerId = deltagerIds.get(i);
//            Resultat resultat = resultater.get(i);
//
//            Optional<Deltager> deltager = deltagerService.findDeltagerMedId(deltagerId);
//            if (deltager.isEmpty()) {
//                throw new IllegalArgumentException("No Deltager found with id: " + deltagerId);
//            }
//
//            resultat.setDeltager(deltager.get());
//            savedResultater.add(resultatRepository.save(resultat));
//        }
//
//        return savedResultater;
//    }

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



    public void sletResultat(int id) {
        resultatRepository.deleteById(id);
    }

}
