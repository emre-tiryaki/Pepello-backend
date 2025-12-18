package org.pepello.observers;

import org.pepello.common.observer.Observer;
import org.pepello.dto.events.TaskAssignedEvent;

public interface ITaskObserver extends Observer {
    void onAssign(TaskAssignedEvent event);
}
