package com.anuradha.fcmpushnotification.repository;

import com.anuradha.fcmpushnotification.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :name")
    Optional<User> getByName(@Param("name") String name);
}
