package ar.edu.uncuyo.carrito.mapper;

import ar.edu.uncuyo.carrito.dto.imagen.ImagenCreateDto;
import ar.edu.uncuyo.carrito.dto.imagen.ImagenDetailDto;
import ar.edu.uncuyo.carrito.dto.imagen.ImagenSummaryDto;
import ar.edu.uncuyo.carrito.entity.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ImagenMapper extends BaseMapper<Imagen, ImagenDetailDto, ImagenSummaryDto, ImagenCreateDto, ImagenDetailDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    Imagen toEntity(ImagenCreateDto Dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    void updateEntity(ImagenDetailDto dto, @MappingTarget Imagen usuario);

    @Override
    ImagenDetailDto toDto(Imagen usuario);

    @Override
    ImagenSummaryDto toSummaryDto(Imagen usuario);
}
