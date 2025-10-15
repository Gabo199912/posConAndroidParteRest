package org.pos.posconandroid.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_compra_producto")
public class DetalleCompraProductoModelo {
    @Id
    @Column(name = "id_detalle_compra_producto")
    private Integer idDetalleCompraProducto;



}
