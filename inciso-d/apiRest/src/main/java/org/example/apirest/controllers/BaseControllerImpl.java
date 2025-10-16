package org.example.apirest.controllers;

import org.example.apirest.entities.Base;
import org.example.apirest.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>>
        implements BaseController<E, Long> {

    @Autowired
    protected S servicio;

    //Hooks para chekeos extra en caso de ser necesarios
    protected void beforeSave(E entity) throws Exception {}
    protected void afterSave(E entity) throws Exception {}

    protected void beforeUpdate(Long id, E entity) throws Exception {}
    protected void afterUpdate(E entity) throws Exception {}

    protected void beforeDelete(Long id) throws Exception {}
    protected void afterDelete(Long id) throws Exception {}

    //Metodos CRUD

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(servicio.findAll());
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable) {
        try {
            return ResponseEntity.ok(servicio.findAll(pageable));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(servicio.findById(id));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody E entity) {
        try {
            beforeSave(entity);
            E saved = servicio.save(entity);
            afterSave(saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity) {
        try {
            beforeUpdate(id, entity);
            E updated = servicio.update(id, entity);
            afterUpdate(updated);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            beforeDelete(id);
            servicio.delete(id);
            afterDelete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return handleException(e);
        }
    }

    //manejo de errores
    protected ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{\"error\":\"" + e.getMessage() + "\"}");
    }
}
