package com.yupexx.bazaar.api.service;

import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username 1:-"+username);
		UserModel user = userDao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

	public UserModel save(UserModel user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}
	
	public UserModel updatePinPassword(UserModel user) {
		user.setPassword(user.getPassword());
		return userDao.save(user);
	}
	
	public UserModel saveGoogleUser(UserModel user) {
		user.setPassword(bcryptEncoder.encode(user.getgId()));
		return userDao.save(user);
	}
	
	public UserModel saveFBUser(UserModel user) {
		user.setPassword(bcryptEncoder.encode(user.getFbId()));
		return userDao.save(user);
	}
	
}