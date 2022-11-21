package com.greenart.ch1.User;

import java.util.List;

public interface BCUserDao {

	//	���̵� ����
	int selectIdCount(String id) throws Exception;

	//	���̵� ã��
	BCUserDto idToEmail(String name, String email) throws Exception;

	//	��й�ȣ ã��
	BCUserDto pwdToEmail(String id, String name, String email) throws Exception;
	
	//  �̸��� Ȯ��
	int confirmEmail(String email) throws Exception;

	//	�� ���� ã��
	BCUserDto selectUser(String id) throws Exception;

	// �� ���� ����
	int deleteUser(String id, String pwd) throws Exception;

	//	�� ���� ���
	int insertUser(BCUserDto bcuserDto) throws Exception;

	//	�� ���� ������Ʈ
	int updateUser(String id, String pwd) throws Exception;

	//	�� ���� ��ü ����
	int deleteAll();

	//	�� ���� ��ü �˻�
	List<BCUserDto> selectAll() throws Exception;

}