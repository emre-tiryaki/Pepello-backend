package org.pepello.service;

import org.pepello.dto.state.StateCreateRequest;
import org.pepello.dto.state.StateUpdateRequest;
import org.pepello.entities.State;

public interface IStateService extends ICrudService<State, StateCreateRequest, StateUpdateRequest> {
}
