package org.pos.posconandroid.repositorios;

import org.pos.posconandroid.modelos.ProveedoresModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedoresRepositorio extends JpaRepository<ProveedoresModelo, Integer> {
    boolean existsByIdProveedor(Integer idProveedor);
    boolean existsByNombreProveedor(String NombreProveedor);
    List<ProveedoresModelo> findByNombreProveedor(String nombreProveedor);
}
