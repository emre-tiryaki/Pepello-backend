package org.pepello.observers;

import org.pepello.dto.events.TeamCreatedEvent;

public interface ITeamObserver {
    void onTeamCreated(TeamCreatedEvent event);
}
