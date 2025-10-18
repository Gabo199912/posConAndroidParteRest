package org.pos.posconandroid.modelos;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "detalle_venta")
public class DetalleVentaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private int idDetalle;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name="id_producto"))
    private ProductosModelo productos;

    @OneToOne
    @JoinColumn(name = "id_venta", nullable = false, foreignKey = @ForeignKey(name="id_venta"))
    private VentaModelo venta;



    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public VentaModelo getVenta() {
        return venta;
    }

    public void setVenta(VentaModelo venta) {
        this.venta = venta;
    }

    public ProductosModelo getProductos() {
        return productos;
    }

    public void setProductos(ProductosModelo productos) {
        this.productos = productos;
    }
}
