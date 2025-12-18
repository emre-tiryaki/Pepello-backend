package org.pepello.common.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic observer yönetim sınıfı.
 * Composition pattern ile observer listesini yönetir.
 *
 * @param <O> Observer interface tipi
 */
public class ObserverManager<O extends Observer> {
    private final List<O> observers = new ArrayList<>();

    /**
     * Yeni bir observer ekler
     * @param observer eklenecek observer
     */
    public void addObserver(O observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Tüm observer'ları döndürür
     * @return observer listesi (unmodifiable)
     */
    public List<O> getObservers() {
        return List.copyOf(observers);
    }
}

