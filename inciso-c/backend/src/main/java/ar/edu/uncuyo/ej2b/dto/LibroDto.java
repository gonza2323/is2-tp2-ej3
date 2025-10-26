package ar.edu.uncuyo.ej2b.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LibroDto {
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String titulo;

    @NotNull(message = "El título no puede estar vacío")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private Integer fecha;

    @NotBlank(message = "El género no puede estar vacío")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String genero;

    @NotNull(message = "Debe indicar la cantidad de páginas")
    @Min(value = 1, message = "La cantidad de páginas debe ser positiva")
    private Integer paginas;

    @NotNull
    private List<Long> autoresIds = new ArrayList<>();

    private String pdfPath;
}
