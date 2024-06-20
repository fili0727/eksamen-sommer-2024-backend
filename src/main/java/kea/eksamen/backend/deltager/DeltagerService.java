package kea.eksamen.backend.deltager;

import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class DeltagerService {
    private final DeltagerRepository deltagerRepository;

    public DeltagerService(DeltagerRepository deltagerRepository) {
        this.deltagerRepository = deltagerRepository;
    }

    public List<Deltager> findAlleDeltagere() {
        return deltagerRepository.findAll();
    }

    public Optional<Deltager> findDeltagerMedId(int id) {
        Optional<Deltager> valgteDeltager = deltagerRepository.findById(id);
        return valgteDeltager;
    }

    public Deltager opretDeltager(Deltager deltager) {
        return deltagerRepository.save(deltager);
    }

    public Deltager redigerDeltager(int id, Deltager deltager) {
        Optional<Deltager> valgteDeltager = deltagerRepository.findById(id);

        if (valgteDeltager.isEmpty()){
            return null;
        }

        Deltager deltagerTilRedigering = valgteDeltager.get();
        deltagerTilRedigering.setNavn(deltager.getNavn());
        deltagerTilRedigering.setAlder(deltager.getAlder());
        deltagerTilRedigering.setKøn(deltager.getKøn());
        deltagerTilRedigering.setKlub(deltager.getKlub());

        deltagerRepository.save(deltagerTilRedigering);

        return deltagerTilRedigering;
    }

    public void sletDeltager(int id) {
        deltagerRepository.deleteById(id);
    }


}
