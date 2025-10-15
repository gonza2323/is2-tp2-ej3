package ar.edu.uncuyo.carrito.service;

import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorCreateDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorDetailDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorSummaryDto;
import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorUpdateDto;
import ar.edu.uncuyo.carrito.entity.Proveedor;
import ar.edu.uncuyo.carrito.error.BusinessException;
import ar.edu.uncuyo.carrito.mapper.ProveedorMapper;
import ar.edu.uncuyo.carrito.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService extends BaseService<
        Proveedor,
        Long,
        ProveedorRepository,
        ProveedorDetailDto,
        ProveedorSummaryDto,
        ProveedorCreateDto,
        ProveedorUpdateDto,
        ProveedorMapper> {

    public ProveedorService(ProveedorRepository repository, ProveedorMapper mapper) {
        super("Proveedor", repository, mapper);
    }

    @Override
    protected void validateCreate(ProveedorCreateDto dto) {
        validarNombreEsUnico(dto.getNombre(), null);
    }

    @Override
    protected void validateUpdate(ProveedorUpdateDto dto) {
        validarNombreEsUnico(dto.getNombre(), dto.getId());
    }

    private void validarNombreEsUnico(String nombre, Long excludeId) {
        boolean exists = (excludeId == null)
                ? repository.existsByNombreAndEliminadoFalse(nombre)
                : repository.existsByNombreAndIdNotAndEliminadoFalse(nombre, excludeId);

        if (exists)
            throw new BusinessException("Ya existe un proveedor con ese email");
    }
}
