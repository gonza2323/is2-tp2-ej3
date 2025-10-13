package org.example.apirest.repositories;

import org.example.apirest.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonaRepository extends BaseRepository<Persona, Long> {

    List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido); //consulta con JQuery

    Page<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido, Pageable pageable); //consulta con JQuery

    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    List<Persona> search(@Param("filtro") String filtro); // consulta con JPA

    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    Page<Persona> search(@Param("filtro") String filtro, Pageable pageable); // consulta con JPA

    @Query(
            value = "SELECT p FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%",
            nativeQuery = true
    ) // consulta con SQL
    List<Persona> searchNativo(@Param("filtro") String filtro);


    @Query(
            value = "SELECT p FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%",
            countQuery = "SELECT COUNT(*) FROM persona",
            nativeQuery = true
    ) // consulta con SQL
    Page<Persona> searchNativo(@Param("filtro") String filtro,Pageable pageable);
}
