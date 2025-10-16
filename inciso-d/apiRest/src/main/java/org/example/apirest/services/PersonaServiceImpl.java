package org.example.apirest.services;

import org.example.apirest.entities.Persona;
import org.example.apirest.repositories.PersonaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService {

    private final PersonaRepository personaRepository; //Cambie el autowired por inyeccion de constructor

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        super(personaRepository);
        this.personaRepository = personaRepository;
    }

    @Override
    public List<Persona> search(String filtro) throws Exception {
        try {
            return personaRepository.searchNativo(filtro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //paginacion
    @Override
    public Page<Persona> search(String filtro, Pageable pageable) throws Exception {
        try {
            return personaRepository.searchNativo(filtro, pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    protected void applyUpdates(Persona current, Persona incoming) throws Exception {
        current.setNombre(incoming.getNombre());
        current.setApellido(incoming.getApellido());
        current.setDni(incoming.getDni());
    }

    //Agregar checkeo de campos antes del update

    @Override
    public void beforeSave(Persona entity) throws Exception{
        if (entity.getDni() <= 0) {
            throw new Exception("El DNI no puede estar vacío o ser menor a 0");
        }
        entity.setEliminado(false);
    }

    @Override
    protected void afterDelete(Long id) throws Exception {
        System.out.println("✅ Persona con ID " + id + " eliminada correctamente.");
        // Aquí podrías mandar un mail, disparar un evento, etc.
    }


}

