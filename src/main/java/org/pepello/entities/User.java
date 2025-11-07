package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.UpdatedEntity;

import java.time.LocalDate;

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

}