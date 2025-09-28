package org.pos.posconandroid.modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class ProductosModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @Column(nullable = false, name = "nombre_producto")
    @JsonProperty("nombre_producto")
    private String nombreProducto;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false, name = "precio_costo")
    @JsonProperty("precio_costo")
    private BigDecimal precioCosto;

    @Column(nullable = false, name = "precio_venta")
    @JsonProperty("precio_venta")
    private BigDecimal precioVenta;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false, name = "codigo_barras")
    @JsonProperty("codigo_barras")
    private String codigoBarras;


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(BigDecimal precioCosto) {
        this.precioCosto = precioCosto;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}
