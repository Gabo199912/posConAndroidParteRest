package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.ProductosModelo;
import org.pos.posconandroid.repositorios.ProductosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServicio {
    private final ProductosRepositorio productosRepositorio;

    @Autowired
    public ProductosServicio(ProductosRepositorio productosRepositorio) {
        this.productosRepositorio = productosRepositorio;
    }

    public void crearProducto(ProductosModelo productosModelo){
        productosRepositorio.save(productosModelo);
    }

    public List<ProductosModelo> listarProductos(){
        return productosRepositorio.findAll();
    }

    public boolean buscarPorId(int id){
        boolean ok = productosRepositorio.existsByIdProducto(id);
        return ok;
    }

    public void eliminarPorID(int id){
        productosRepositorio.deleteById(id);
    }

}
