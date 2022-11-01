package com.greenart.ch1;

import java.util.ArrayList;

public interface UserDao {

	int deleteUser(String id);

	DBUser selectUser(String id);

	// ����� ������ user_info���̺� �����ϴ� �޼���
	int insertUser(DBUser user);

	// �Ű������� ���� ����� ������ user_info ���̺��� update�ϴ� �޼���
	int updateUser(DBUser user);

	void deleteAll() throws Exception;

	ArrayList selectAll();
}