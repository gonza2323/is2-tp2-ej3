package ar.edu.uncuyo.carrito.dto.proveedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorCreateDto {
    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;
}
