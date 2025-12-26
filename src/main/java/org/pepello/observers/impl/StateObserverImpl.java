package org.pepello.observers.impl;

import jakarta.annotation.PostConstruct;
import org.pepello.dto.events.StateCreatedEvent;
import org.pepello.observers.IStateObserver;
import org.pepello.service.impl.ProjectStateRelationServiceImpl;
import org.pepello.service.impl.StateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateObserverImpl implements IStateObserver {

    @Autowired
    private StateServiceImpl stateService;
    @Autowired
    private ProjectStateRelationServiceImpl projectStateRelationService;

    @PostConstruct
    public void registerObserver() {
        stateService.addObserver(this);
    }

    @Override
    public void onStateCreated(StateCreatedEvent event) {
        if (event.projectId() != null) {
            projectStateRelationService.addRelation(event.projectId(), event.state().getId());
        }
    }
}
