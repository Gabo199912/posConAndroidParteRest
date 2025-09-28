package org.pos.posconandroid.controladores;

import org.pos.posconandroid.modelos.TipoPagoModel;
import org.pos.posconandroid.servicios.TipoPagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TipoPagos")
public class TipoPagosControlador {

    public final TipoPagoServicio tipoPagoServicio;

    @Autowired
    public TipoPagosControlador(TipoPagoServicio tipoPagoServicio) {
        this.tipoPagoServicio = tipoPagoServicio;
    }


    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/administrador/crear/TipoPago")
    public ResponseEntity<?> crearTipoPago(@RequestBody TipoPagoModel tipoPagoModel){
      if (tipoPagoModel.getNombre() == null || tipoPagoModel.getNombre().isEmpty()){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("complete todos los campos");
      }

      tipoPagoModel.setActivo(true);
      tipoPagoServicio.guardarTipoPago(tipoPagoModel);
      return ResponseEntity.status(HttpStatus.CREATED).body("Tipo de pago creado con exito");
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/administrador/actualizar/TipoPago/{idPago}")
    public ResponseEntity<?> actualizarTipoPago(@PathVariable Integer idPago,  @RequestBody TipoPagoModel tipoPagoModel){
        boolean ok = tipoPagoServicio.buscarPorId(idPago);
        if (!ok){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de pago no existe");
        }

        tipoPagoModel.setIdTipoPago(idPago);
        tipoPagoModel.setActivo(true);
        tipoPagoServicio.guardarTipoPago(tipoPagoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de pago actualizado con exito");
    }

    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMINISTRADOR')")
    @GetMapping("/vendedor/listar/TipoPago")
    public ResponseEntity<?> crearTipoPagoVendedor(){
        List<TipoPagoModel> listaTipoPago = tipoPagoServicio.obtenerTiposPago();
        return ResponseEntity.ok(listaTipoPago);
    }
}
