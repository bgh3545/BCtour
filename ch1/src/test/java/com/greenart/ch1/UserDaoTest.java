package com.greenart.ch1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoTest {
	@Autowired
	UserDao userDao;
	
	final int FAIL=0;
	
	@Test
	public void updateUserTest() throws Exception {
		
		userDao.deleteAll();
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 2, 1);
		
		User user = new User("asdf","1234","asd","asd@asd.com",new Date(),"facebook",new Date());
		int rowCnt = userDao.insertUser(user);
		System.out.println("user:"+userDao.SelectUser(user.getId()));
		System.out.println(rowCnt);
		assertTrue(rowCnt==1);
		
		User user2 = new User("asdf","123456","asdf","asd@asd.com",new Date(cal.getTimeInMillis()),"facebook",new Date());
		rowCnt=userDao.updateUser(user2);
		System.out.println("user2:"+userDao.SelectUser(user2.getId()));
		assertTrue(rowCnt==1);
		
		user = userDao.SelectUser(user2.getId());
		assertTrue(user2.equals(user));
	}

}
