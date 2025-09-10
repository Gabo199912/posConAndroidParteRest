package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {this.usuarioRepositorio = usuarioRepositorio;}


    public UsuarioModelo guardarUsuario(UsuarioModelo usuario)  {
        String contraseniaCodificada = passwordEncoder.encode(usuario.getContraseniaHash());
        usuario.setContraseniaHash(contraseniaCodificada);
        return usuarioRepositorio.save(usuario);
    }

    public boolean verificarUsuario(String nombreUsuario, String contraseniaHash){
        boolean ok = false;
        List<UsuarioModelo> usuarios = usuarioRepositorio.findBynombreUsuario(nombreUsuario);
        if(!usuarios.isEmpty()){
            if(passwordEncoder.matches(contraseniaHash, usuarios.get(0).getContraseniaHash()) && nombreUsuario.equals(usuarios.get(0).getNombreUsuario())){
                ok = true;
            }
        }
        return ok;
    }

    public List<UsuarioModelo> obtenerUsuarios(int id){
        return usuarioRepositorio.findByIdUsuario(id);
    }

    public List<UsuarioModelo> obtenerTodos(){
        return usuarioRepositorio.findAll();
    }

    public void eliminarUsuario(int id){
            usuarioRepositorio.deleteById(id);
    }

    public UsuarioModelo obtenerUsuarioPorNombre(String nombreUsuario){
        return usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }


}
