package org.pepello.common.observer;

public interface Observerable<O extends Observer> {

    void addObserver(O observer);
}
