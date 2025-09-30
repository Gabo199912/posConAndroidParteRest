package org.pos.posconandroid.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "detalle_compra_producto")
public class DetalleCompraProductoModelo {

    @Id
    @Column(name = "id_detalle_compra_producto")
    private Integer idDetalleCompra;

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "cantidad")
    private List<Integer> cantidad;

    public Integer getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Integer idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public List<Integer> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<Integer> cantidad) {
        this.cantidad = cantidad;
    }
}
