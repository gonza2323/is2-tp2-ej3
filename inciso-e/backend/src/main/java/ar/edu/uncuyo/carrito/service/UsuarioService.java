package ar.edu.uncuyo.carrito.service;

import ar.edu.uncuyo.carrito.dto.user.UsuarioCreateDto;
import ar.edu.uncuyo.carrito.dto.user.UsuarioDetailDto;
import ar.edu.uncuyo.carrito.dto.user.UsuarioSummaryDto;
import ar.edu.uncuyo.carrito.dto.user.UsuarioUpdateDto;
import ar.edu.uncuyo.carrito.entity.Usuario;
import ar.edu.uncuyo.carrito.error.BusinessException;
import ar.edu.uncuyo.carrito.mapper.UsuarioMapper;
import ar.edu.uncuyo.carrito.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<
        Usuario,
        Long,
        UsuarioRepository,
        UsuarioDetailDto,
        UsuarioSummaryDto,
        UsuarioCreateDto,
        UsuarioUpdateDto,
        UsuarioMapper> {

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        super("Usuario", repository, mapper);
    }

    @Override
    protected void validateCreate(UsuarioCreateDto dto) {
        validarNombreEsUnico(dto.getNombre(), null);

        if (!dto.getClave().equals(dto.getClaveConfirmacion()))
            throw new BusinessException("Las contraseñas no coinciden");
    }

    @Override
    protected void validateUpdate(UsuarioUpdateDto dto) {
        validarNombreEsUnico(dto.getNombre(), dto.getId());
    }

    private void validarNombreEsUnico(String nombre, Long excludeId) {
        boolean exists = (excludeId == null)
                ? repository.existsByNombreAndEliminadoFalse(nombre)
                : repository.existsByNombreAndIdNotAndEliminadoFalse(nombre, excludeId);

        if (exists)
            throw new BusinessException("El nombre de usuario ya está en uso");
    }
}
