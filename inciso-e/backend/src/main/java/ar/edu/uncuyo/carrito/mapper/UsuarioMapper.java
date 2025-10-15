package ar.edu.uncuyo.carrito.mapper;

import ar.edu.uncuyo.carrito.dto.usuario.UsuarioCreateDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioDetailDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioSummaryDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioUpdateDto;
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

    @Override
    UsuarioDetailDto toDto(Usuario usuario);

    @Override
    UsuarioSummaryDto toSummaryDto(Usuario usuario);
}
