package com.greenart.ch1;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class UpdateTest {
	@Autowired
	UserDao userDao;
	
	final int FAIL = 0;
	
	@Test
	public void updateUserTest() throws Exception {
		
		userDao.deleteAll(); // 모든 데이터 삭제 메서드
		
		Calendar cal = Calendar.getInstance();
		cal.clear(); // 달력 모든 값 초기화
		cal.set(2021, 2, 1); // 0부터 시작 2는 3월
		
		DBUser user = new DBUser("asdf","1234","abcd","aaa@aa.com", new Date(), "facebook", new Date());
		int rowCnt = userDao.insertUser(user);
		System.out.println("user:" + userDao.selectUser(user.getId()));
		System.out.println(rowCnt);
		assertTrue(rowCnt==1); // 삽입 성공
		
		DBUser user2 = new DBUser("asdf","123","abd","aa@aa.com", new Date(cal.getTimeInMillis()), "instagram", new Date());
		rowCnt = userDao.updateUser(user2);
		System.out.println("user2:" + userDao.selectUser(user2.getId()));
		assertTrue(rowCnt==1); // 업데이트 성공
		
		user = userDao.selectUser(user2.getId());
		assertTrue(user2.equals(user));
	}

}
