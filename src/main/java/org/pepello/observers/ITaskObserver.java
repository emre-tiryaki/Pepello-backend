package org.pepello.observers;

import org.pepello.dto.events.TaskAssignedEvent;

public interface ITaskObserver {
    void onAssign(TaskAssignedEvent event);
}
