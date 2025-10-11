package ar.edu.uncuyo.carrito.dto.user;

import ar.edu.uncuyo.carrito.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDto extends BaseDto<Long> {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String nombre;
}
