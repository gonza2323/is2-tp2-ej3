package ar.edu.uncuyo.carrito.dto.proveedor;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorCreateDto {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;

    @NotNull(message = "La latitud no puede ser nula")
    @DecimalMin(value = "-90.0", message = "La latitud mínima es -90.0")
    @DecimalMax(value = "90.0", message = "La latitud máxima es 90.0")
    private Double latitud;

    @NotNull(message = "La longitud no puede ser nula")
    @DecimalMin(value = "-180.0", message = "La longitud mínima es -180.0")
    @DecimalMax(value = "180.0", message = "La longitud máxima es 180.0")
    private Double longitud;
}
