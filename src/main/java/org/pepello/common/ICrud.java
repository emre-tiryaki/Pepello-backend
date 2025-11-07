package org.pepello.common;

import java.util.List;
import java.util.UUID;

/*
 * Crud implementasyonları için generic interface
 * @param <T> geri dönecek
 * @param <C> oluşturma isteği
 * @param <U> güncelleme isteği
 */
public interface ICrud<T, C, U> {
    //tüm veritabanını çekmek
    List<T> getAll();

    //id'ye göre veritabanından eleman çekmek
    T getById(UUID id);

    //veritabanına eleman eklemek
    T create(C createDto);

    //veritabanındaki elemanı güncellemek
    T update(UUID id, U updateDto);

    //veritabanından eleman silmek
    void delete(UUID id);
}
