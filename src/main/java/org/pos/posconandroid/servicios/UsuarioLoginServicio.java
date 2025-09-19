package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsuarioLoginServicio implements UserDetailsService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModelo> userOptional = usuarioRepositorio.findByNombreUsuario(username);

        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuario %s no encontrado", username));
        }

        UsuarioModelo user = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(user.getTipoUsuario())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getNombreUsuario(),   // tu username
                user.getContraseniaHash(),
                user.getActivo(),
                true,
                true,
                true,// tu password encriptada
                authorities);
    }
}
