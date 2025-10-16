package ar.edu.uncuyo.carrito.dto.carrito;

import ar.edu.uncuyo.carrito.dto.detalle.DetalleDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDetalleDto {
    private Double total;
    private List<DetalleDetailDto> detalles;
}
