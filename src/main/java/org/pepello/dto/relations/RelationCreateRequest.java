package org.pepello.dto.relations;

import org.pepello.common.request.BaseCreateRequest;

import java.util.UUID;

public record RelationCreateRequest(
        UUID id
) implements BaseCreateRequest {
}
