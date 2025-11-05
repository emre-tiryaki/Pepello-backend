package org.pepello.service.impl;

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
public class StateServiceImpl implements IStateService {
    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public State getById(UUID id) {
        //TODO: hata
        if (id == null)
            return null;

        return stateRepository.findById(id)
                .orElseThrow(null);
    }

    @Override
    public State create(StateCreateRequest createDto) {
        //TODO: hata
        if (createDto == null)
            return null;

        State newState = new State();

        newState.setStateName(createDto.stateName());
        newState.setStateColor(createDto.stateColor());
        //TODO: buraya mantık ekle
        newState.setIcon(null);

        return stateRepository.save(newState);
    }

    @Override
    public State update(UUID id, StateUpdateRequest updateDto) {
        //TODO: hata var
        if (id == null || updateDto == null)
            return null;

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
        //TODO: hata
        if (id == null)
            return;

        State existingState = getById(id);

        stateRepository.delete(existingState);
    }
}
