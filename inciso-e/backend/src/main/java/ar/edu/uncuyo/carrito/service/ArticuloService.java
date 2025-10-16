package ar.edu.uncuyo.carrito.service;

import ar.edu.uncuyo.carrito.dto.articulo.ArticuloCreateDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloDetailDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloSummaryDto;
import ar.edu.uncuyo.carrito.dto.articulo.ArticuloUpdateDto;
import ar.edu.uncuyo.carrito.entity.Articulo;
import ar.edu.uncuyo.carrito.entity.Imagen;
import ar.edu.uncuyo.carrito.entity.Proveedor;
import ar.edu.uncuyo.carrito.error.BusinessException;
import ar.edu.uncuyo.carrito.mapper.ArticuloMapper;
import ar.edu.uncuyo.carrito.repository.ArticuloRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticuloService extends BaseService<
        Articulo,
        Long,
        ArticuloRepository,
        ArticuloDetailDto,
        ArticuloSummaryDto,
        ArticuloCreateDto,
        ArticuloUpdateDto,
        ArticuloMapper> {

    private final ImagenService imagenService;
    private final ProveedorService proveedorService;

    public ArticuloService(ArticuloRepository repository,
                           ArticuloMapper mapper,
                           ImagenService imagenService,
                           ProveedorService proveedorService) {
        super("Articulo", repository, mapper);
        this.imagenService = imagenService;
        this.proveedorService = proveedorService;
    }

    @Override
    protected void preCreate(ArticuloCreateDto dto, Articulo entity) {
        Imagen imagen = imagenService.create(dto.getImagen());
        entity.setImagen(imagen);

        Proveedor proveedor = proveedorService.find(dto.getProveedorId());
        entity.setProveedor(proveedor);
    }

    @Override
    protected void preDelete(Articulo articulo) {
        imagenService.delete(articulo.getImagen().getId());
    }

    @Override
    protected void validateCreate(ArticuloCreateDto dto) { validarNombreEsUnico(dto.getNombre(), null); }

    @Override
    protected void validateUpdate(ArticuloUpdateDto dto) {
        validarNombreEsUnico(dto.getNombre(), dto.getId());
    }

    private void validarNombreEsUnico(String nombre, Long excludeId) {
        boolean exists = (excludeId == null)
                ? repository.existsByNombreAndEliminadoFalse(nombre)
                : repository.existsByNombreAndIdNotAndEliminadoFalse(nombre, excludeId);

        if (exists)
            throw new BusinessException("El email de articulo ya está en uso");
    }
}
