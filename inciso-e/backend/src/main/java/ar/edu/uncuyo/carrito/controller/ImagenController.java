package ar.edu.uncuyo.carrito.controller;

import ar.edu.uncuyo.carrito.dto.imagen.ImagenDetailDto;
import ar.edu.uncuyo.carrito.service.ImagenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/imagenes")
@RequiredArgsConstructor
public class ImagenController{

    private final ImagenService imagenService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        ImagenDetailDto imagen = imagenService.findDto(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(imagen.getMime()))
                .body(imagen.getContenido());
    }

}
