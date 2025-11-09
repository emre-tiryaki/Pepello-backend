package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.dto.state.StateCreateRequest;
import org.pepello.dto.state.StateUpdateRequest;
import org.pepello.entities.State;
import org.pepello.repository.StateRepository;
import org.pepello.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StateServiceImpl implements IStateService {
    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public State getById(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        return stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State bulunamadı: " + id));
    }

    @Override
    public State create(StateCreateRequest createDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (createDto == null) {
            throw new RuntimeException("CreateDto null olamaz");
        }

        State newState = State.builder()
                .stateName(createDto.stateName())
                .stateColor(createDto.stateColor())
                //TODO: buraya mantık ekle
                .icon(null)
                .build();

        return stateRepository.save(newState);
    }

    @Override
    public State update(UUID id, StateUpdateRequest updateDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        if (updateDto == null) {
            throw new RuntimeException("UpdateDto null olamaz");
        }

        State existingState = getById(id);

        if (updateDto.stateName() != null) existingState.setStateName(updateDto.stateName());
        if (updateDto.stateColor() != null) existingState.setStateColor(updateDto.stateColor());
        if (updateDto.icon() != null) {
            //TODO: buraya mantık ekle
            existingState.setIcon(null);
        }

        return stateRepository.save(existingState);
    }

    @Override
    public void delete(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        State existingState = getById(id);

        stateRepository.delete(existingState);
    }

    @Override
    public boolean exists(UUID id) {
        return stateRepository.existsById(id);
    }
}
