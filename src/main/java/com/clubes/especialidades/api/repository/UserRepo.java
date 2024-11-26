package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.sec.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
