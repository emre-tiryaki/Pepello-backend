package org.pepello.dto.dashboard;

import lombok.*;
import org.pepello.entities.State;
import org.pepello.entities.Task;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
        List<State> states;
        List<Task> tasks;
}
