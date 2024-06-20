package kea.eksamen.backend.resultat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Integer> {
    List<Resultat> findByDeltagerId(int deltagerId);
}
