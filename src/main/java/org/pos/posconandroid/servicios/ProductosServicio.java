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

    public List<ProductosModelo> listarProductosPorNombre(String nombreProducto){
        return productosRepositorio.findByNombreProducto(nombreProducto);
    }

    public boolean buscarPorId(int id){
        boolean ok = productosRepositorio.existsByIdProducto(id);
        return ok;
    }

    public boolean buscarPorNombre(String nombreProducto){
        boolean ok = productosRepositorio.existsByNombreProducto(nombreProducto);
        return ok;
    }

    public void eliminarPorID(int id){
        productosRepositorio.deleteById(id);
    }

}
