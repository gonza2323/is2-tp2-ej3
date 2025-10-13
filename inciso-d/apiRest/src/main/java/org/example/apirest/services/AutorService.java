package org.example.apirest.services;

import org.example.apirest.entities.Autor;
import org.example.apirest.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AutorService extends BaseService<Autor, Long> {
    List<Autor> search(String filtro) throws Exception;
    Page<Autor> search(String filtro, Pageable pageable) throws Exception;
}
