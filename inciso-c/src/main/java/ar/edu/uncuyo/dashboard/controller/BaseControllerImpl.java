package ar.edu.uncuyo.dashboard.controller;


import ar.edu.uncuyo.dashboard.entity.Base;
import ar.edu.uncuyo.dashboard.service.BaseServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ar.edu.uncuyo.dashboard.repository.BaseRepository;

import java.util.List;

public abstract class BaseControllerImpl<
        E extends Base,
        S extends BaseServiceImpl<E, ? extends BaseRepository<E, Long>>
        >
        implements BaseController<E, Long> {

    protected final S service;

    protected BaseControllerImpl(S service) {
        this.service = service;
    }

    protected List<E> handleFindAll() throws Exception {
        return service.findAll();
    }

    protected E handleFindById(Long id) throws Exception {
        return service.findById(id);
    }

    protected E handleSave(E entity) throws Exception {
        return service.save(entity);
    }

    protected E handleUpdate(Long id, E entity) throws Exception {
        return service.update(id, entity);
    }

    protected boolean handleDelete(Long id) throws Exception {
        return service.delete(id);
    }

    // Métodos de la interfaz (no anotados con @GetMapping, etc.)
    @Override
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error, por favor intente más tarde.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        try {
            return ResponseEntity.ok(service.findAll(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error, por favor intente más tarde.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"No encontrado\"}");
        }
    }

    @Override
    public ResponseEntity<?> save(E entity) {
        try {
            return ResponseEntity.ok(service.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error al guardar\"}");
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, E entity) {
        try {
            return ResponseEntity.ok(service.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error al actualizar\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error al eliminar\"}");
        }
    }
}



