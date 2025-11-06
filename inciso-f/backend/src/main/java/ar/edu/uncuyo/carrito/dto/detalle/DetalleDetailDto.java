package ar.edu.uncuyo.carrito.dto.detalle;

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
public class DetalleDetailDto {
    private Long id;
    private Long articuloId;
    private String nombre;
    private Double precio;
    private Long imagenId;
}
