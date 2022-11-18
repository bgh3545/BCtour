package com.greenart.ch1.Community;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	CommunityDao commDao;
	
	@Override
	public int c_getCount() throws Exception{
		return commDao.c_count();
	}
	
	@Override
	public int c_remove(Integer comm_num,String comm_writer) throws Exception{
		return commDao.c_delete(comm_num, comm_writer);
	}
	
	@Override
	public int c_writer(CommunityDto commDto) throws Exception {
		return commDao.c_insert(commDto);
	}
	
	@Override
	public List<CommunityDto> c_getList() throws Exception {
		return commDao.c_selectAll();
	}
	
	@Override
	public CommunityDto c_read(Integer comm_num) throws Exception{
		CommunityDto CommunityDto = commDao.c_select(comm_num);
		commDao.c_increaseViewCnt(comm_num);
		return CommunityDto;
	}
	
	@Override
	public int c_increaseCommCnt(Integer comm_num) throws Exception{
		return commDao.c_increaseCommCnt(comm_num);
	}
	
	@Override
	public int c_decreaseCommCnt(Integer comm_num) throws Exception{
		return commDao.c_decreaseCommCnt(comm_num);
	}
	
	@Override
	public List<CommunityDto> c_getPage(Map map) throws Exception{
		return commDao.c_selectPage(map);
	}
	
	@Override
	public int c_modify(CommunityDto commDto) throws Exception{
		return commDao.c_update(commDto);
	}
	
	@Override
	public int c_getSearchResultCnt(SearchCondition sc) throws Exception{
		return commDao.c_searchResultCnt(sc);
	}
	
	@Override
	public List<CommunityDto> c_getSearchResultPage(SearchCondition sc) throws Exception{
		return commDao.c_searchSelectPage(sc);
	}
}
