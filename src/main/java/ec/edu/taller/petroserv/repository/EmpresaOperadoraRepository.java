package ec.edu.taller.petroserv.repository;

import ec.edu.taller.petroserv.model.EmpresaOperadora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaOperadoraRepository extends JpaRepository<EmpresaOperadora, Long> {
    Optional<EmpresaOperadora> findByRuc(String ruc);
}

