package com.project.bctour;

import java.util.List;

public interface BCUserDao {

	//	���̵� ����
	int selectIdCount(String id) throws Exception;

	//	���̵� ã��
	BCUserDto idToTelUser(String id, String tel) throws Exception;

	//	��й�ȣ ã��
	BCUserDto pwdToTelUser(String id, String pwd, String tel) throws Exception;

	//	���� ���� ã��
	BCUserDto selectUser(String id) throws Exception;

	// ���� ���� ����
	int deleteUser(String id, String pwd) throws Exception;

	//	���� ���� ���
	int insertUser(BCUserDto bcuserDto) throws Exception;

	//	���� ���� ������Ʈ
	int updateUser(String id, String pwd) throws Exception;

	//	���� ���� ��ü ����
	int deleteAll();

	//	���� ���� ��ü �˻�
	List<BCUserDto> selectAll() throws Exception;

}