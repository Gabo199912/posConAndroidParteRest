package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.DetalleCompraModelo;
import org.pos.posconandroid.repositorios.DetalleCompraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCompraServicio {
    private final DetalleCompraRepositorio detalleCompraRepositorio;

    @Autowired
    public DetalleCompraServicio(DetalleCompraRepositorio detalleCompraRepositorio) {
        this.detalleCompraRepositorio = detalleCompraRepositorio;
    }
    public void guardarDetalle(DetalleCompraModelo detalleCompraModelo){
        detalleCompraRepositorio.save(detalleCompraModelo);
    }


}
