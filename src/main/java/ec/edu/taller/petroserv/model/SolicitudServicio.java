package ec.edu.taller.petroserv.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "solicitud_servicio")
public class SolicitudServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "servicio_id", nullable = false)
    private ServicioPetrolero servicio;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 30)
    private String estado; // REGISTRADA, EN_PROCESO, CERRADA

    protected SolicitudServicio() {
    }

    public SolicitudServicio(Persona persona, ServicioPetrolero servicio, LocalDate fecha, String estado) {
        this.persona = persona;
        this.servicio = servicio;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public Persona getPersona() { return persona; }
    public ServicioPetrolero getServicio() { return servicio; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }

    public void setPersona(Persona persona) { this.persona = persona; }
    public void setServicio(ServicioPetrolero servicio) { this.servicio = servicio; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setEstado(String estado) { this.estado = estado; }
}

