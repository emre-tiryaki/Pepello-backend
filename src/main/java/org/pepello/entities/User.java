package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends UpdatedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_pic_id")
    private Media profilePic;

    @NotNull(message = "first name should be given")
    @NotBlank(message = "first name cannot be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "last name should be given")
    @NotBlank(message = "last name cannot be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "email should be given")
    @NotBlank(message = "email cannot be blank")
    @Email(message = "should give valid email")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "birthday cannot be null")
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    /**
     * Kullanıcının yaptığı yorumlar.
     * cascade = ALL: User silinince yorumlar da silinir
     * orphanRemoval = true: İlişki koparılınca yorum silinir
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    /**
     * Kullanıcıya atanmış task'lar (TaskAsigneeRelation).
     * cascade = ALL: User silinince atamaları da sil
     * orphanRemoval = true: İlişki koparılınca atama silinir
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TaskAsigneeRelation> taskAssigneeRelations = new ArrayList<>();

    /**
     * Kullanıcının üye olduğu team'ler (UserTeamRelation).
     * cascade = ALL: User silinince team ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<UserTeamRelation> userTeamRelations = new ArrayList<>();

    /**
     * Kullanıcının rolleri (RoleUserRelation).
     * cascade = ALL: User silinince rol ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RoleUserRelation> roleUserRelations = new ArrayList<>();

}