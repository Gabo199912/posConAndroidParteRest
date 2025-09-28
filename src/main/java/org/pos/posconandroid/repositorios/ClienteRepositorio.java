package org.pos.posconandroid.repositorios;

import org.pos.posconandroid.modelos.ClienteModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteModelo, Integer> {


        boolean existsByNombre(String nombre);
        boolean existsByDpiNit(String dpiNit);
        boolean existsByEmail(String email);
        void deleteByNombre(String nombre);
}
