package ar.edu.uncuyo.carrito.service;

import ar.edu.uncuyo.carrito.dto.carrito.CarritoDetalleDto;
import ar.edu.uncuyo.carrito.entity.Articulo;
import ar.edu.uncuyo.carrito.entity.Carrito;
import ar.edu.uncuyo.carrito.entity.Detalle;
import ar.edu.uncuyo.carrito.entity.Usuario;
import ar.edu.uncuyo.carrito.error.BusinessException;
import ar.edu.uncuyo.carrito.mapper.CarritoMapper;
import ar.edu.uncuyo.carrito.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoService {
    private final CarritoRepository repository;
    private final ArticuloService articuloService;
    private final CarritoMapper carritoMapper;
    private final AuthService authService;

    @Transactional
    public Carrito create(Usuario usuario) {
        Carrito carrito = new Carrito();
        carrito.setTotal(0);
        carrito.setUsuario(usuario);
        return carrito;
    }

    @Transactional
    public void addProductToCart(Long productId) {
        Articulo articulo = articuloService.find(productId);
        Carrito carrito = buscarCarritoUsuarioActual();

        double newTotal = carrito.getTotal() + articulo.getPrecio();

        Detalle detalle = new Detalle();
        detalle.setArticulo(articulo);
        carrito.getDetalles().add(detalle);
        carrito.setTotal(newTotal);
        repository.save(carrito);
    }

    @Transactional
    public void removeItemFromCart(Long detalleId) {
        Carrito carrito = buscarCarritoUsuarioActual();

        List<Detalle> detalles = carrito.getDetalles();

        Detalle detalleToRemove = detalles.stream()
                .filter(detalle -> detalle.getId().equals(detalleId))
                .findFirst()
                .orElseThrow(() -> new BusinessException("No se encontró el detalle"));

        detalles.remove(detalleToRemove);

        double newTotal = carrito.getTotal() + detalleToRemove.getArticulo().getPrecio();
        newTotal = Math.max(newTotal, 0);

        carrito.setTotal(newTotal);

        repository.save(carrito);
    }

    @Transactional(readOnly = true)
    public Carrito buscarCarritoUsuarioActual() {
        Long userId = authService.buscarIdUsuarioActual();
        return repository.findByEliminadoFalseAndUsuarioId(userId)
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public CarritoDetalleDto buscarCarritoDtoUsuarioActual() {
        Carrito carrito = buscarCarritoUsuarioActual();
        return carritoMapper.toDto(carrito);
    }

}
