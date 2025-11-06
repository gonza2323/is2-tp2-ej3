package ar.edu.uncuyo.carrito.service;

import ar.edu.uncuyo.carrito.dto.usuario.UsuarioCreateDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioDetailDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioSummaryDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioUpdateDto;
import ar.edu.uncuyo.carrito.entity.Usuario;
import ar.edu.uncuyo.carrito.error.BusinessException;
import ar.edu.uncuyo.carrito.mapper.UsuarioMapper;
import ar.edu.uncuyo.carrito.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    private final PasswordEncoder passwordEncoder;
    private final CarritoService carritoService;
    private final AuthService authService;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper, PasswordEncoder passwordEncoder, CarritoService carritoService, AuthService authService) {
        super("Usuario", repository, mapper);
        this.passwordEncoder = passwordEncoder;
        this.carritoService = carritoService;
        this.authService = authService;
    }

    @Transactional
    public Usuario buscarUsuarioActual() {
        Long userId = authService.buscarIdUsuarioActual();
        return find(userId);
    }

    @Transactional
    public UsuarioDetailDto buscarUsuarioActualDto() {
        return mapper.toDto(buscarUsuarioActual());
    }

    @Override
    protected void preCreate(UsuarioCreateDto dto, Usuario usuario) {
        String passwordHash = passwordEncoder.encode(dto.getClave());
        usuario.setClave(passwordHash);
    }

    @Override
    protected void postCreate(UsuarioCreateDto dto, Usuario usuario) {
        carritoService.create(usuario);
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
            throw new BusinessException("El email de usuario ya está en uso");
    }
}
