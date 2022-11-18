package com.greenart.ch1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.greenart.ch1.Community.CommunityDao;
import com.greenart.ch1.Community.CommunityDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class insertTest {
	
	@Autowired
	private CommunityDao commDao;
	
	@Test
	public void test() throws Exception {

		for(int i=1; i<=50; i++) {
			CommunityDto commDto = new CommunityDto(i+"번글", "no content", "asdf"); //RecommendDto에 제목, 내용, 작성자 저장
			commDao.c_insert(commDto); // DB Recommend 테이블에 RecommendDto에 저장된 내용 입력
		}
	}
}
