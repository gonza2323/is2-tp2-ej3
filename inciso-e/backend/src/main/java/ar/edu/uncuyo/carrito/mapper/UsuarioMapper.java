package ar.edu.uncuyo.carrito.mapper;

import ar.edu.uncuyo.carrito.dto.user.UsuarioCreateDto;
import ar.edu.uncuyo.carrito.dto.user.UsuarioDetailDto;
import ar.edu.uncuyo.carrito.dto.user.UsuarioSummaryDto;
import ar.edu.uncuyo.carrito.dto.user.UsuarioUpdateDto;
import ar.edu.uncuyo.carrito.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDetailDto, UsuarioSummaryDto, UsuarioCreateDto, UsuarioUpdateDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    Usuario toEntity(UsuarioCreateDto Dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    void updateEntity(UsuarioUpdateDto dto, @MappingTarget Usuario usuario);

    UsuarioDetailDto toDto(Usuario usuario);

    UsuarioSummaryDto toSummaryDto(Usuario usuario);
}
