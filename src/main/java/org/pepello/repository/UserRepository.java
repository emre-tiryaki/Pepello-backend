package org.pepello.repository;

import org.pepello.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE lower(concat(coalesce(u.firstName, ''), ' ', coalesce(u.lastName, ''))) LIKE lower(concat('%', :name, '%'))")
    List<User> findByFullName(@Param("name") String name);
}
