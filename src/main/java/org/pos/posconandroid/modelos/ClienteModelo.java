package org.pos.posconandroid.modelos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class ClienteModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    @JsonProperty("id_cliente")
    private int idCliente;

    @Column(nullable = true)
    private String nombre;


    @Column(nullable = true, unique = true, name = "dpi_nit")
    @JsonProperty("dpi_nit")
    private String dpiNit;

    @Column(nullable = true)
    private String telefono;

    @Column(nullable = true)
    private String email;




    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDpiNit() {
        return dpiNit;
    }

    public void setDpiNit(String dpiNit) {
        this.dpiNit = dpiNit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
