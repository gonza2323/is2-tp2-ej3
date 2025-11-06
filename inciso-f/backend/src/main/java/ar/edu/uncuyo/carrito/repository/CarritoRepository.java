package ar.edu.uncuyo.carrito.repository;

import ar.edu.uncuyo.carrito.entity.Carrito;

import java.util.Optional;

public interface CarritoRepository extends BaseRepository<Carrito, Long> {
    Optional<Carrito> findByEliminadoFalseAndUsuarioId(Long userId);
}
