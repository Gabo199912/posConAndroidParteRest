package org.pos.posconandroid.controladores;


import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/porID/{id}")
    public ResponseEntity<List<UsuarioModelo>> obtenerUsuarioPorID(@PathVariable Integer id){
        if(id <= 0 || id.equals(null)) {
            return ResponseEntity.badRequest().build();
        }

        List<UsuarioModelo> usuarios = usuarioServicio.obtenerUsuarios(id);
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id){
        if(id <= 0 || id.equals(null)) {
            return ResponseEntity.badRequest().build();
        }

        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> verificarUsuario(@RequestBody UsuarioModelo usuario){
        boolean ok = usuarioServicio.verificarUsuario(usuario.getNombreUsuario(), usuario.getContraseniaHash());
        try {
            if (ok) {
                UsuarioModelo usuarioLogueado = usuarioServicio.obtenerUsuarioPorNombre(usuario.getNombreUsuario());
                return ResponseEntity.ok(usuarioLogueado);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al verificar usuario");
        }

    }

    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioModelo>> obtenerTodosLosUsuarios(){
        List<UsuarioModelo> usuarios = usuarioServicio.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/crear/usuario")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioModelo usuario){
        System.out.println(usuario.getNombreUsuario());
        System.out.println(usuario.getContraseniaHash());
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getTipoUsuario());


        try {
            usuario.setActivo(true);
            usuarioServicio.guardarUsuario(usuario);
            return ResponseEntity.ok("Usuario creado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al crear el usuario");
        }
    }

}