package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.UpdatedEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "projects")
@AttributeOverride(name = "id", column = @Column(name = "project_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project extends UpdatedEntity {

    @NotNull(message = "project should have a name")
    @NotBlank(message = "project should have a valid name")
    @Column(name = "project_name", nullable = false)
    private String projectName;

    @NotBlank(message = "project description cannot be blank")
    @Column(name = "project_description")
    private String projectDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    private Media icon;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProjectStateRelation> projectStateRelations = new ArrayList<>();

    /**
     * Projenin takımları (TeamProjectRelation).
     * cascade = ALL: Project silinince takım ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TeamProjectRelation> teamProjectRelations = new ArrayList<>();

}