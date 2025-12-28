package ec.edu.taller.petroserv.config;

import ec.edu.taller.petroserv.model.EmpresaOperadora;
import ec.edu.taller.petroserv.model.ServicioPetrolero;
import ec.edu.taller.petroserv.repository.EmpresaOperadoraRepository;
import ec.edu.taller.petroserv.repository.ServicioPetroleroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final EmpresaOperadoraRepository empresaRepo;
    private final ServicioPetroleroRepository servicioRepo;

    public DataSeeder(EmpresaOperadoraRepository empresaRepo, ServicioPetroleroRepository servicioRepo) {
        this.empresaRepo = empresaRepo;
        this.servicioRepo = servicioRepo;
    }

    @Override
    public void run(String... args) {
        if (empresaRepo.count() == 0) {
            empresaRepo.save(new EmpresaOperadora("Petroamazonas EP", "1790012345001", "Quito"));
            empresaRepo.save(new EmpresaOperadora("ENAP Sipetrol", "1790098765001", "Quito"));
            empresaRepo.save(new EmpresaOperadora("Repsol Ecuador", "1790045678001", "Quito"));
        }

        if (servicioRepo.count() == 0) {
            servicioRepo.save(new ServicioPetrolero("Mantenimiento de equipos", "Mantenimiento preventivo y correctivo en campo", new BigDecimal("1200.00")));
            servicioRepo.save(new ServicioPetrolero("Transporte especializado", "Logística de equipos y materiales en zona operativa", new BigDecimal("900.00")));
            servicioRepo.save(new ServicioPetrolero("Soporte HSE", "Acompañamiento en seguridad industrial y ambiente", new BigDecimal("650.00")));
        }
    }
}

