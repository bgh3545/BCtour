package com.greenart.ch1;

import java.util.ArrayList;

public interface RollCommentDao {

	int insertCmt(RollComment cmt);

	ArrayList<RollComment> selectAll();

	// auto_increment�� delete�ص� ���� ���� �����Ƿ� �ʱ�ȭ ����
	void deleteAll() throws Exception;

	// �޽��� ���� ��ȸ
	int cnt();

}