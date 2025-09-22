package com.learn.Learn1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learn.Learn1.models.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	java.util.Optional<User> findByEmail(String email);
}