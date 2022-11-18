package com.greenart.ch1.Community;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class comm_commentServiceImpl implements comm_commentService {
	
	@Autowired
	comm_commentDao comm_commDao;
	
	@Override
	public int cm_getCount(Integer comm_num) throws Exception{
		return comm_commDao.cm_count(comm_num);
	}
	
	@Override
	public int cm_remove(Integer comm_comm_num,String comm_comm_writer) throws Exception{
		return comm_commDao.cm_delete(comm_comm_num, comm_comm_writer);
	}
	
	@Override
	public int cm_write(comm_commentDto comm_commDto) throws Exception {
		return comm_commDao.cm_insert(comm_commDto);
	}
	
	@Override
	public List<comm_commentDto> cm_getList(Integer comm_num) throws Exception {
		return comm_commDao.cm_selectAll(comm_num);
	}
	
	@Override
	public comm_commentDto cm_read(Integer comm_comm_num) throws Exception{
		comm_commentDto comm_commDto = comm_commDao.cm_select(comm_comm_num);
		return comm_commDto;
	}
	
	@Override
	public List<comm_commentDto> cm_getPage(Map map) throws Exception{
		return comm_commDao.cm_selectPage(map);
	}
	
	@Override
	public int cm_modify(comm_commentDto comm_commDto) throws Exception{
		return comm_commDao.cm_update(comm_commDto);
	}
}
