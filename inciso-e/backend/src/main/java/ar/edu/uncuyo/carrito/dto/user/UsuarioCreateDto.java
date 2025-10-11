package ar.edu.uncuyo.carrito.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDto {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;

    @NotBlank(message = "Debe indicar una contraseña")
    @Size(min = 8, max = 127, message = "Entre 8 y 127 caracteres")
    private String clave;

    @NotBlank(message = "Debe confirmar la contraseña")
    @Size(min = 8, max = 127, message = "Entre 8 y 127 caracteres")
    private String claveConfirmacion;
}
