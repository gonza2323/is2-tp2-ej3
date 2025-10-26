package ar.edu.uncuyo.ej2b.controller;

import ar.edu.uncuyo.ej2b.dto.LibroDto;
import ar.edu.uncuyo.ej2b.entity.Libro;
import ar.edu.uncuyo.ej2b.mapper.LibroMapper;
import ar.edu.uncuyo.ej2b.service.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;
    private final LibroMapper libroMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarLibro(@PathVariable Long id) {
        LibroDto libro = libroService.buscarLibroDto(id);
        return ResponseEntity.ok(libro);
    }

    @GetMapping
    public ResponseEntity<?> listarLibroes() {
        List<LibroDto> libros = libroService.listarLibrosDtos();
        return ResponseEntity.ok(libros);
    }

    @PostMapping
    public ResponseEntity<?> crearLibro(@Valid @RequestBody LibroDto libroDto) {
        Libro libro = libroService.crearLibro(libroDto);
        LibroDto dto = libroMapper.toDto(libro);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarLibro(@PathVariable Long id, @Valid @RequestBody LibroDto libroDto) {
        libroDto.setId(id);
        Libro libro = libroService.modificarLibro(libroDto);
        LibroDto dto = libroMapper.toDto(libro);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/crear-con-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LibroDto> crearLibroConPdf(
            @RequestPart("libro") String libroJson,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        // ✅ Convertimos el JSON manualmente a LibroDto
        ObjectMapper objectMapper = new ObjectMapper();
        LibroDto libroDto = objectMapper.readValue(libroJson, LibroDto.class);

        Libro libroCreado = libroService.crearLibroConPdf(libroDto, file);
        LibroDto libroCreadoDto = libroMapper.toDto(libroCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(libroCreadoDto);
    }

}
