package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.sec.User;
import com.clubes.especialidades.api.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo repo;

	@Override
	public User getByUsername(String username) {
		return repo.findByUsername(username).orElseThrow(EntityNotFoundException::new);
	}
}
