package ar.edu.uncuyo.carrito.repository;

import ar.edu.uncuyo.carrito.entity.Usuario;

public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
    boolean existsByNombreAndEliminadoFalse(String name);
    boolean existsByNombreAndIdNotAndEliminadoFalse(String name, Long id);
}
