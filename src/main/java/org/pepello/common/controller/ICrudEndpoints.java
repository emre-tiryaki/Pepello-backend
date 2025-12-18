package org.pepello.common.controller;

import jakarta.validation.Valid;
import org.pepello.common.ICrud;
import org.pepello.common.request.BaseCreateRequest;
import org.pepello.common.request.BaseUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * EntityControllerları için basit crud endpointleri
 *
 * @param <D> geri dönecek dto tipi
 * @param <C> create request tipi (BaseCreateRequest implement etmeli)
 * @param <U> update request tipi (BaseUpdateRequest implement etmeli)
 */
public interface ICrudEndpoints<D, C extends BaseCreateRequest, U extends BaseUpdateRequest> extends ICrud<D, C, U> {
    @GetMapping("/list")
    @Override
    List<D> getAll();

    @GetMapping("/{id}")
    @Override
    D getById(@PathVariable UUID id);

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    D create(@Valid @RequestBody C createDto);

    @PatchMapping("/{id}")
    @Override
    D update(
            @PathVariable UUID id,
            @Valid @RequestBody U updateDto
    );

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    void delete(@PathVariable UUID id);

    @GetMapping("/exists/{id}")
    @Override
    boolean exists(@PathVariable UUID id);
}
