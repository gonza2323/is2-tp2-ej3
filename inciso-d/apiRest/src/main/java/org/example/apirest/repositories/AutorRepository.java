package org.example.apirest.repositories;

import org.example.apirest.entities.Autor;
import org.example.apirest.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends BaseRepository<Autor, Long>{

    List<Autor> findByNombreContainingOrApellidoContaining(String nombre, String apellido); //consulta con JQuery

    Page<Autor> findByNombreContainingOrApellidoContaining(String nombre, String apellido, Pageable pageable); //consulta con JQuery

    @Query(value = "SELECT a FROM Autor a WHERE a.nombre LIKE %:filtro% OR a.apellido LIKE %:filtro%")
    List<Autor> search(@Param("filtro") String filtro); // consulta con JPA

    @Query(value = "SELECT a FROM Autor a WHERE a.nombre LIKE %:filtro% OR a.apellido LIKE %:filtro%")
    Page<Autor> search(@Param("filtro") String filtro, Pageable pageable); // consulta con JPA

    @Query(
            value = "SELECT a FROM autor WHERE autor.nombre LIKE %:filtro% OR autor.apellido LIKE %:filtro%",
            nativeQuery = true
    ) // consulta con SQL
    List<Autor> searchNativo(@Param("filtro") String filtro);


    @Query(
            value = "SELECT a FROM autor WHERE autor.nombre LIKE %:filtro% OR autor.apellido LIKE %:filtro%",
            countQuery = "SELECT COUNT(*) FROM autor",
            nativeQuery = true
    ) // consulta con SQL
    Page<Autor> searchNativo(@Param("filtro") String filtro,Pageable pageable);

}
