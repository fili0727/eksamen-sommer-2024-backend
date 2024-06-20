package kea.eksamen.backend.disciplin;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinService {
    private final DisciplinRepository disciplinRepository;

    public DisciplinService(DisciplinRepository disciplinRepository) {
        this.disciplinRepository = disciplinRepository;
    }

    public List<Disciplin> findAlleDiscipliner() {
        return disciplinRepository.findAll();
    }

    public Optional<Disciplin> findDisciplinMedId(int id) {
        Optional<Disciplin> valgteDisciplin = disciplinRepository.findById(id);
        return valgteDisciplin;
    }

    public Disciplin opretDisciplin(Disciplin disciplin) {
        return disciplinRepository.save(disciplin);
    }

    public void sletDisciplin(int id) {
        disciplinRepository.deleteById(id);
    }


}
