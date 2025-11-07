package org.pepello.service;

import org.pepello.dto.state.StateCreateRequest;
import org.pepello.dto.state.StateUpdateRequest;
import org.pepello.entities.State;
import org.pepello.common.ICrud;

public interface IStateService extends ICrud<State, StateCreateRequest, StateUpdateRequest> {
}
