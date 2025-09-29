package org.pos.posconandroid.controladores;

import org.pos.posconandroid.modelos.ProveedoresModelo;
import org.pos.posconandroid.servicios.ProveedoresServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedoresControlador {
    private final ProveedoresServicio proveedoresServicio;

    @Autowired
    public ProveedoresControlador(ProveedoresServicio proveedoresServicio) {
        this.proveedoresServicio = proveedoresServicio;
    }

    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    @GetMapping("/vendedor/listar")
    public ResponseEntity<List<ProveedoresModelo>> obtenerTodosLosProveedores(){
        List<ProveedoresModelo> listaProveedores = proveedoresServicio.listarProveedores();
        return ResponseEntity.status(HttpStatus.OK).body(listaProveedores);
    }

    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    @GetMapping("/vendedor/listar/porNombre/{nombreProveedor}")
    public ResponseEntity<?> obtenerProveedoresPorNombre(@PathVariable String nombreProveedor){
        String nombreProveedorRecibido = nombreProveedor.trim();
        boolean ok = proveedoresServicio.buscarPorNombre(nombreProveedorRecibido);

        if (!ok){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no existe el proveedor con ese nombre");
        }
        List<ProveedoresModelo> listaProveedores =  proveedoresServicio.listarProveedoresPorNombre(nombreProveedor);
        return ResponseEntity.status(HttpStatus.OK).body(listaProveedores);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/administrador/crear")
    public ResponseEntity<?> crearProveedor(@RequestBody ProveedoresModelo proveedoresModelo){
        String nombreProveedor = proveedoresModelo.getNombreProveedor();
        String Telefono = proveedoresModelo.getTelefono();
        String email = proveedoresModelo.getEmail();
        String direccion = proveedoresModelo.getDireccion();

        System.out.println(nombreProveedor + " " + Telefono + " " + email + " " + direccion);

        if (nombreProveedor == null || Telefono == null || email == null || direccion == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("complete todos los campos");
        }
        proveedoresModelo.setActivo(true);
        proveedoresServicio.crearProveedor(proveedoresModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Proveedor creado con exito");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/administrador/actualizar/{idProveedor}")
    public ResponseEntity<?> actualizarProveedor(@PathVariable Integer idProveedor, @RequestBody ProveedoresModelo proveedoresModelo){
        boolean ok = proveedoresServicio.buscarPorId(idProveedor);
        if (!ok){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El proveedor no existe");
        }

        proveedoresModelo.setIdProveedor(idProveedor);
        proveedoresModelo.setActivo(true);
        proveedoresServicio.crearProveedor(proveedoresModelo);
        return ResponseEntity.status(HttpStatus.OK).body("Proveedor actualizado con exito");
    }


    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/administrador/eliminar/{idProveedor}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Integer idProveedor){
        if(idProveedor == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del proveedor no puede estar vacio");
        }
        proveedoresServicio.eliminarProveedor(idProveedor);
        return ResponseEntity.status(HttpStatus.OK).body("Proveedor eliminado con exito");
    }
}
