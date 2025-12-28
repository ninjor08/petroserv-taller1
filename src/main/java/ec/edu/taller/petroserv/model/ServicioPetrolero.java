package ec.edu.taller.petroserv.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "servicio_petrolero")
public class ServicioPetrolero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 90)
    private String nombre;

    @Column(nullable = false, length = 180)
    private String descripcion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal tarifaBaseUsd;

    protected ServicioPetrolero() {
    }

    public ServicioPetrolero(String nombre, String descripcion, BigDecimal tarifaBaseUsd) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifaBaseUsd = tarifaBaseUsd;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getTarifaBaseUsd() { return tarifaBaseUsd; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setTarifaBaseUsd(BigDecimal tarifaBaseUsd) { this.tarifaBaseUsd = tarifaBaseUsd; }
}

