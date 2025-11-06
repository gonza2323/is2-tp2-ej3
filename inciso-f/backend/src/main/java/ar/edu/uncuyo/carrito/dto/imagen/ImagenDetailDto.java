package ar.edu.uncuyo.carrito.dto.imagen;

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
public class ImagenDetailDto extends IdentifiableDto<Long> {
    private String nombre;
    private String mime;
    private byte[] contenido;
}
