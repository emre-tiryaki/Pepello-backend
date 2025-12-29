package org.pepello.common.service;

import lombok.AllArgsConstructor;
import org.pepello.common.ICrud;
import org.pepello.common.request.BaseCreateRequest;
import org.pepello.common.request.BaseUpdateRequest;
import org.pepello.common.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.pepello.common.exception.notfound.ResourceNotFoundException;
import org.pepello.common.exception.validation.ValidationException;


import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public abstract class BaseCrudService<E, C extends BaseCreateRequest, U extends BaseUpdateRequest> implements ICrud<E, C, U> {
    protected JpaRepository<E, UUID> repository;

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public E getById(UUID id) {
        validateId(id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                                "Entity",
                                "id",
                                id
                        )
                );  //ResourceNotFoundException
    }

    @Override
    public E create(C createDto) {
        validateRequest(createDto);

        E newEntity = buildEntity(createDto);

        return repository.save(newEntity);
    }

    public E create(E entity) {
        if (entity == null)
            throw new ValidationException("entity cannot be null"); //ValidationException

        return repository.save(entity);
    }

    @Override
    public E update(UUID id, U updateDto) {
        validateId(id);
        validateRequest(updateDto);

        E existingEntity = getById(id);
        updateEntity(existingEntity, updateDto);

        return repository.save(existingEntity);
    }


    @Override
    public void delete(UUID id) {
        validateId(id);

        E existingEntity = getById(id);

        repository.delete(existingEntity);
    }

    @Override
    public boolean exists(UUID id) {
        return repository.existsById(id);
    }

    protected void validateId(UUID id) {
        if (id == null) {
            throw new ValidationException("ID cannot be null"); //ValidationException
        }
    }

    protected void validateRequest(Request request) {
        if (request == null)
            throw new ValidationException("Request cannot be null");
    }

    protected abstract E buildEntity(C createDto);

    protected abstract void updateEntity(E existingEntity, U updateDto);
}
