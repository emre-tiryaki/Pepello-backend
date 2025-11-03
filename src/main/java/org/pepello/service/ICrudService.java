package org.pepello.service;

import java.util.List;
import java.util.UUID;

/*
 * Crud servisleri için generic interface
 * @param <T> geri dönecek entity
 * @param <C> oluşturma isteği
 * @param <U> güncelleme isteği
 */
public interface ICrudService<T, C, U> {
    // tüm verileri çekmek
    List<T> getAll();

    // id'ye göre veri çekmek
    T getById(UUID id);

    // veri oluşturmak
    T create(C createDto);

    //veriyi güncellemek
    T update(UUID id, U updateDto);

    //veriyi silmek
    void delete(UUID id);
}
