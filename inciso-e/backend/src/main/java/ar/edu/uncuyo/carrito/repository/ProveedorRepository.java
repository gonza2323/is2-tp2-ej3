package ar.edu.uncuyo.carrito.repository;

import ar.edu.uncuyo.carrito.entity.Proveedor;

public interface ProveedorRepository extends BaseRepository<Proveedor, Long> {
    boolean existsByNombreAndEliminadoFalse(String name);
    boolean existsByNombreAndIdNotAndEliminadoFalse(String name, Long id);
}
