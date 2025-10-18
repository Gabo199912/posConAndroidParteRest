package org.pos.posconandroid.DTO;

import org.pos.posconandroid.modelos.ProductosModelo;
import org.pos.posconandroid.modelos.VentaModelo;

import java.math.BigDecimal;

public class DetalleVentaDTO {
    private ProductosModelo productos;
    private VentaModelo venta;
    private BigDecimal cantidad;

    public ProductosModelo getProductos() {
        return productos;
    }

    public void setProductos(ProductosModelo productos) {
        this.productos = productos;
    }

    public VentaModelo getVenta() {
        return venta;
    }

    public void setVenta(VentaModelo venta) {
        this.venta = venta;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
