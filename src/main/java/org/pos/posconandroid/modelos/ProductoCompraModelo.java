package org.pos.posconandroid.modelos;

public class ProductoCompraModelo {
    private ProductosModelo producto;
    private Integer cantidad;

    public ProductoCompraModelo(ProductosModelo producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductosModelo getProducto() {
        return producto;
    }

    public void setProducto(ProductosModelo producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
