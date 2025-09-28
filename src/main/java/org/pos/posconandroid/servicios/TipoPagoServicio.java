package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.TipoPagoModel;
import org.pos.posconandroid.repositorios.TipoPagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagoServicio {

    public final TipoPagoRepositorio tipoPagoRepositorio;

    @Autowired
    public TipoPagoServicio(TipoPagoRepositorio tipoPagoRepositorio) {this.tipoPagoRepositorio = tipoPagoRepositorio;}

    public void guardarTipoPago(TipoPagoModel tipoPagoModel){
        tipoPagoRepositorio.save(tipoPagoModel);
    }

    public void eliminarTipoPago(String nombre){
        tipoPagoRepositorio.deleteByNombre(nombre);
    }

    public List<TipoPagoModel> obtenerTiposPago(){
        return tipoPagoRepositorio.findAll();
    }

    public boolean buscarPorId(int id){
        boolean ok = tipoPagoRepositorio.existsByIdTipoPago(id);
        return ok;
    }
}
