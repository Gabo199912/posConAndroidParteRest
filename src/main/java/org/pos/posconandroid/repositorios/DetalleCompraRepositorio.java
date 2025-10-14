package org.pos.posconandroid.repositorios;

import org.pos.posconandroid.modelos.DetalleCompraModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraRepositorio extends JpaRepository<DetalleCompraModelo, Integer> {
}
