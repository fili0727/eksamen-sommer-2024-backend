package kea.eksamen.backend.disciplin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discipliner")
public class DisciplinController {
    private final DisciplinService disciplinService;

    public DisciplinController(DisciplinService disciplinService) {
        this.disciplinService = disciplinService;
    }

    @GetMapping
    public ResponseEntity<List<Disciplin>> findAlleDiscipliner() {
        List<Disciplin> discipliner = disciplinService.findAlleDiscipliner();
        return ResponseEntity.ok(discipliner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplin> findDisciplinMedId(@PathVariable int id) {
        if (disciplinService.findDisciplinMedId(id).isPresent()) {
            Disciplin disciplin = disciplinService.findDisciplinMedId(id).get();
            return ResponseEntity.ok(disciplin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Disciplin> opretDisciplin(@RequestBody Disciplin disciplin) {
        try {
            Disciplin createdDisciplin = disciplinService.opretDisciplin(disciplin);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDisciplin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
