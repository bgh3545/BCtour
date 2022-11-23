package com.greenart.ch1;

import java.util.ArrayList;

public interface UserDao {

	int deleteUser(String id);

	User SelectUser(String id);

	int insertUser(User user);

	int updateUser(User user);
	
	void deleteAll() throws Exception;

	ArrayList SelectAll();

	User SelectUserEmail(String email);

}