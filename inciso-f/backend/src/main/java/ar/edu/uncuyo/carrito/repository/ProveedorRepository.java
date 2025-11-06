package ar.edu.uncuyo.carrito.repository;

import ar.edu.uncuyo.carrito.entity.Proveedor;

import java.util.List;

public interface ProveedorRepository extends BaseRepository<Proveedor, Long> {
    boolean existsByNombreAndEliminadoFalse(String name);
    boolean existsByNombreAndIdNotAndEliminadoFalse(String name, Long id);

    List<Proveedor> findAllByEliminadoFalseOrderByNombre();
}
