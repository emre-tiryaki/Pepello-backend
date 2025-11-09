package org.pepello.common;

import org.pepello.common.request.BaseCreateRequest;
import org.pepello.common.request.BaseUpdateRequest;

import java.util.List;
import java.util.UUID;

/**
 * Crud implementasyonları için generic interface
 *
 * @param <T> geri dönecek tip (DTO tipi)
 * @param <C> create request tipi (BaseCreateRequest implement etmeli)
 * @param <U> update request tipi (BaseUpdateRequest implement etmeli)
 */
public interface ICrud<T, C extends BaseCreateRequest, U extends BaseUpdateRequest> {
    /**
     * Tüm veritabanını çekmek
     */
    List<T> getAll();

    /**
     * ID'ye göre veritabanından eleman çekmek
     */
    T getById(UUID id);

    /**
     * Veritabanına eleman eklemek
     *
     * @param createDto BaseCreateRequest implement eden create request objesi
     */
    T create(C createDto);

    /**
     * Veritabanındaki elemanı güncellemek
     *
     * @param updateDto BaseUpdateRequest implement eden update request objesi
     */
    T update(UUID id, U updateDto);

    /**
     * Veritabanından eleman silmek
     */
    void delete(UUID id);

    /**
     * ID'ye göre elemanın var olup olmadığını kontrol eder
     */
    boolean exists(UUID id);
}
