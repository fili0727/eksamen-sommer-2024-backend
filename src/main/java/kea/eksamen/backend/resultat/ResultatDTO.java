package kea.eksamen.backend.resultat;


import kea.eksamen.backend.enums.ResultatEnum;

import java.time.LocalDate;

public record ResultatDTO(LocalDate dato, double distance, int point, int tidSekunder, double højde, ResultatEnum resultatEnum, int disciplinId, int deltagerId) {
}
