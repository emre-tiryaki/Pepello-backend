package org.pepello.controller;

import org.pepello.common.controller.ICrudEndpoints;
import org.pepello.dto.state.DtoState;
import org.pepello.dto.state.StateCreateRequest;
import org.pepello.dto.state.StateUpdateRequest;

public interface IStateController extends ICrudEndpoints<DtoState, StateCreateRequest, StateUpdateRequest> {
}
