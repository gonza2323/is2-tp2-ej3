package ar.edu.uncuyo.carrito.controller;

import ar.edu.uncuyo.carrito.dto.usuario.UsuarioDetailDto;
import ar.edu.uncuyo.carrito.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class MeController {

    private final UsuarioService usuarioService;

    @GetMapping("/account")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioDetailDto> getCurrentUser() {
        UsuarioDetailDto usuario = usuarioService.buscarUsuarioActualDto();
        return ResponseEntity.ok(usuario);
    }
}
