package ar.edu.uncuyo.carrito.controller;

import ar.edu.uncuyo.carrito.dto.carrito.CarritoDetalleDto;
import ar.edu.uncuyo.carrito.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carrito")
public class CarritoController {
    private final CarritoService carritoService;

    @GetMapping
    public ResponseEntity<CarritoDetalleDto> getCarrito() {
        CarritoDetalleDto carrito = carritoService.buscarCarritoDtoUsuarioActual();
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/{articuloId}")
    public ResponseEntity<Void> agregarProducto(@PathVariable Long articuloId) {
        carritoService.addProductToCart(articuloId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{detalleId}")
    public ResponseEntity<Void> removerDetalle(@PathVariable Long detalleId) {
        carritoService.removeItemFromCart(detalleId);
        return ResponseEntity.noContent().build();
    }
}
