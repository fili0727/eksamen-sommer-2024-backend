package kea.eksamen.backend.resultat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultater")
public class ResultatController {
    private final ResultatService resultatService;

    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @GetMapping
    public ResponseEntity<List<Resultat>> findAlleResultater() {
        List<Resultat> resultater = resultatService.findAlleResultater();
        return ResponseEntity.ok(resultater);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> findResultatMedId(@PathVariable int id) {
        if (resultatService.findResultatMedId(id).isPresent()) {
            Resultat resultat = resultatService.findResultatMedId(id).get();
            return ResponseEntity.ok(resultat);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/deltager/{deltagerId}")
    public ResponseEntity<List<Resultat>> findResultaterMedDeltagerId(@PathVariable int deltagerId) {
        List<Resultat> resultater = resultatService.findResultaterMedDeltagerId(deltagerId);
        return ResponseEntity.ok(resultater);
    }


    @PostMapping()
    public ResponseEntity<ResultatDTO> opretResultat(@RequestBody ResultatDTO resultatDTO) {
        try {
            ResultatDTO oprettetResultat = resultatService.opretResultat(resultatDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(oprettetResultat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResultatDTO> redigerResultat(@PathVariable int id, @RequestBody ResultatDTO resultatDTO) {
        try {
            ResultatDTO updatedResultat = resultatService.redigerResultat(id, resultatDTO);
            return ResponseEntity.ok(updatedResultat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> sletResultat(@PathVariable int id) {
        resultatService.sletResultat(id);
        return ResponseEntity.noContent().build();
    }
}
