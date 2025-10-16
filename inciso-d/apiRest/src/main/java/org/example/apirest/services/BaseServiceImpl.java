package org.example.apirest.services;

import jakarta.transaction.Transactional;
import org.example.apirest.entities.Base;
import org.example.apirest.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {

    protected final BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }


    //Hooks para checheos pre y post acciones

    // Guardado
    protected void beforeSave(E entity) throws Exception {}
    protected void afterSave(E saved) throws Exception {}

    // Actualización
    protected void beforeUpdate(ID id, E current, E incoming) throws Exception {}
    //Aplica en 'current' los cambios de 'incoming'. Por defecto no hace nada: cada subclase debe sobrescribir este método.
    protected void applyUpdates(E current, E incoming) throws Exception {}
    protected void afterUpdate(E updated) throws Exception {}

    // Borrado
    protected void beforeDelete(ID id) throws Exception {}
    protected void afterDelete(ID id) throws Exception {}

    // Y aca se aplican, casa clase concreta define la implementacion

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            return baseRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception {
        try {
            return baseRepository.findAll(pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isEmpty()) {
                throw new Exception("No existe la entidad con id: " + id);
            }
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            beforeSave(entity);
            E saved = baseRepository.save(entity);
            afterSave(saved);
            return saved;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isEmpty()) {
                throw new Exception("No existe la entidad con id: " + id);
            }

            E current = entityOptional.get();
            beforeUpdate(id, current, entity);
            // <- AQUÍ está el Template Method: delegamos el mapeo de campos a la subclase
            applyUpdates(current, entity);
            E updated = baseRepository.save(current);
            afterUpdate(updated);
            return updated;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if (!baseRepository.existsById(id)) {
                throw new Exception("No existe la entidad con id: " + id);
            }
            beforeDelete(id);
            baseRepository.deleteById(id);
            afterDelete(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
