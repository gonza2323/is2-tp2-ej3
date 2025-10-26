package ar.edu.uncuyo.ej2b.service;

import ar.edu.uncuyo.ej2b.dto.DomicilioDto;
import ar.edu.uncuyo.ej2b.dto.LibroDto;
import ar.edu.uncuyo.ej2b.dto.LocalidadDto;
import ar.edu.uncuyo.ej2b.dto.PersonaDto;
import ar.edu.uncuyo.ej2b.entity.Domicilio;
import ar.edu.uncuyo.ej2b.entity.Libro;
import ar.edu.uncuyo.ej2b.entity.Localidad;
import ar.edu.uncuyo.ej2b.entity.Persona;
import ar.edu.uncuyo.ej2b.error.BusinessException;
import ar.edu.uncuyo.ej2b.mapper.PersonaMapper;
import ar.edu.uncuyo.ej2b.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaService {
    private final PersonaMapper personaMapper;
    private final PersonaRepository personaRepository;
    private final LibroService libroService;
    private final DomicilioService domicilioService;
    private final LocalidadService localidadService;

    @Transactional(readOnly = true)
    public Persona buscarPersona(Long id) {
        return personaRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new BusinessException("La persona no existe"));
    }

    @Transactional(readOnly = true)
    public PersonaDto buscarPersonaDto(Long id) {
        Persona persona = buscarPersona(id);
        return personaMapper.toDto(persona);
    }

    @Transactional(readOnly = true)
    public List<PersonaDto> listarPersonasDtos() {
        List<Persona> personas = personaRepository.findAllByEliminadoFalse();
        return personaMapper.toDtos(personas);
    }

    @Transactional
    public Persona crearPersona(PersonaDto personaDto) {
        Persona persona = personaMapper.toEntity(personaDto);
        persona.setId(null);

        Domicilio domicilio = domicilioService.crearDomicilio(personaDto.getDomicilio());
        if (personaDto.getDomicilio() != null && personaDto.getDomicilio().getLocalidadId() != null) {
            Long localidadId = personaDto.getDomicilio().getLocalidadId();
            Localidad localidad = localidadService.buscarLocalidad(localidadId);
            domicilio.setLocalidad(localidad);
        }
        persona.setDomicilio(domicilio);

        return personaRepository.save(persona);
    }

    @Transactional
    public Persona modificarPersona(PersonaDto personaDto) {
        Persona persona = buscarPersona(personaDto.getId());
        personaMapper.updateEntityFromDto(personaDto, persona);

        DomicilioDto domicilioDto = personaDto.getDomicilio();
        domicilioDto.setId(persona.getDomicilio().getId());
        domicilioService.modificarDomicilio(domicilioDto);

        return personaRepository.save(persona);
    }

    @Transactional
    public void eliminarPersona(Long id) {
        Persona persona = buscarPersona(id);
        persona.setEliminado(true);

        for (Libro libro : persona.getLibros())
            libroService.eliminarLibro(libro.getId());

        personaRepository.save(persona);
    }
}
