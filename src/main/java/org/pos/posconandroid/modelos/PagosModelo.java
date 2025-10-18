package org.pos.posconandroid.modelos;


import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class PagosModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pago", nullable = false, foreignKey = @ForeignKey(name="id_tipo_pago"))
    private TipoPagoModel tipoPago;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false, foreignKey = @ForeignKey(name="id_venta"))
    private VentaModelo ventas;


    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public TipoPagoModel getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPagoModel tipoPago) {
        this.tipoPago = tipoPago;
    }

    public VentaModelo getVentas() {
        return ventas;
    }

    public void setVentas(VentaModelo ventas) {
        this.ventas = ventas;
    }
}
