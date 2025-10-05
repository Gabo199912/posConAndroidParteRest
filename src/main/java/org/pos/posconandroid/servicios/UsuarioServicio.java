package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return  usuarioRepositorio.findByTipoUsuario(tipoUsuario);
    }


    public UsuarioModelo guardarUsuario(UsuarioModelo usuarioModelo){
        String contraseñaLista = passwordEncoder.encode(usuarioModelo.getContraseniaHash());
        usuarioModelo.setContraseniaHash(contraseñaLista);
        return usuarioRepositorio.save(usuarioModelo);
    }


    public List<UsuarioModelo> obtenerTodos(){
        return usuarioRepositorio.findAll();
    }

    @Transactional
    public void eliminarUsuario(String nombreUsuario){
        usuarioRepositorio.deleteByNombreUsuario(nombreUsuario);
    }

    public void eliminarUsuarioPorId(int id){
        usuarioRepositorio.deleteById(id);
    }

    public boolean verificarCorreoExistente(String email){
        boolean ok = usuarioRepositorio.existsByEmail(email);
        return ok;
    }

    public boolean verificarUsuarioExistente(String nombreUsuario){
        boolean ok = usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
        return ok;
    }

    public boolean verificarUsuarioPorId(int id){
        boolean ok = usuarioRepositorio.existsByIdUsuario(id);
        return ok;
    }




}
