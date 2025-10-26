package ar.edu.uncuyo.ej2b.service;


import ar.edu.uncuyo.ej2b.dto.AutorDto;
import ar.edu.uncuyo.ej2b.entity.Autor;

import java.util.List;

public interface AutorService extends BaseService<Autor, Long>{
    AutorDto buscarAutorDto(Long id) throws Exception;

    List<AutorDto> listarAutoresDtos() throws Exception;

    Autor crearAutor(AutorDto autorDto) throws Exception;

    Autor modificarAutor(AutorDto autorDto) throws Exception;

}
