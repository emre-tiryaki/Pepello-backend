package org.pepello.common.controller;

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
 * @param <T> geri dönecek dto tipi
 * @param <C> create request tipi (BaseCreateRequest implement etmeli)
 * @param <U> update request tipi (BaseUpdateRequest implement etmeli)
 */
public interface ICrudEndpoints<T, C extends BaseCreateRequest, U extends BaseUpdateRequest> extends ICrud<T, C, U> {
    @GetMapping("/list")
    @Override
    List<T> getAll();

    @GetMapping("/{id}")
    @Override
    T getById(@PathVariable(name = "id") UUID id);

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    T create(@RequestBody C createDto);

    @PatchMapping("/{id}")
    @Override
    T update(
            @PathVariable(name = "id") UUID id,
            @RequestBody U updateDto
    );

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    void delete(@PathVariable(name = "id") UUID id);

    @GetMapping("/exists/{id}")
    @Override
    boolean exists(@PathVariable(name = "id") UUID id);
}
