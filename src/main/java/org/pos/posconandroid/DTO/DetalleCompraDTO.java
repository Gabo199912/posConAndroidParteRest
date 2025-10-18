package org.pos.posconandroid.DTO;

import org.pos.posconandroid.modelos.DetalleCompraModelo;
import org.pos.posconandroid.modelos.DetalleCompraProductoModelo;
import org.pos.posconandroid.modelos.ProductosModelo;
import org.pos.posconandroid.modelos.ProveedoresModelo;

import java.util.List;

public class DetalleCompraDTO {
    public DetalleCompraModelo detalleCompraModelo;
    public ProveedoresModelo proveedoresModelo;
    public List<ProductosModelo> listaProductosModelo;

    public DetalleCompraModelo getDetalleCompraModelo() {
        return detalleCompraModelo;
    }

    public void setDetalleCompraModelo(DetalleCompraModelo detalleCompraModelo) {
        this.detalleCompraModelo = detalleCompraModelo;
    }



    public ProveedoresModelo getProveedoresModelo() {
        return proveedoresModelo;
    }

    public void setProveedoresModelo(ProveedoresModelo proveedoresModelo) {
        this.proveedoresModelo = proveedoresModelo;
    }

    public List<ProductosModelo> getListaProductosModelo() {
        return listaProductosModelo;
    }

    public void setListaProductosModelo(List<ProductosModelo> listaProductosModelo) {
        this.listaProductosModelo = listaProductosModelo;
    }
}
