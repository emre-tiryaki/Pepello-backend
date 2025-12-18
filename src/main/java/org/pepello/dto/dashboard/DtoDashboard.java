package org.pepello.dto.dashboard;

import org.pepello.dto.state.DtoState;
import org.pepello.dto.task.DtoTask;

import java.util.List;

public record DtoDashboard(
        List<DtoState> states,
        List<DtoTask> tasks
) {
}
