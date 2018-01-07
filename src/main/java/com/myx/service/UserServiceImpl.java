package com.myx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myx.dao.UserRepository;
import com.myx.po.User;
import com.myx.util.MD5Utils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User checkUser(String username, String password) {
		// TODO Auto-generated method stub
		User user =userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
		return user;
	}

}
