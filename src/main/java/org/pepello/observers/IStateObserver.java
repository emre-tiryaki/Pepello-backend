package org.pepello.observers;

import org.pepello.common.observer.Observer;
import org.pepello.dto.events.StateCreatedEvent;

public interface IStateObserver extends Observer {
    void onStateCreated(StateCreatedEvent event);
}
