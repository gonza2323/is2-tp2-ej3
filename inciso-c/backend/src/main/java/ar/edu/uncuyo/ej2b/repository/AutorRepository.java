package ar.edu.uncuyo.ej2b.repository;

import ar.edu.uncuyo.ej2b.entity.Autor;
import ar.edu.uncuyo.ej2b.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends BaseRepository<Autor, Long> {
    Optional<Autor> findByIdAndEliminadoFalse(Long id);

    List<Autor> findAllByEliminadoFalse();
}
