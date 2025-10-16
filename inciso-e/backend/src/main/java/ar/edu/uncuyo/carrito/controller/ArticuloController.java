package ar.edu.uncuyo.carrito.controller;

import ar.edu.uncuyo.carrito.dto.articulo.ArticuloCreateDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloDetailDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloSummaryDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloUpdateDto;
import ar.edu.uncuyo.carrito.dto.imagen.ImagenCreateDto;
import ar.edu.uncuyo.carrito.mapper.ImagenMapper;
import ar.edu.uncuyo.carrito.service.ArticuloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/articulos")
@RequiredArgsConstructor
public class ArticuloController{

    private final ArticuloService articuloService;
    private final ImagenMapper imagenMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDetailDto> find(@PathVariable Long id) {
        ArticuloDetailDto dto = articuloService.findDto(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ArticuloSummaryDto>> find(Pageable pageable) {
        Page<ArticuloSummaryDto> dtos = articuloService.findDtos(pageable);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArticuloDetailDto> create(
            @Valid @RequestPart("articulo") ArticuloCreateDto createDto,
            @RequestPart(value = "imagen", required = false) MultipartFile imagenArchivo) {

        ImagenCreateDto imagenDto = imagenMapper.toDto(imagenArchivo);
        createDto.setImagen(imagenDto);

        ArticuloDetailDto dto = articuloService.createAndReturnDto(createDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloDetailDto> update(@PathVariable Long id, @Valid @RequestBody ArticuloUpdateDto updateDto) {
        updateDto.setId(id);
        ArticuloDetailDto dto = articuloService.updateAndReturnDto(updateDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articuloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
