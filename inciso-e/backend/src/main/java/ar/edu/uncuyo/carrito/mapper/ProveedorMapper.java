package ar.edu.uncuyo.carrito.mapper;

import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorCreateDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorDetailDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorSummaryDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorUpdateDto;
import ar.edu.uncuyo.carrito.entity.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProveedorMapper extends BaseMapper<Proveedor, ProveedorDetailDto, ProveedorSummaryDto, ProveedorCreateDto, ProveedorUpdateDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    Proveedor toEntity(ProveedorCreateDto Dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    void updateEntity(ProveedorUpdateDto dto, @MappingTarget Proveedor proveedor);

    @Override
    ProveedorDetailDto toDto(Proveedor proveedor);

    @Override
    ProveedorSummaryDto toSummaryDto(Proveedor proveedor);
}
