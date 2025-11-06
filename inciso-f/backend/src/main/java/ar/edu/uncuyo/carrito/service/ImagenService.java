package ar.edu.uncuyo.carrito.service;

import ar.edu.uncuyo.carrito.dto.imagen.ImagenCreateDto;
import ar.edu.uncuyo.carrito.dto.imagen.ImagenDetailDto;
import ar.edu.uncuyo.carrito.dto.imagen.ImagenSummaryDto;
import ar.edu.uncuyo.carrito.entity.Imagen;
import ar.edu.uncuyo.carrito.mapper.ImagenMapper;
import ar.edu.uncuyo.carrito.repository.ImagenRepository;
import org.springframework.stereotype.Service;

@Service
public class ImagenService extends BaseService<
        Imagen,
        Long,
        ImagenRepository,
        ImagenDetailDto,
        ImagenSummaryDto,
        ImagenCreateDto,
        ImagenDetailDto,
        ImagenMapper> {

    public ImagenService(ImagenRepository repository, ImagenMapper mapper) {
        super("Imagen", repository, mapper);
    }

    @Override
    public Imagen update(ImagenDetailDto dto) { throw new UnsupportedOperationException(); }
}
