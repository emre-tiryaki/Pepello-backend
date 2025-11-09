package org.pepello.service;

import org.pepello.common.ICrud;
import org.pepello.dto.state.StateCreateRequest;
import org.pepello.dto.state.StateUpdateRequest;
import org.pepello.entities.State;

public interface IStateService extends ICrud<State, StateCreateRequest, StateUpdateRequest> {
}
