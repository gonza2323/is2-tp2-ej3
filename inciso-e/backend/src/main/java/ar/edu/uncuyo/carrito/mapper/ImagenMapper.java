package ar.edu.uncuyo.carrito.mapper;

import ar.edu.uncuyo.carrito.dto.imagen.ImagenCreateDto;
import ar.edu.uncuyo.carrito.dto.imagen.ImagenDetailDto;
import ar.edu.uncuyo.carrito.dto.imagen.ImagenSummaryDto;
import ar.edu.uncuyo.carrito.entity.Imagen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface ImagenMapper extends BaseMapper<Imagen, ImagenDetailDto, ImagenSummaryDto, ImagenCreateDto, ImagenDetailDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    Imagen toEntity(ImagenCreateDto Dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eliminado", ignore = true)
    void updateEntity(ImagenDetailDto dto, @MappingTarget Imagen imagen);

    @Override
    ImagenDetailDto toDto(Imagen imagen);

    @Override
    ImagenSummaryDto toSummaryDto(Imagen imagen);

    @Mapping(target = "nombre", source = "imagen", qualifiedByName = "getFileName")
    @Mapping(target = "mime", source = "imagen", qualifiedByName = "getContentType")
    @Mapping(target = "contenido", source = "imagen", qualifiedByName = "getFileContents")
    ImagenCreateDto toDto(MultipartFile imagen);

    @Named("getFileName")
    default String getFileName(MultipartFile file) {
        return file != null ? file.getOriginalFilename() : null;
    }

    @Named("getContentType")
    default String getContentType(MultipartFile file) {
        return file != null ? file.getContentType() : null;
    }

    @Named("getFileContents")
    default byte[] getFileContents(MultipartFile file) throws Exception {
        return file != null ? file.getBytes() : null;
    }
}
