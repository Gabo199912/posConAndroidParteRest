package org.pos.posconandroid.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompraModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra")
    private int idDetalleCompra;

    @ManyToMany
    @JoinTable(name = "detalle_compra_producto", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_detalle_compra"),
            inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private List<ProductosModelo> productos;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name="id_proveedor"))
    private ProveedoresModelo proveedores;


    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "total")
    private BigDecimal totalCompar;


    public DetalleCompraModelo() {
    }

    public DetalleCompraModelo(List<ProductosModelo> productos, ProveedoresModelo proveedores, int cantidad, BigDecimal totalCompar) {
        this.productos = productos;
        this.proveedores = proveedores;
        this.cantidad = cantidad;
        this.totalCompar = totalCompar;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public List<ProductosModelo> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductosModelo> productos) {
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

    public BigDecimal getTotalCompar() {
        return totalCompar;
    }

    public void setTotalCompar(BigDecimal totalCompar) {
        this.totalCompar = totalCompar;
    }
}
