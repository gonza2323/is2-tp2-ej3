package ar.edu.uncuyo.carrito.controller;

import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorCreateDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorDetailDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorSummaryDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorUpdateDto;
import ar.edu.uncuyo.carrito.service.ProveedorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController extends BaseController<Long, ProveedorDetailDto, ProveedorSummaryDto, ProveedorCreateDto, ProveedorUpdateDto, ProveedorService> {

    public ProveedorController(ProveedorService service) {
        super(service);
    }
}
