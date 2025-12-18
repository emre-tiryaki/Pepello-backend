package org.pepello.controller;

import org.pepello.dto.dashboard.DtoDashboard;
import org.pepello.dto.state.DtoState;
import org.pepello.dto.state.StateCreateRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface IProjectController {
    @PostMapping("/{projectId}/add-state")
    DtoState addStateToProject(
            @PathVariable UUID projectId,
            @RequestBody StateCreateRequest request
    );

    @GetMapping("/{projectId}/dashboard]")
    DtoDashboard getDashboard(
            @PathVariable UUID projectId
    );
}
