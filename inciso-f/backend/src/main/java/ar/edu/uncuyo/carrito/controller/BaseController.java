package ar.edu.uncuyo.carrito.controller;

import ar.edu.uncuyo.carrito.dto.IdentifiableDto;
import ar.edu.uncuyo.carrito.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;

public abstract class BaseController<
        ID extends Serializable,
        DetailDto extends IdentifiableDto<ID>,
        SummaryDto,
        CreateDto,
        UpdateDto extends IdentifiableDto<ID>,
        S extends BaseService<?, ID, ?, DetailDto, SummaryDto, CreateDto, UpdateDto, ?>> {

    protected final S service;

    protected BaseController(S service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDto> find(@PathVariable ID id) {
        DetailDto dto = service.findDto(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<SummaryDto>> find(Pageable pageable) {
        Page<SummaryDto> dtos = service.findDtos(pageable);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<DetailDto> create(@Valid @RequestBody CreateDto createDto) {
        preCreate(createDto);
        DetailDto dto = service.createAndReturnDto(createDto);
        postCreate(createDto, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailDto> update(@PathVariable ID id, @Valid @RequestBody UpdateDto updateDto) {
        preUpdate(updateDto);
        updateDto.setId(id);
        DetailDto dto = service.updateAndReturnDto(updateDto);
        postUpdate(updateDto, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        preDelete(id);
        service.delete(id);
        postDelete(id);
        return ResponseEntity.noContent().build();
    }


    // sobreescribir para agregar funcionalidad

    protected void preCreate(CreateDto createDto) {}
    protected void postCreate(CreateDto createDto, DetailDto created) {}

    protected void preUpdate(UpdateDto updateDto) {}
    protected void postUpdate(UpdateDto updateDto, DetailDto updated) {}

    protected void preDelete(ID id) {}
    protected void postDelete(ID id) {}
}
