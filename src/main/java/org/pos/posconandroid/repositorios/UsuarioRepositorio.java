package org.pos.posconandroid.repositorios;

import org.pos.posconandroid.modelos.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Integer> {
    List<UsuarioModelo> findByIdUsuario(Integer nombreUsuario);

    List<UsuarioModelo> findBynombreUsuario(String nombreUsuario);

    UsuarioModelo findByNombreUsuario(String nombreUsuario);

}
