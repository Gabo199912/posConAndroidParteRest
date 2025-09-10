package org.pos.posconandroid.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "venta")
public class VentaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private int idVenta;

    @Column(nullable = false, name = "total_venta")
    private BigDecimal totalVenta;


    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false, foreignKey = @ForeignKey(name="id_cliente"))
    private ClienteModelo cliente;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "id_usuario"))
    private UsuarioModelo usuario;

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }
}
