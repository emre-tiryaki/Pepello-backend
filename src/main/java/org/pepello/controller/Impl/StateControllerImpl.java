package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IStateController;
import org.pepello.dto.state.DtoState;
import org.pepello.dto.state.StateCreateRequest;
import org.pepello.dto.state.StateUpdateRequest;
import org.pepello.entities.State;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state")
public class StateControllerImpl extends BaseCrudController<State, DtoState, StateCreateRequest, StateUpdateRequest> implements IStateController {
    public StateControllerImpl(ICrud<State, StateCreateRequest, StateUpdateRequest> service, BaseMapper<State, DtoState> mapper) {
        super(service, mapper);
    }
}
