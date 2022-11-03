package com.greenart.ch1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class insertTest {
	
	@Autowired
	private CommunityDao commDao;
	
	@Test
	public void test() throws Exception {
		commDao.c_deleteAll();
		for(int i=1; i<=220; i++) {
			CommunityDto commDto = new CommunityDto("title"+i, "no content", "asdf");
			commDao.c_insert(commDto);
		}
	}
}
