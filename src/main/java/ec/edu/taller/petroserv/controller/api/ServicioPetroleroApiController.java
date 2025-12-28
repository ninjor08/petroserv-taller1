package ec.edu.taller.petroserv.controller.api;

import ec.edu.taller.petroserv.model.ServicioPetrolero;
import ec.edu.taller.petroserv.repository.ServicioPetroleroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicios")
public class ServicioPetroleroApiController {

    private final ServicioPetroleroRepository servicioRepository;

    public ServicioPetroleroApiController(ServicioPetroleroRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @GetMapping
    public List<ServicioPetrolero> listar() {
        return servicioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioPetrolero> obtenerPorId(@PathVariable Long id) {
        return servicioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServicioPetrolero> crear(@RequestBody ServicioPetrolero servicio) {
        ServicioPetrolero guardado = servicioRepository.save(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioPetrolero> actualizar(@PathVariable Long id, @RequestBody ServicioPetrolero datos) {
        Optional<ServicioPetrolero> actualOpt = servicioRepository.findById(id);
        if (actualOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ServicioPetrolero actual = actualOpt.get();
        actual.setNombre(datos.getNombre());
        actual.setDescripcion(datos.getDescripcion());
        actual.setTarifaBaseUsd(datos.getTarifaBaseUsd());
        return ResponseEntity.ok(servicioRepository.save(actual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!servicioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        servicioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
