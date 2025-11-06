package ar.edu.uncuyo.carrito.mapper;

import ar.edu.uncuyo.carrito.dto.detalle.DetalleDetailDto;
import ar.edu.uncuyo.carrito.entity.Detalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleMapper {
    @Mapping(target = "articuloId", source = "articulo.id")
    @Mapping(target = "nombre", source = "articulo.nombre")
    @Mapping(target = "precio", source = "articulo.precio")
    @Mapping(target = "imagenId", source = "articulo.imagen.id")
    DetalleDetailDto toDto(Detalle detalle);
}
