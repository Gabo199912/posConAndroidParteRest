package org.pos.posconandroid.controladores;

import org.pos.posconandroid.DTO.DetalleCompraDTO;
import org.pos.posconandroid.modelos.DetalleCompraModelo;
import org.pos.posconandroid.modelos.DetalleCompraProductoModelo;
import org.pos.posconandroid.modelos.ProductosModelo;
import org.pos.posconandroid.modelos.ProveedoresModelo;
import org.pos.posconandroid.servicios.DetalleCompraProductoServicio;
import org.pos.posconandroid.servicios.DetalleCompraServicio;
import org.pos.posconandroid.servicios.ProductosServicio;
import org.pos.posconandroid.servicios.ProveedoresServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/compras")
public class DetalleCompraProductoControlador {
    private DetalleCompraServicio detalleCompraServicio;
    private DetalleCompraProductoServicio detalleCompraProductoServicio;
    private ProveedoresServicio  proveedoresServicio;
    private ProductosServicio productosServicio;

    @Autowired
    public DetalleCompraProductoControlador(DetalleCompraServicio detalleCompraServicio,
                                            DetalleCompraProductoServicio detalleCompraProductoServicio,
                                            ProveedoresServicio proveedoresServicio,
                                            ProductosServicio productosServicio) {
        this.detalleCompraServicio = detalleCompraServicio;
        this.detalleCompraProductoServicio = detalleCompraProductoServicio;
        this.proveedoresServicio = proveedoresServicio;
        this.productosServicio = productosServicio;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/administrador/proveedores")
    public ResponseEntity<?> proveedores() {
        List<ProveedoresModelo> listaProveedores = proveedoresServicio.listarProveedores();
        return ResponseEntity.ok(listaProveedores);
    }



    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/administrador/generarCompra")
    public ResponseEntity<?> generarCompra(@RequestBody DetalleCompraDTO  detalleCompraDTO) {

        if (detalleCompraDTO == null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "La compra no puede ser nula");
            return ResponseEntity.badRequest().body(map);
        }

        ProveedoresModelo proveedor = proveedoresServicio.buscarProveedorPorId(detalleCompraDTO.proveedoresModelo.getIdProveedor());

        if (proveedor == null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "El proveedor no puede ser nulo");
            return ResponseEntity.badRequest().body(map);
        }

        DetalleCompraModelo detalleCompraModelo = new DetalleCompraModelo();
        detalleCompraModelo.setProveedores(proveedor);
        detalleCompraModelo.setCantidad(detalleCompraDTO.detalleCompraModelo.getCantidad());
        detalleCompraModelo.setTotal(detalleCompraDTO.detalleCompraModelo.getTotal());

        if (detalleCompraModelo == null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "todos los campos deben estar llenos ");
            return ResponseEntity.badRequest().body(map);
        }

        detalleCompraServicio.guardarDetalle(detalleCompraModelo);

        for (ProductosModelo productos: detalleCompraDTO.listaProductosModelo){
            DetalleCompraProductoModelo detalleCompraProducto = new DetalleCompraProductoModelo();
            detalleCompraProducto.setDetalle(detalleCompraModelo);
            detalleCompraProducto.setProducto(productos);

            detalleCompraProductoServicio.guardarDetalle(detalleCompraProducto);
        }

        return ResponseEntity.ok(detalleCompraModelo);
    }
}
