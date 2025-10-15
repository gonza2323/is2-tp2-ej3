package ar.edu.uncuyo.carrito.controller;

import ar.edu.uncuyo.carrito.dto.usuario.UsuarioCreateDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioDetailDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioSummaryDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioUpdateDto;
import ar.edu.uncuyo.carrito.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController extends BaseController<Long,UsuarioDetailDto, UsuarioSummaryDto, UsuarioCreateDto, UsuarioUpdateDto, UsuarioService> {

    public UsuarioController(UsuarioService service) { super(service); }
}
