package com.vyntramart.service;

import com.vyntramart.models.User;

public interface UserService {
	public void saveUser(User user);
	public boolean isUserAlreadyPresent(User user);
}
