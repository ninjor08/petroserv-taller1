package ec.edu.taller.petroserv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(regexp = "[0-9]{10}", message = "La cédula debe tener 10 dígitos")
    private String cedula;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 3, max = 100)
    private String nombres;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "El celular es obligatorio")
    @Pattern(regexp = "09[0-9]{8}", message = "El celular debe iniciar con 09 y tener 10 dígitos")
    private String celular;

    private int edad;

    @ManyToOne
    @JoinColumn(name = "empresa_operadora_id")
    private EmpresaOperadora empresaOperadora;

    // CAMBIO IMPORTANTE
    // Constructor vacío PUBLIC (requerido por JPA y por Spring MVC)
    public Persona() {
    }

    // Getters y setters (simples, sin Lombok para que se vea estudiantil)

    public Long getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public EmpresaOperadora getEmpresaOperadora() {
        return empresaOperadora;
    }

    public void setEmpresaOperadora(EmpresaOperadora empresaOperadora) {
        this.empresaOperadora = empresaOperadora;
    }
}
