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
		
		userDao.deleteAll(); // ��� ������ ���� �޼���
		
		Calendar cal = Calendar.getInstance();
		cal.clear(); // �޷� ��� �� �ʱ�ȭ
		cal.set(2021, 2, 1); // 0���� ���� 2�� 3��
		
		DBUser user = new DBUser("asdf","1234","abcd","aaa@aa.com", new Date(), "facebook", new Date());
		int rowCnt = userDao.insertUser(user);
		System.out.println("user:" + userDao.selectUser(user.getId()));
		System.out.println(rowCnt);
		assertTrue(rowCnt==1); // ���� ����
		
		DBUser user2 = new DBUser("asdf","123","abd","aa@aa.com", new Date(cal.getTimeInMillis()), "instagram", new Date());
		rowCnt = userDao.updateUser(user2);
		System.out.println("user2:" + userDao.selectUser(user2.getId()));
		assertTrue(rowCnt==1); // ������Ʈ ����
		
		user = userDao.selectUser(user2.getId());
		assertTrue(user2.equals(user));
	}

}
