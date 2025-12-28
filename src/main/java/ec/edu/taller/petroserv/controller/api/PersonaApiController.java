package ec.edu.taller.petroserv.controller.api;

import ec.edu.taller.petroserv.model.EmpresaOperadora;
import ec.edu.taller.petroserv.model.Persona;
import ec.edu.taller.petroserv.repository.EmpresaOperadoraRepository;
import ec.edu.taller.petroserv.repository.PersonaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaApiController {

    private final PersonaRepository personaRepository;
    private final EmpresaOperadoraRepository empresaRepository;

    public PersonaApiController(PersonaRepository personaRepository,
                                EmpresaOperadoraRepository empresaRepository) {
        this.personaRepository = personaRepository;
        this.empresaRepository = empresaRepository;
    }

    @GetMapping
    public List<Persona> listar() {
        return personaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable Long id) {
        return personaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Persona persona) {
        // Si viene empresaOperadora con id, se valida y se asigna.
        if (persona.getEmpresaOperadora() == null || persona.getEmpresaOperadora().getId() == null) {
            return ResponseEntity.badRequest().body("Debe enviar empresaOperadora con su id.");
        }

        Optional<EmpresaOperadora> empresaOpt = empresaRepository.findById(persona.getEmpresaOperadora().getId());
        if (empresaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmpresaOperadora no encontrada.");
        }

        persona.setEmpresaOperadora(empresaOpt.get());
        Persona guardado = personaRepository.save(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Persona datos) {
        Optional<Persona> actualOpt = personaRepository.findById(id);
        if (actualOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Persona actual = actualOpt.get();
        actual.setCedula(datos.getCedula());
        actual.setNombres(datos.getNombres());
        actual.setCiudad(datos.getCiudad());
        actual.setCelular(datos.getCelular());
        actual.setEdad(datos.getEdad());

        if (datos.getEmpresaOperadora() != null && datos.getEmpresaOperadora().getId() != null) {
            Optional<EmpresaOperadora> empresaOpt = empresaRepository.findById(datos.getEmpresaOperadora().getId());
            if (empresaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmpresaOperadora no encontrada.");
            }
            actual.setEmpresaOperadora(empresaOpt.get());
        }

        return ResponseEntity.ok(personaRepository.save(actual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!personaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
