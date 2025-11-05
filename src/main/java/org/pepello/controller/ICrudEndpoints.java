package org.pepello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
 * CRUD işlemleri için generic interface
 * @param <T> geri dönecek DTO tipi
 * @param <C> oluşturma için gelecek veri tipi
 * @param <U> güncelleme için gelecek veri tipi
 */
public interface ICrudEndpoints<T, C, U> {
    //tüm veritabanını çekmek
    @GetMapping("/list")
    List<T> getAll();

    //id'ye göre veritabanından eleman çekmek
    @GetMapping("/{id}")
    T getById(@PathVariable(name = "id") UUID id);

    //veritabanına eleman eklemek
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    T create(@RequestBody C createDto);

    //veritabanındaki elemanı güncellemek
    @PatchMapping("/{id}")
    T update(
            @PathVariable(name = "id") UUID id,
            @RequestBody U updateDto
    );

    //veritabanından eleman silmek
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") UUID id);
}
