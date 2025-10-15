package org.pos.posconandroid.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompraModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deatlle_compra")
    private Integer idDetalleCompra;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private ProveedoresModelo proveedores;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "total")
    private BigDecimal total;

    public Integer getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Integer idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public ProveedoresModelo getProveedores() {
        return proveedores;
    }

    public void setProveedores(ProveedoresModelo proveedores) {
        this.proveedores = proveedores;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
