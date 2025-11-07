package org.pepello.common.controller;

import org.pepello.common.ICrud;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ICrudEndpoints<T, C, U> extends ICrud<T, C, U> {
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
}
