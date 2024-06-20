package kea.eksamen.backend.deltager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deltagere")
public class DeltagerController {
    private final DeltagerService deltagerService;

    public DeltagerController(DeltagerService deltagerService) {
        this.deltagerService = deltagerService;
    }

    @GetMapping
    public ResponseEntity<List<Deltager>> findAlleDeltagere() {
        List<Deltager> deltagere = deltagerService.findAlleDeltagere();
        return ResponseEntity.ok(deltagere);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deltager> findDeltagerMedId(@PathVariable int id) {
        if (deltagerService.findDeltagerMedId(id).isPresent()) {
            Deltager deltager = deltagerService.findDeltagerMedId(id).get();
            return ResponseEntity.ok(deltager);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Deltager> opretDeltager(@RequestBody Deltager deltager) {
        try {
            Deltager createdDeltager = deltagerService.opretDeltager(deltager);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDeltager);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deltager> redigerDeltager(@PathVariable int id, @RequestBody Deltager deltager) {
        try {
            Deltager updatedDeltager = deltagerService.redigerDeltager(id, deltager);
            return ResponseEntity.ok(updatedDeltager);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> sletDeltager(@PathVariable int id) {
        deltagerService.sletDeltager(id);
        return ResponseEntity.noContent().build();
    }
}
