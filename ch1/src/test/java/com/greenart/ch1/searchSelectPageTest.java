package com.greenart.ch1;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class searchSelectPageTest {

	@Autowired
	BoardDao boardDao;
	
	@Test
	public void test() throws Exception {
		boardDao.deleteAll();
		for(int i=1; i<=20; i++) {
			BoardDto boardDto = new BoardDto("title"+i,"content"+i,"asdf");
			boardDao.insert(boardDto);
		}
		SearchCondition sc = new SearchCondition(1,100,"title2","T");
		List<BoardDto> list = boardDao.searchSelectPage(sc);
		System.out.println("list="+list);
		assertTrue(list.size()==2);
	}
	
	@Test
	public void searchResultCntTest() throws Exception{
		boardDao.deleteAll();
		for(int i=1; i<=20; i++) {
			BoardDto boardDto = new BoardDto("title"+i,"content"+i,"asdf");
			boardDao.insert(boardDto);
		}
		SearchCondition sc = new SearchCondition(1,100,"title2","T");
		System.out.println(sc);
		int cnt = boardDao.searchResultCnt(sc);
		System.out.println(cnt);
		assertTrue(cnt==2);
	}

}
