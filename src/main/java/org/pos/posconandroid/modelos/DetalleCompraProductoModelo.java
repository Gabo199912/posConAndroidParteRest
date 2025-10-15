package org.pos.posconandroid.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_compra_producto")
public class DetalleCompraProductoModelo {
    @Id
    @Column(name = "id_detalle_compra_producto")
    private Integer idDetalleCompraProducto;

    @ManyToOne
    @JoinColumn(name = "id_detalle")
    private DetalleCompraModelo detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductosModelo producto;


    public Integer getIdDetalleCompraProducto() {
        return idDetalleCompraProducto;
    }

    public void setIdDetalleCompraProducto(Integer idDetalleCompraProducto) {
        this.idDetalleCompraProducto = idDetalleCompraProducto;
    }

    public DetalleCompraModelo getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleCompraModelo detalle) {
        this.detalle = detalle;
    }

    public ProductosModelo getProducto() {
        return producto;
    }

    public void setProducto(ProductosModelo producto) {
        this.producto = producto;
    }
}
