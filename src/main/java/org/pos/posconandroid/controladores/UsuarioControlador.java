package org.pos.posconandroid.controladores;


import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.servicios.UsuarioLoginServicio;
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
    private final UsuarioLoginServicio usuarioLoginServicio;

    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioServicio, UsuarioLoginServicio usuarioLoginServicio) {
        this.usuarioServicio = usuarioServicio;
        this.usuarioLoginServicio = usuarioLoginServicio;
    }




    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioModelo>> obtenerTodosLosUsuarios(){
        List<UsuarioModelo> usuarios = usuarioServicio.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }
}