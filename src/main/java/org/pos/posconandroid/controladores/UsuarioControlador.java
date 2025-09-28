package org.pos.posconandroid.controladores;


import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.servicios.UsuarioLoginServicio;
import org.pos.posconandroid.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }


    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/administrador/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioModelo usuarioModelo){
        boolean okUsurario = usuarioServicio.verificarUsuarioExistente(usuarioModelo.getNombreUsuario());
        boolean okCorreo = usuarioServicio.verificarCorreoExistente(usuarioModelo.getEmail());
        String tipoUsuario = usuarioModelo.getTipoUsuario().toUpperCase();

        if (okUsurario || okCorreo){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario o correo ya existe en el sistema, intente con otros datos");
        }

        if (usuarioModelo.getNombreUsuario().isEmpty() || usuarioModelo.getNombre().isEmpty() || usuarioModelo.getTipoUsuario().isEmpty()
        || usuarioModelo.getEmail().isEmpty() || usuarioModelo.getContraseniaHash().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos no pueden estar vacios");
        }


        usuarioModelo.setActivo(true);
        usuarioModelo.setTipoUsuario("ROLE_" + tipoUsuario);
        usuarioServicio.guardarUsuario(usuarioModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con exito");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/administrador/eliminar/{nombreUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String nombreUsuario){
        boolean ok = usuarioServicio.verificarUsuarioExistente(nombreUsuario);

        if (ok){
            usuarioServicio.eliminarUsuario(nombreUsuario);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado con exito");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EL USUARIO NO SE ENCONTRO EN EL SISTEMA");
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/administrador/tipo/{tipoUsuario}")
    public ResponseEntity<List<UsuarioModelo>> todosTipoUsuario(@PathVariable String tipoUsuario) {
        String tipoUsuarioRecibido = tipoUsuario.toUpperCase();
        List<UsuarioModelo> usuarioModelos = usuarioServicio.listarPorTipoUsuario(tipoUsuarioRecibido);
        return ResponseEntity.ok().body(usuarioModelos);
    }

    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    @GetMapping("/vendedor/todos")
    public ResponseEntity<List<UsuarioModelo>> obtenerTodosLosUsuarios(){
        List<UsuarioModelo> usuarios = usuarioServicio.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }
}