package ar.edu.uncuyo.ej2b.service;

import ar.edu.uncuyo.ej2b.entity.Base;
import ar.edu.uncuyo.ej2b.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;
    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try{
            List<E> personas = baseRepository.findAll();
            return personas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception{
        try{
            Page<E> personas = baseRepository.findAll(pageable);
            return personas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> persona = baseRepository.findById(id);
            if(persona.isPresent()){
                return persona.get();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try{
            entity = baseRepository.save(entity);
            return entity;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try{
            Optional<E> persona = baseRepository.findById(id);
            if(persona.isPresent()){
                E personaUpdate = persona.get();
//                personaUpdate.setNombre(entity.getNombre());
//                personaUpdate.setApellido(entity.getApellido());
//                personaUpdate.setDni(entity.getDni());
                personaUpdate = baseRepository.save(entity);
                return personaUpdate;
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try{
            if (baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception("No se encontro la persona con id: " + id);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
