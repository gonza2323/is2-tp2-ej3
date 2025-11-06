package ar.edu.uncuyo.carrito.repository;

import ar.edu.uncuyo.carrito.entity.Articulo;

public interface ArticuloRepository extends BaseRepository<Articulo, Long> {
    boolean existsByNombreAndEliminadoFalse(String name);
    boolean existsByNombreAndIdNotAndEliminadoFalse(String name, Long id);
}
