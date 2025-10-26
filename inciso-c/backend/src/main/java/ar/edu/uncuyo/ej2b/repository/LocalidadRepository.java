package ar.edu.uncuyo.ej2b.repository;

import ar.edu.uncuyo.ej2b.entity.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
    boolean existsByDenominacionAndEliminadoFalse(String nombre);
    boolean existsByDenominacionAndIdNotAndEliminadoFalse(String nombre, Long id);

    List<Localidad> findAllByEliminadoFalseOrderByDenominacion();
    Optional<Localidad> findByIdAndEliminadoFalse(Long id);
}
