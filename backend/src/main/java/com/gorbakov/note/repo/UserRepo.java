package com.gorbakov.note.repo;

import com.gorbakov.note.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findBySub(String sub);
}
