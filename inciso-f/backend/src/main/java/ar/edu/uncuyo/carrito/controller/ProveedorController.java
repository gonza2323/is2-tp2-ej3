package ar.edu.uncuyo.carrito.controller;

import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorCreateDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorDetailDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorSummaryDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorUpdateDto;
import ar.edu.uncuyo.carrito.service.ProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController extends BaseController<Long, ProveedorDetailDto, ProveedorSummaryDto, ProveedorCreateDto, ProveedorUpdateDto, ProveedorService> {

    public ProveedorController(ProveedorService service) {
        super(service);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProveedorSummaryDto>> getAll() {
        return ResponseEntity.ok(service.findAllDtos());
    }
}
