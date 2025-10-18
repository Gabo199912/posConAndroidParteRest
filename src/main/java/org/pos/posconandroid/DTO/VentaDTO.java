package org.pos.posconandroid.DTO;

import org.pos.posconandroid.modelos.ClienteModelo;
import org.pos.posconandroid.modelos.UsuarioModelo;

public class VentaDTO {
    private ClienteModelo cliente;
    private UsuarioModelo usuario;

    public ClienteModelo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModelo cliente) {
        this.cliente = cliente;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }
}
