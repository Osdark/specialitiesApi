package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.sec.User;

public interface UserService {
	User getByUsername(String username);
}
