package org.pos.posconandroid.controladores;


import org.pos.posconandroid.modelos.ProductosModelo;
import org.pos.posconandroid.servicios.ProductosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoControlador {
    private final ProductosServicio productosServicio;

    @Autowired
    public ProductoControlador(ProductosServicio productosServicio) {
        this.productosServicio = productosServicio;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/administrador/crear")
    public ResponseEntity<?> crearUsuarios(@RequestBody ProductosModelo productosModelo){
        String nombreProducto = productosModelo.getNombreProducto();
        BigDecimal precioCosto = productosModelo.getPrecioCosto();
        BigDecimal precioVenta = productosModelo.getPrecioVenta();
        productosModelo.setStock(0);
        String codigoBarras = productosModelo.getCodigoBarras();


        if (nombreProducto == null || precioCosto == null || precioVenta == null){
            return ResponseEntity.status(400).body("Los campos no pueden estar vacios");
        }

        if (codigoBarras == null || codigoBarras.isEmpty()){
            productosServicio.crearProducto(productosModelo);
            return ResponseEntity.status(201).body("Producto creado con exito 'SIN CODIGO BARRAS'");
        }

        productosServicio.crearProducto(productosModelo);
        return ResponseEntity.status(201).body("Producto creado con exito");

    }

    @PreAuthorize("hasRole('VENDEDOR')")
    @PostMapping("/vendedor/actualizar/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Integer idProducto, @RequestBody ProductosModelo productosModelo){
        boolean ok = productosServicio.buscarPorId(idProducto);
        if (!ok){
            return ResponseEntity.status(404).body("El producto no existe");
        }
        productosModelo.setIdProducto(idProducto);
        productosServicio.crearProducto(productosModelo);
        return ResponseEntity.status(200).body("Producto actualizado con exito");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/administrador/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer idProducto){
        boolean ok = productosServicio.buscarPorId(idProducto);
        if (!ok){
            return ResponseEntity.status(404).body("El producto no existe");
        }

        productosServicio.eliminarPorID(idProducto);
        return ResponseEntity.status(200).body("Producto eliminado con exito");
    }

    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    @GetMapping("/vendedor/listar")
    public ResponseEntity<List<ProductosModelo>> obtenerTodosLosProductos(){
        return ResponseEntity.ok(productosServicio.listarProductos());
    }


}
