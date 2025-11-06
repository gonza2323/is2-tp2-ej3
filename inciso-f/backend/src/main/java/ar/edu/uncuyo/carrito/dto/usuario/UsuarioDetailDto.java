package ar.edu.uncuyo.carrito.dto.usuario;

import ar.edu.uncuyo.carrito.dto.IdentifiableDto;
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
public class UsuarioDetailDto extends IdentifiableDto<Long> {
    private String nombre;
}
