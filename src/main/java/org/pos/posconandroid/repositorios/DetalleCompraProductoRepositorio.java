package org.pos.posconandroid.repositorios;

import org.pos.posconandroid.modelos.DetalleCompraProductoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraProductoRepositorio extends JpaRepository<DetalleCompraProductoModelo, Integer> {
}
