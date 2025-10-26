package ar.edu.uncuyo.ej2b.service;


import ar.edu.uncuyo.ej2b.dto.AutorDto;
import ar.edu.uncuyo.ej2b.entity.Autor;
import ar.edu.uncuyo.ej2b.mapper.AutorMapper;
import ar.edu.uncuyo.ej2b.repository.AutorRepository;
import ar.edu.uncuyo.ej2b.repository.BaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Long> implements AutorService {

    private AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    public AutorServiceImpl(BaseRepository<Autor, Long> baseRepository,
                            AutorRepository autorRepository,
                            AutorMapper autorMapper) {
        super(baseRepository);
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public AutorDto buscarAutorDto(Long id) throws Exception {
        try {
            Optional<Autor> autorOpt = autorRepository.findById(id);
            if (autorOpt.isEmpty()) {
                throw new Exception("Autor no encontrado con id: " + id);
            }
            return autorMapper.toDto(autorOpt.get());
        } catch (Exception e) {
            throw new Exception("Error al buscar autor: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutorDto> listarAutoresDtos() throws Exception {
        try {
            List<Autor> autores = autorRepository.findAll();
            return autores.stream()
                    .map(autorMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error al listar autores: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Autor crearAutor(AutorDto autorDto) throws Exception {
        try {
            Autor autor = autorMapper.toEntity(autorDto);
            return autorRepository.save(autor);
        } catch (Exception e) {
            throw new Exception("Error al crear autor: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Autor modificarAutor(AutorDto autorDto) throws Exception {
        try {
            Long id = autorDto.getId();
            if (id == null) throw new Exception("El id del autor es requerido para modificar");

            Optional<Autor> existente = autorRepository.findById(id);
            if (existente.isEmpty()) throw new Exception("Autor no encontrado con id: " + id);

            Autor autorActualizado = autorMapper.toEntity(autorDto);
            autorActualizado.setId(id);
            return autorRepository.save(autorActualizado);
        } catch (Exception e) {
            throw new Exception("Error al modificar autor: " + e.getMessage());
        }
    }
}
