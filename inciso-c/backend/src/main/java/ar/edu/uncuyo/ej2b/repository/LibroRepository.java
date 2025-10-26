package ar.edu.uncuyo.ej2b.repository;

import ar.edu.uncuyo.ej2b.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByIdAndEliminadoFalse(Long id);

    List<Libro> findAllByEliminadoFalseOrderByTitulo();
}
