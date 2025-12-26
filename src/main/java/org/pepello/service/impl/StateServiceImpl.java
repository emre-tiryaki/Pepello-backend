package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.observer.ObserverManager;
import org.pepello.common.observer.Observerable;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.events.StateCreatedEvent;
import org.pepello.dto.state.StateCreateRequest;
import org.pepello.dto.state.StateUpdateRequest;
import org.pepello.entities.State;
import org.pepello.service.IStateService;
import org.pepello.observers.IStateObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class StateServiceImpl extends BaseCrudService<State, StateCreateRequest, StateUpdateRequest>
        implements IStateService, Observerable<IStateObserver> {

    @Autowired
    private MediaServiceImpl mediaService;

    private final ObserverManager<IStateObserver> observerManager = new ObserverManager<>();

    public StateServiceImpl(JpaRepository<State, UUID> repository) {
        super(repository);
    }

    @Override
    protected State buildEntity(StateCreateRequest createDto) {
        return State.builder()
                .stateName(createDto.stateName())
                .stateColor(createDto.stateColor())
                .icon(createDto.icon() != null ? mediaService.getById(createDto.icon()) : null)
                .build();
    }

    @Override
    public State create(StateCreateRequest createDto) {
        State newState = super.create(createDto);

        notifyObservers(newState, createDto.projectId());

        return newState;
    }

    @Override
    public void addObserver(IStateObserver observer) {
        observerManager.addObserver(observer);
    }

    private void notifyObservers(State state, java.util.UUID projectId) {
        StateCreatedEvent event = new StateCreatedEvent(state, projectId);
        for (IStateObserver observer : observerManager.getObservers()) {
            observer.onStateCreated(event);
        }
    }

    @Override
    protected void updateEntity(State existingEntity, StateUpdateRequest updateDto) {
        if (updateDto.stateName() != null)
            existingEntity.setStateName(updateDto.stateName());
        if (updateDto.stateColor() != null)
            existingEntity.setStateColor(updateDto.stateColor());
        if (updateDto.icon() != null)
            existingEntity.setIcon(mediaService.getById(updateDto.icon()));
    }
}
