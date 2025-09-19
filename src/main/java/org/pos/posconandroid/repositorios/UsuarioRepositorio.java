package org.pos.posconandroid.repositorios;

import org.pos.posconandroid.modelos.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Integer> {
    List<UsuarioModelo> findBynombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);

    Optional<UsuarioModelo> findByNombreUsuario(String nombreUsuario);

}
