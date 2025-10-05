package org.pos.posconandroid.servicios;

import jakarta.annotation.PostConstruct;
import org.pos.posconandroid.modelos.UsuarioModelo;
import org.pos.posconandroid.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UsuarioAdministradorInicializador  implements CommandLineRunner {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!usuarioRepositorio.existsByNombreUsuario("administrador")){
            UsuarioModelo administrador = new UsuarioModelo();
            administrador.setNombre("administrador");
            administrador.setNombreUsuario("administrador");
            administrador.setContraseniaHash(passwordEncoder.encode("1234"));
            administrador.setEmail("administrador@gmail.com");
            administrador.setTipoUsuario("ROLE_ADMINISTRADOR");
            administrador.setActivo(true);
            usuarioRepositorio.save(administrador);
        }
    }
}
