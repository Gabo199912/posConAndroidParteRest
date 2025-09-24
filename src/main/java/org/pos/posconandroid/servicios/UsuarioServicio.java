package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {this.usuarioRepositorio = usuarioRepositorio;}

    public List<UsuarioModelo> listarPorTipoUsuario(String tipoUsuario){
        List<UsuarioModelo> usuarioModelos = new ArrayList<>();

        return  usuarioRepositorio.findByTipoUsuario(tipoUsuario);
    }


    public List<UsuarioModelo> obtenerTodos(){
        return usuarioRepositorio.findAll();
    }




}
