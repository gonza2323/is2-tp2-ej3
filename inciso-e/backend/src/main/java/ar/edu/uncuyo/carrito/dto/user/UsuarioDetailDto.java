package ar.edu.uncuyo.carrito.dto.user;

import ar.edu.uncuyo.carrito.dto.BaseDto;
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
public class UsuarioDetailDto extends BaseDto<Long> {
    private String nombre;
}
