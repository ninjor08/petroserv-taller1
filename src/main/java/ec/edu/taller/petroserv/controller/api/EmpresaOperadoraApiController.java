package ec.edu.taller.petroserv.controller.api;

import ec.edu.taller.petroserv.model.EmpresaOperadora;
import ec.edu.taller.petroserv.repository.EmpresaOperadoraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaOperadoraApiController {

    private final EmpresaOperadoraRepository empresaRepository;

    public EmpresaOperadoraApiController(EmpresaOperadoraRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @GetMapping
    public List<EmpresaOperadora> listar() {
        return empresaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaOperadora> obtenerPorId(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpresaOperadora> crear(@RequestBody EmpresaOperadora empresa) {
        EmpresaOperadora guardado = empresaRepository.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaOperadora> actualizar(@PathVariable Long id, @RequestBody EmpresaOperadora datos) {
        Optional<EmpresaOperadora> actualOpt = empresaRepository.findById(id);
        if (actualOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        EmpresaOperadora actual = actualOpt.get();
        actual.setRazonSocial(datos.getRazonSocial());
        actual.setRuc(datos.getRuc());
        actual.setCiudad(datos.getCiudad());
        return ResponseEntity.ok(empresaRepository.save(actual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!empresaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        empresaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
