package org.pos.posconandroid.servicios;

import org.pos.posconandroid.modelos.ClienteModelo;
import org.pos.posconandroid.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    @Autowired
    public ClienteServicio(ClienteRepositorio clienteRepositorio) {this.clienteRepositorio = clienteRepositorio;}


    public ClienteModelo guardarCliente(ClienteModelo clienteModelo) {
        return clienteRepositorio.save(clienteModelo);
    }

    public void eliminarCliente(String nombre){
        clienteRepositorio.deleteByNombre(nombre);
    }

    public boolean verificarClienteExistente(String nombre){
        boolean ok = clienteRepositorio.existsByNombre(nombre);
        return ok;
    }

    public boolean buscarPorId(int id){
        boolean ok = clienteRepositorio.existsById(id);
        return ok;
    }

    public boolean buscarPorDpiNit(String dpiNit){
        boolean ok = clienteRepositorio.existsByDpiNit(dpiNit);
        return ok;
    }

    public boolean buscarPorEmail(String email){
        boolean ok = clienteRepositorio.existsByEmail(email);
        return ok;
    }

    public List<ClienteModelo> obtenerClientes(){
        return clienteRepositorio.findAll();
    }
}
