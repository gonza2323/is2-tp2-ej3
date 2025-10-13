package org.example.apirest.services;

import org.example.apirest.entities.Libro;
import org.example.apirest.repositories.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl extends BaseServiceImpl<Libro, Long> implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        super(libroRepository);
        this.libroRepository = libroRepository;
    }

    @Override
    protected void applyUpdates(Libro current, Libro incoming) throws Exception {
        current.setTitulo(incoming.getTitulo());
        current.setFecha(incoming.getFecha());
        current.setGenero(incoming.getGenero());
        current.setPaginas(incoming.getPaginas());
        current.setAutores(incoming.getAutores());
    }
}

