package org.pos.posconandroid.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra")
    private int idDetalleCompra;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name="id_producto"))
    private ProductosModelo productos;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name="id_proveedor"))
    private ProveedoresModelo proveedores;
    private int cantidad;


    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public ProductosModelo getProductos() {
        return productos;
    }

    public void setProductos(ProductosModelo productos) {
        this.productos = productos;
    }

    public ProveedoresModelo getProveedores() {
        return proveedores;
    }

    public void setProveedores(ProveedoresModelo proveedores) {
        this.proveedores = proveedores;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
