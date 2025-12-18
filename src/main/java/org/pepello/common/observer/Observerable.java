package org.pepello.common.observer;

import org.pepello.dto.events.TaskAssignedEvent;
import org.pepello.entities.Task;
import org.pepello.entities.User;
import org.pepello.observers.ITaskObserver;

public interface Observerable<O extends Observer> {

    void addObserver(O observer);
}
