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

import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }


    //@PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/administrador/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioModelo usuarioModelo){
        boolean okUsurario = usuarioServicio.verificarUsuarioExistente(usuarioModelo.getNombreUsuario());
        boolean okCorreo = usuarioServicio.verificarCorreoExistente(usuarioModelo.getEmail());
        String tipoUsuario = usuarioModelo.getTipoUsuario().toUpperCase();

        System.out.println(usuarioModelo.getNombreUsuario() + " " + usuarioModelo.getNombre() + " " + usuarioModelo.getTipoUsuario() + " " + usuarioModelo.getEmail() + " " + usuarioModelo.getContraseniaHash());

        if (okUsurario || okCorreo){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("nombre_usuario", "El usuario o correo ya existe en el sistema, intente con otros datos");
            respuesta.put("Respuesta", "CONFLICTO");


            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }

        if (usuarioModelo.getNombreUsuario().isEmpty() || usuarioModelo.getNombre().isEmpty() || usuarioModelo.getTipoUsuario().isEmpty()
        || usuarioModelo.getEmail().isEmpty() || usuarioModelo.getContraseniaHash().isEmpty()){
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("nombre_usuario", "Los campos no pueden estar vacios");
            respuesta.put("Respuesta", "BAD_REQUEST");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }


        usuarioModelo.setActivo(true);
        usuarioModelo.setTipoUsuario("ROLE_" + tipoUsuario);
        usuarioServicio.guardarUsuario(usuarioModelo);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombre_usuario", usuarioModelo.getNombreUsuario());
        respuesta.put("Respuesta", "Usuario Creado con exito");

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
        @DeleteMapping("/administrador/eliminar/{nombreUsuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String nombreUsuario){
        boolean ok = usuarioServicio.verificarUsuarioExistente(nombreUsuario);
        System.out.println(nombreUsuario);

        if (ok){
            usuarioServicio.eliminarUsuario(nombreUsuario);
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("nombre_usuario", "usuario eliminado con exito");
            respuesta.put("Respuesta", "OK");
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }else {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("nombre_usuario", "El usuario no existe en el sistema");
            respuesta.put("Respuesta", "NOT_FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/administrador/tipo/{tipoUsuario}")
    public ResponseEntity<List<UsuarioModelo>> todosTipoUsuario(@PathVariable String tipoUsuario) {
        String tipoUsuarioRecibido = tipoUsuario.toLowerCase();
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