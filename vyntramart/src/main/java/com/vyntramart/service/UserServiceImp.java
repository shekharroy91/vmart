package com.vyntramart.service;

import java.util.Arrays;
import java.util.HashSet;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vyntramart.models.User;
import com.vyntramart.repository.RoleRepository;
import com.vyntramart.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository rolerepository;
	@Autowired
	UserRepository userrepository;
	
	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		com.vyntramart.models.Role userRole=rolerepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<com.vyntramart.models.Role>(Arrays.asList(userRole)));
		userrepository.save(user);

	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getRoles());
		return false;
	}

}
