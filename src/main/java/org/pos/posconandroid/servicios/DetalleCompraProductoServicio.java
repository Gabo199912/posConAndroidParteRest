package org.pos.posconandroid.servicios;

import org.hibernate.Internal;
import org.pos.posconandroid.modelos.DetalleCompraProductoModelo;
import org.pos.posconandroid.repositorios.DetalleCompraProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCompraProductoServicio {
    private final DetalleCompraProductoRepositorio detalleCompraProductoRepositorio;

    @Autowired
    public DetalleCompraProductoServicio(DetalleCompraProductoRepositorio detalleCompraProductoRepositorio) {
        this.detalleCompraProductoRepositorio = detalleCompraProductoRepositorio;
    }

    public List<DetalleCompraProductoModelo> listarTodos(){
        return detalleCompraProductoRepositorio.findAll();
    }

    public void guardarDetalle(DetalleCompraProductoModelo detalleCompraProductoModelo){
        detalleCompraProductoRepositorio.save(detalleCompraProductoModelo);
    }
}
