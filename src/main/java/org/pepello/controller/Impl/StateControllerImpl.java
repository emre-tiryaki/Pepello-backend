package org.pepello.controller.Impl;

import org.pepello.controller.IStateController;
import org.pepello.dto.state.DtoState;
import org.pepello.dto.state.StateCreateRequest;
import org.pepello.dto.state.StateUpdateRequest;
import org.pepello.mappers.StateMapper;
import org.pepello.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/state")
public class StateControllerImpl implements IStateController {
    @Autowired
    private IStateService stateService;
    @Autowired
    private StateMapper stateMapper;

    @Override
    public List<DtoState> getAll() {
        return stateService.getAll().stream()
                .map(stateMapper::toDto)
                .toList();
    }

    @Override
    public DtoState getById(UUID id) {
        return stateMapper.toDto(stateService.getById(id));
    }

    @Override
    public DtoState create(StateCreateRequest createDto) {
        return stateMapper.toDto(stateService.create(createDto));
    }

    @Override
    public DtoState update(UUID id, StateUpdateRequest updateDto) {
        return stateMapper.toDto(stateService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        stateService.delete(id);
    }

    @Override
    public boolean exists(UUID id) {
        return stateService.exists(id);
    }
}
