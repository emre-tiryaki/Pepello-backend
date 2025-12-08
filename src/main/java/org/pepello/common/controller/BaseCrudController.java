package org.pepello.common.controller;

import lombok.AllArgsConstructor;
import org.pepello.common.ICrud;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.common.request.BaseCreateRequest;
import org.pepello.common.request.BaseUpdateRequest;

import java.util.List;
import java.util.UUID;

/**
 * Standart CRUD işlemlerini sağlayan temel controller sınıfı.
 * Alt sınıflar bu sınıfı extend ederek otomatik olarak tüm CRUD endpoint'lerini kazanır.
 *
 * @param <E> Entity tipi
 * @param <D> DTO tipi
 * @param <C> Create request tipi
 * @param <U> Update request tipi
 */

@AllArgsConstructor
public abstract class BaseCrudController<E, D, C extends BaseCreateRequest, U extends BaseUpdateRequest> implements ICrudEndpoints<D, C, U> {
    protected ICrud<E, C, U> service;
    protected BaseMapper<E, D> mapper;

    @Override
    public List<D> getAll() {
        return service.getAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public D getById(UUID id) {
        return mapper.toDto(service.getById(id));
    }

    @Override
    public D create(C createDto) {
        return mapper.toDto(service.create(createDto));
    }

    @Override
    public D update(UUID id, U updateDto) {
        return mapper.toDto(service.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        service.delete(id);
    }

    @Override
    public boolean exists(UUID id) {
        return service.exists(id);
    }
}
