package org.pos.posconandroid.controladores;


import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.servicios.UsuarioLoginServicio;
import org.pos.posconandroid.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/todos/tipoUsuario")
    public ResponseEntity<List<UsuarioModelo>>  todosTipoUsuario(@RequestBody String tipoUsuario) {
        List<UsuarioModelo> usuarioModelos = usuarioServicio.listarPorTipoUsuario(tipoUsuario);
        return ResponseEntity.ok().body(usuarioModelos);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioModelo>> obtenerTodosLosUsuarios(){
        List<UsuarioModelo> usuarios = usuarioServicio.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }
}