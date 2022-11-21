package com.greenart.ch1.User;

import java.util.List;

public interface BCUserDao {

	//	아이디 개수
	int selectIdCount(String id) throws Exception;

	//	아이디 찾기
	BCUserDto idToEmail(String name, String email) throws Exception;

	//	비밀번호 찾기
	BCUserDto pwdToEmail(String id, String name, String email) throws Exception;
	
	//  이메일 확인
	int confirmEmail(String email) throws Exception;

	//	고객 정보 찾기
	BCUserDto selectUser(String id) throws Exception;

	// 고객 정보 삭제
	int deleteUser(String id, String pwd) throws Exception;

	//	고객 정보 등록
	int insertUser(BCUserDto bcuserDto) throws Exception;

	//	고객 정보 업데이트
	int updateUserPwd(String id, String pwd) throws Exception;
	int updateUserEmail(String id, String email) throws Exception;
	int updateUserTel(String id, String tel) throws Exception;

	//	고객 정보 전체 삭제
	int deleteAll();

	//	고객 정보 전체 검색
	List<BCUserDto> selectAll() throws Exception;

}