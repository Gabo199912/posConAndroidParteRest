package org.pos.posconandroid.controladores;


import org.pos.posconandroid.modelos.ClienteModelo;
import org.pos.posconandroid.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteControlador {
    private final ClienteServicio clienteServicio;

    @Autowired
    public ClienteControlador(ClienteServicio clienteServicio) {this.clienteServicio = clienteServicio;}

    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    @PostMapping("/vendedor/crear")
    public ResponseEntity<?> crearCliente(@RequestBody ClienteModelo clienteModelo){
        String nombre = clienteModelo.getNombre();
        String dpiNit = clienteModelo.getDpiNit();
        boolean ok = clienteServicio.verificarClienteExistente(nombre);
        boolean okDpiNit = clienteServicio.buscarPorDpiNit(dpiNit);


        if (ok || okDpiNit){
            return ResponseEntity.status(409).body("El cliente ya existe en el sistema");
        }

        if (clienteModelo.getEmail() == null || clienteModelo.getDpiNit() == null || clienteModelo.getNombre() == null){
            return ResponseEntity.status(400).body("Los campos no pueden estar vacios");
        }

        if (dpiNit == ""){
            clienteModelo.setDpiNit("C/F");
        }
        clienteServicio.guardarCliente(clienteModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado con exito");
    }

    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    @PostMapping("/vendedor/actualizar/{idCliente}")
    public ResponseEntity<?> actualizarDatos(@PathVariable Integer idCliente, @RequestBody ClienteModelo clienteModelo){
        Integer id = idCliente;
        boolean okDpiNit = clienteServicio.buscarPorDpiNit(clienteModelo.getDpiNit());
        boolean okEmail = clienteServicio.buscarPorEmail(clienteModelo.getEmail());

        if (okDpiNit || okEmail){
            return ResponseEntity.status(409).body("El cliente ya existe en el sistema");
        }

        clienteModelo.setIdCliente(id);
        clienteServicio.guardarCliente(clienteModelo);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente actualizado con exito");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/administrador/eliminar/{nombre}")
    public ResponseEntity<?> eliminarCliente(@PathVariable String nombre){
        if (nombre == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario no puede estar vacio");
        }
        clienteServicio.eliminarCliente(nombre);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado con exito");
    }

    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    @GetMapping("/vendedor/listar")
    public ResponseEntity<List<ClienteModelo>> obtenerTodosLosClientes(){
        List<ClienteModelo> listaCliente = clienteServicio.obtenerClientes();
        return ResponseEntity.ok(listaCliente);
    }
}
