package ar.edu.uncuyo.carrito.dto.articulo;

import ar.edu.uncuyo.carrito.dto.imagen.ImagenCreateDto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloCreateDto {
    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;

    @NotNull(message = "El precio no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener un formato válido")
    private Double precio;

    private Long proveedorId;

    private ImagenCreateDto imagen;
}
