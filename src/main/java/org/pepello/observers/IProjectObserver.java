package org.pepello.observers;

import org.pepello.common.observer.Observer;
import org.pepello.dto.events.ProjectCreatedEvent;

public interface IProjectObserver extends Observer {
    void onProjectCreated(ProjectCreatedEvent event);
}
