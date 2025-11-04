package ar.edu.uncuyo.dashboard.controller;


import ar.edu.uncuyo.dashboard.entity.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface BaseController<E extends Base, ID extends Serializable> {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getAll(Pageable pageable);

    ResponseEntity<?> getOne(ID id) throws Exception;

    ResponseEntity<?> save(E entity);

    ResponseEntity<?> update(ID id, E entity);

    ResponseEntity<?> delete(ID id);
}
