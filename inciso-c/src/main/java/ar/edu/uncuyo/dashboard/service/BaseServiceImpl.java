package ar.edu.uncuyo.dashboard.service;


import ar.edu.uncuyo.dashboard.entity.Base;
import ar.edu.uncuyo.dashboard.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public abstract class BaseServiceImpl<E extends Base, R extends BaseRepository<E, Long>>
        implements BaseService<E, Long> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<E> findAll() throws Exception {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public E findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("No encontrado"));
    }

    @Transactional
    public E save(E entity) throws Exception {
        return repository.save(entity);
    }

    @Transactional
    public E update(Long id, E entity) throws Exception {
        if (!repository.existsById(id))
            throw new Exception("No existe el id " + id);
        return repository.save(entity);
    }

    @Transactional
    public boolean delete(Long id) throws Exception {
        if (!repository.existsById(id))
            throw new Exception("No existe el id " + id);
        repository.deleteById(id);
        return true;
    }
}
