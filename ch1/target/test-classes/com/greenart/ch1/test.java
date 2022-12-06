package com.greenart.ch1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.greenart.ch1.Recommend.RecommendDto;
import com.greenart.ch1.Recommend.RecommendService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class test {

	@Autowired
	RecommendService rs;
	
	@Test
	public void test() {
		for(int i=0; i<50; i++) {
			RecommendDto rd = new RecommendDto(null, "title"+i, "content", "asdf", 0,0,0,0,0, now(),now(),null );
			rs.r_writer(rd);
		}
	}

}
