package ec.edu.taller.petroserv.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa_operadora")
public class EmpresaOperadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String razonSocial;

    @Column(nullable = false, length = 20, unique = true)
    private String ruc;

    @Column(nullable = false, length = 80)
    private String ciudad;

    protected EmpresaOperadora() {
    }

    public EmpresaOperadora(String razonSocial, String ruc, String ciudad) {
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.ciudad = ciudad;
    }

    public Long getId() { return id; }
    public String getRazonSocial() { return razonSocial; }
    public String getRuc() { return ruc; }
    public String getCiudad() { return ciudad; }

    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
}

