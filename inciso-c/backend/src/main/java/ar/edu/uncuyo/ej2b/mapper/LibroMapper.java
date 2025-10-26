package ar.edu.uncuyo.ej2b.mapper;

import ar.edu.uncuyo.ej2b.dto.AutorDto;
import ar.edu.uncuyo.ej2b.dto.LibroDto;
import ar.edu.uncuyo.ej2b.dto.PersonaDto;
import ar.edu.uncuyo.ej2b.entity.Autor;
import ar.edu.uncuyo.ej2b.entity.Libro;
import ar.edu.uncuyo.ej2b.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibroMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    @Mapping(target = "autores", ignore = true)
    Libro toEntity(LibroDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    @Mapping(target = "autores", ignore = true)
    void updateEntityFromDto(LibroDto dto, @MappingTarget Libro libro);

    @Mapping(target = "autoresIds", ignore = true)
    LibroDto toDto(Libro libro);


    List<LibroDto> toDtos(List<Libro> libros);
}
