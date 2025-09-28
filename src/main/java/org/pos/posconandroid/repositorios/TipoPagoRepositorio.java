package org.pos.posconandroid.repositorios;

import org.pos.posconandroid.modelos.TipoPagoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoPagoRepositorio extends JpaRepository<TipoPagoModel, Integer> {
    void deleteByNombre(String nombre);
    boolean existsByIdTipoPago(Integer idTipoPago);
}
