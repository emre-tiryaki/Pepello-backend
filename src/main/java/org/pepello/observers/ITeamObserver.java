package org.pepello.observers;

import org.pepello.common.observer.Observer;
import org.pepello.dto.events.TeamCreatedEvent;

public interface ITeamObserver extends Observer {
    void onTeamCreated(TeamCreatedEvent event);
}
