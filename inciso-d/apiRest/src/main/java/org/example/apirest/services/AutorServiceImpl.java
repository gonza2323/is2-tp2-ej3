package org.example.apirest.services;

import org.example.apirest.entities.Autor;
import org.example.apirest.entities.Persona;
import org.example.apirest.repositories.AutorRepository;
import org.example.apirest.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Long> implements AutorService {

    private final AutorRepository autorRepository;

    public AutorServiceImpl(BaseRepository<Autor, Long> baseRepository, AutorRepository autorRepository) {
        super(baseRepository);
        this.autorRepository = autorRepository;
    }

    @Override
    public List<Autor> search(String filtro) throws Exception {
        try {
            return autorRepository.searchNativo(filtro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //paginacion
    @Override
    public Page<Autor> search(String filtro, Pageable pageable) throws Exception {
        try {
            return autorRepository.searchNativo(filtro, pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    protected void applyUpdates(Autor current, Autor incoming) throws Exception {
        current.setNombre(incoming.getNombre());
        current.setApellido(incoming.getApellido());
        current.setBiografia(incoming.getBiografia());
    }


    @Override
    protected void afterDelete(Long id) throws Exception {
        System.out.println("✅ Autor con ID " + id + " eliminado correctamente.");
        // Aquí podrías mandar un mail, disparar un evento, etc.
    }

}
