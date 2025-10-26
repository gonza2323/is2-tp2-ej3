package ar.edu.uncuyo.ej2b.controller;

import ar.edu.uncuyo.ej2b.dto.AutorDto;
import ar.edu.uncuyo.ej2b.entity.Autor;
import ar.edu.uncuyo.ej2b.mapper.AutorMapper;
import ar.edu.uncuyo.ej2b.service.AutorService;
import ar.edu.uncuyo.ej2b.service.AutorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
@RequiredArgsConstructor
public class AutorController extends BaseControllerImpl<Autor, AutorServiceImpl> {

    private final AutorService autorService;
    private final AutorMapper autorMapper;

    // 🔹 Endpoint personalizado: obtener un autor como DTO
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAutor(@PathVariable Long id) throws Exception {
        AutorDto autor = autorService.buscarAutorDto(id);
        return ResponseEntity.ok(autor);
    }

    // 🔹 Endpoint personalizado: listar todos los autores como DTOs
    @GetMapping
    public ResponseEntity<?> listarAutores() throws Exception {
        List<AutorDto> autores = autorService.listarAutoresDtos();
        return ResponseEntity.ok(autores);
    }

    // 🔹 Endpoint personalizado: crear un autor usando DTO
    @PostMapping
    public ResponseEntity<?> crearAutor(@Valid @RequestBody AutorDto autorDto) throws Exception {
        Autor autor = autorService.crearAutor(autorDto);
        AutorDto dto = autorMapper.toDto(autor);
        return ResponseEntity.ok(dto);
    }

    // 🔹 Endpoint personalizado: actualizar un autor usando DTO
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarAutor(@PathVariable Long id, @Valid @RequestBody AutorDto autorDto) throws Exception {
        autorDto.setId(id);
        Autor autor = autorService.modificarAutor(autorDto);
        AutorDto dto = autorMapper.toDto(autor);
        return ResponseEntity.ok(dto);
    }
}

