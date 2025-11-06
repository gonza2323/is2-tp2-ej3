package ar.edu.uncuyo.carrito.mapper;

import ar.edu.uncuyo.carrito.dto.carrito.CarritoDetalleDto;
import ar.edu.uncuyo.carrito.entity.Carrito;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DetalleMapper.class})
public interface CarritoMapper {
    CarritoDetalleDto toDto(Carrito carrito);
}
