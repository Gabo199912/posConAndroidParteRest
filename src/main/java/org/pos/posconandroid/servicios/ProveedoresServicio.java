package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.ProveedoresModelo;
import org.pos.posconandroid.repositorios.ProveedoresRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresServicio {
    private final ProveedoresRepositorio proveedoresRepositorio;

    @Autowired
    public ProveedoresServicio(ProveedoresRepositorio proveedoresRepositorio) {
        this.proveedoresRepositorio = proveedoresRepositorio;
    }

    public void crearProveedor(ProveedoresModelo proveedoresModelo){
        proveedoresRepositorio.save(proveedoresModelo);
    }

    public List<ProveedoresModelo> listarProveedores(){
        return proveedoresRepositorio.findAll();
    }

    public List<ProveedoresModelo> listarProveedoresPorNombre(String nombreProveedor){
        return proveedoresRepositorio.findByNombreProveedor(nombreProveedor);
    }

    public void eliminarProveedor(Integer id){
        proveedoresRepositorio.deleteById(id);
    }

    public boolean buscarPorId(int id){
        boolean ok = proveedoresRepositorio.existsByIdProveedor(id);
        return ok;
    }
    public boolean buscarPorNombre(String nombreProveedor){
        boolean ok = proveedoresRepositorio.existsByNombreProveedor(nombreProveedor);
        return ok;
    }
}
