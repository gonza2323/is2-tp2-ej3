package ar.edu.uncuyo.carrito.mapper;

import ar.edu.uncuyo.carrito.dto.articulo.ArticuloCreateDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloDetailDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloSummaryDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloUpdateDto;
import ar.edu.uncuyo.carrito.entity.Articulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArticuloMapper extends BaseMapper<Articulo, ArticuloDetailDto, ArticuloSummaryDto, ArticuloCreateDto, ArticuloUpdateDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    @Mapping(target = "imagen", ignore = true)
    @Mapping(target = "proveedor", ignore = true)
    Articulo toEntity(ArticuloCreateDto Dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    @Mapping(target = "imagen", ignore = true)
    @Mapping(target = "proveedor", ignore = true)
    void updateEntity(ArticuloUpdateDto dto, @MappingTarget Articulo articulo);

    @Override
    @Mapping(target = "imagenId", source = "imagen.id")
    @Mapping(target = "proveedorId", source = "proveedor.id")
    @Mapping(target = "proveedorNombre", source = "proveedor.nombre")
    ArticuloDetailDto toDto(Articulo articulo);

    @Override
    @Mapping(target = "imagenId", source = "imagen.id")
    @Mapping(target = "proveedorNombre", source = "proveedor.nombre")
    ArticuloSummaryDto toSummaryDto(Articulo articulo);
}
