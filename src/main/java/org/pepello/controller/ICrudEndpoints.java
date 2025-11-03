package org.pepello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<T>> getAll();

    //id'ye göre veritabanından eleman çekmek
    @GetMapping("/{id}")
    ResponseEntity<T> getById(@PathVariable(name = "id") UUID id);

    //veritabanına eleman eklemek
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<T> create(@RequestBody C createDto);

    //veritabanındaki elemanı güncellemek
    @PatchMapping("/{id}")
    ResponseEntity<T> update(
            @PathVariable(name = "id") UUID id,
            @RequestBody U updateDto
    );

    //veritabanından eleman silmek
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id);
}
