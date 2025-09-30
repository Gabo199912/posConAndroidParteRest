package org.pos.posconandroid.repositorios;

import org.pos.posconandroid.modelos.ProductosModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepositorio extends JpaRepository<ProductosModelo, Integer> {
    boolean existsByIdProducto(Integer idProducto);
    boolean existsByNombreProducto(String nombreProducto);
    List<ProductosModelo> findByNombreProducto(String nombreProducto);
    List<ProductosModelo> findAllByIdProducto(List<Integer> idProducto);
}
