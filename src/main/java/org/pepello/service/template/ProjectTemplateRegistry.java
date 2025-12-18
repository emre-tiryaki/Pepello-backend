package org.pepello.service.template;

import jakarta.annotation.PostConstruct;
import org.pepello.entities.State;
import org.pepello.entities.Task;
import org.pepello.entities.enums.Color;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectTemplateRegistry {
    private List<State> defaultStates;
    private List<Task> defaultTasks;

    @PostConstruct
    public void initTemplates() {
        defaultStates = new ArrayList<>();

        defaultStates.add(State.builder()
                .stateName("Yapılacak")
                .stateColor(Color.green)
                .icon(null)  // Icon nullable yapıldı
                .build());

        defaultStates.add(State.builder()
                .stateName("Yapılıyor")
                .stateColor(Color.purple)
                .icon(null)  // Icon nullable yapıldı
                .build());

        defaultStates.add(State.builder()
                .stateName("Bitti")
                .stateColor(Color.red)
                .icon(null)  // Icon nullable yapıldı
                .build());

        defaultTasks = new ArrayList<>();
        defaultTasks.add(Task.builder()
                .taskTitle("Projenize hoşgeldiniz!!")
                .taskDescription("Bu otomatik oluşturulmuş bir başlangıç görevidir")
                .state(defaultStates.getFirst())
                .media(null)  // Media nullable olmalı
                .isFinished(false)
                .build());

        System.out.println("Default state'ler ve görevler oluşturuldu");
    }

    public List<State> getDefaultStates() {
        return defaultStates.stream()
                .map(State::cloneEntity)
                .toList();
    }

    public List<Task> getDefaultTasks() {
        return defaultTasks.stream()
                .map(Task::cloneEntity)
                .toList();
    }
}
