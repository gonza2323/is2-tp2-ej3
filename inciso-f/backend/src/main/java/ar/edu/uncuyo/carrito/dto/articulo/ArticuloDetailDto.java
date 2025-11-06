package ar.edu.uncuyo.carrito.dto.articulo;

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
public class ArticuloDetailDto extends IdentifiableDto<Long> {
    private String nombre;
    private Double precio;
    private Long proveedorId;
    private String proveedorNombre;
    private Long imagenId;
}
