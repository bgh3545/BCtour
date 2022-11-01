package com.greenart.ch1;

import java.util.ArrayList;

public interface BCUserDao {

	int deleteUser(String id);

	BCUser selectUser(String id);

	// ����� ������ user_info���̺� �����ϴ� �޼���
	int insertUser(BCUser user);

	// �Ű������� ���� ����� ������ user_info ���̺��� update�ϴ� �޼���
	int updateUser(BCUser user);

	void deleteAll() throws Exception;

	ArrayList selectAll();
	
	public BCUser idToTelUser (BCUser bcuser);
	
	public BCUser pwdToTelUser(BCUser bcuser);
	
}