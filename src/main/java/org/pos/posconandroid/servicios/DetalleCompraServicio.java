package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.DetalleCompraModelo;
import org.pos.posconandroid.modelos.ProductoCompraModelo;
import org.pos.posconandroid.modelos.ProductosModelo;
import org.pos.posconandroid.modelos.ProveedoresModelo;
import org.pos.posconandroid.repositorios.DetalleCompraRepositorio;
import org.pos.posconandroid.repositorios.ProductosRepositorio;
import org.pos.posconandroid.repositorios.ProveedoresRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleCompraServicio {
    private final DetalleCompraRepositorio detalleCompraRepositorio;
    private final ProductosRepositorio productosRepositorio;
    private final ProveedoresRepositorio proveedoresRepositorio;

    @Autowired
    public DetalleCompraServicio(DetalleCompraRepositorio detalleCompraRepositorio, ProductosRepositorio productosRepositorio, ProveedoresRepositorio proveedoresRepositorio) {
        this.detalleCompraRepositorio = detalleCompraRepositorio;
        this.productosRepositorio = productosRepositorio;
        this.proveedoresRepositorio = proveedoresRepositorio;
    }

    public void generarCompra(List<Integer> idProducto, Integer idProveedor, List<Integer> cantidad, BigDecimal totalCompra){
        DetalleCompraModelo detalle = new DetalleCompraModelo();

        List<ProductosModelo> productos = productosRepositorio.findAllByIdProducto(idProducto);
        ProveedoresModelo proveedor = proveedoresRepositorio.findByIdProveedor(idProveedor);

        List<ProductoCompraModelo> productosConPrecio = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++){
            productosConPrecio.add(new ProductoCompraModelo(productos.get(i), cantidad.get(i)));
        }

        int cantidadTotal = 0;
        for (int cantidades: cantidad){
            cantidadTotal += cantidades;
        }

        DetalleCompraModelo detalleCompraModelo = new DetalleCompraModelo();
        detalleCompraModelo.setProductos(productos);
        detalleCompraModelo.setProveedores(proveedor);
        detalleCompraModelo.setCantidad(cantidadTotal);
        detalleCompraModelo.setTotalCompar(totalCompra);


        detalleCompraRepositorio.save(detalleCompraModelo);
    }

}
