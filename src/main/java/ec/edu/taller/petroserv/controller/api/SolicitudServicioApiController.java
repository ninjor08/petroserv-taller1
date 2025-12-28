package ec.edu.taller.petroserv.controller.api;

import ec.edu.taller.petroserv.model.Persona;
import ec.edu.taller.petroserv.model.ServicioPetrolero;
import ec.edu.taller.petroserv.model.SolicitudServicio;
import ec.edu.taller.petroserv.repository.PersonaRepository;
import ec.edu.taller.petroserv.repository.ServicioPetroleroRepository;
import ec.edu.taller.petroserv.repository.SolicitudServicioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudServicioApiController {

    private final SolicitudServicioRepository solicitudRepository;
    private final PersonaRepository personaRepository;
    private final ServicioPetroleroRepository servicioRepository;

    public SolicitudServicioApiController(SolicitudServicioRepository solicitudRepository,
                                          PersonaRepository personaRepository,
                                          ServicioPetroleroRepository servicioRepository) {
        this.solicitudRepository = solicitudRepository;
        this.personaRepository = personaRepository;
        this.servicioRepository = servicioRepository;
    }

    @GetMapping
    public List<SolicitudServicio> listar() {
        return solicitudRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudServicio> obtenerPorId(@PathVariable Long id) {
        return solicitudRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody SolicitudServicio solicitud) {

        if (solicitud.getPersona() == null || solicitud.getPersona().getId() == null) {
            return ResponseEntity.badRequest().body("Debe enviar persona con su id.");
        }
        if (solicitud.getServicio() == null || solicitud.getServicio().getId() == null) {
            return ResponseEntity.badRequest().body("Debe enviar servicio con su id.");
        }

        Optional<Persona> personaOpt = personaRepository.findById(solicitud.getPersona().getId());
        if (personaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada.");
        }

        Optional<ServicioPetrolero> servOpt = servicioRepository.findById(solicitud.getServicio().getId());
        if (servOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ServicioPetrolero no encontrado.");
        }

        solicitud.setPersona(personaOpt.get());
        solicitud.setServicio(servOpt.get());

        SolicitudServicio guardado = solicitudRepository.save(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody SolicitudServicio datos) {
        Optional<SolicitudServicio> actualOpt = solicitudRepository.findById(id);
        if (actualOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SolicitudServicio actual = actualOpt.get();
        actual.setFecha(datos.getFecha());
        actual.setEstado(datos.getEstado());

        if (datos.getPersona() != null && datos.getPersona().getId() != null) {
            Optional<Persona> personaOpt = personaRepository.findById(datos.getPersona().getId());
            if (personaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada.");
            }
            actual.setPersona(personaOpt.get());
        }

        if (datos.getServicio() != null && datos.getServicio().getId() != null) {
            Optional<ServicioPetrolero> servOpt = servicioRepository.findById(datos.getServicio().getId());
            if (servOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ServicioPetrolero no encontrado.");
            }
            actual.setServicio(servOpt.get());
        }

        return ResponseEntity.ok(solicitudRepository.save(actual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!solicitudRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        solicitudRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
