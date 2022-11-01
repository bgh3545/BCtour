package com.greenart.ch1;

import java.util.ArrayList;

public interface RollCommentDao {

	int insertCmt(RollComment cmt);

	ArrayList<RollComment> selectAll();

	// auto_increment는 delete해도 값이 남아 있으므로 초기화 진행
	void deleteAll() throws Exception;

	// 메시지 개수 조회
	int cnt();

}