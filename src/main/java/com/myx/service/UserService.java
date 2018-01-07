package com.myx.service;

import com.myx.po.User;

public interface UserService {

	User checkUser(String username,String password);
}
