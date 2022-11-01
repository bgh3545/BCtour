package com.greenart.ch1;

import java.util.ArrayList;

public interface BCUserDao {

	int deleteUser(String id);

	BCUser selectUser(String id);

	// 사용자 정보를 user_info테이블에 저장하는 메서드
	int insertUser(BCUser user);

	// 매개변수로 받은 사용자 정보로 user_info 테이블을 update하는 메서드
	int updateUser(BCUser user);

	void deleteAll() throws Exception;

	ArrayList selectAll();
	
	public BCUser idToTelUser (BCUser bcuser);
	
	public BCUser pwdToTelUser(BCUser bcuser);
	
}