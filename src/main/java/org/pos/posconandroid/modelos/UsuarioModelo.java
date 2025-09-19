package org.pos.posconandroid.modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class  UsuarioModelo {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(nullable = false)
    @JsonProperty("nombre")
    private String nombre;

    @Column(nullable = false, name = "nombre_usuario")
    @JsonProperty("nombre_usuario")
    private String nombreUsuario;

    @Column(nullable = false, unique = true)
    @JsonProperty("email")
    private String email;

    @Column(nullable = false, name = "contrasenia_hash")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "contrasenia_hash")
    private String contraseniaHash;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "activo")
    private Boolean activo;

    @Column(nullable = false, name = "tipo_usuario")
    @JsonProperty("tipo_usuario")
    private String tipoUsuario;



    public UsuarioModelo() {
    }

    public UsuarioModelo(String nombre, String nombreUsuario, String email) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    public UsuarioModelo(String nombre, String nombreUsuario, String email, String contraseniaHash, String tipoUsuario) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contraseniaHash = contraseniaHash;
        this.tipoUsuario = tipoUsuario;
    }

    public UsuarioModelo(int idUsuario, String nombre, String nombreUsuario, String contraseniaHash, String email, boolean activo, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contraseniaHash = contraseniaHash;
        this.email = email;
        this.activo = activo;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseniaHash() {
        return contraseniaHash;
    }

    public void setContraseniaHash(String contraseniaHash) {
        this.contraseniaHash = contraseniaHash;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
