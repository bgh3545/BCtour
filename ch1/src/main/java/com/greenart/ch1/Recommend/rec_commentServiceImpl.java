package com.greenart.ch1.Recommend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.ch1.PageHandlerAndSearchCondition.CommSearchCondition;

@Service
public class rec_commentServiceImpl implements rec_commentService {
	
	@Autowired
	rec_commentDao rec_commDao;
	
	@Override
	public int rm_getCount(Integer rec_num) throws Exception{
		return rec_commDao.rm_count(rec_num);
	}
	
	@Override
	public int rm_remove(Integer rec_comm_num,String rec_comm_writer) throws Exception{
		return rec_commDao.rm_delete(rec_comm_num, rec_comm_writer);
	}
	
	@Override
	public int rm_write(rec_commentDto rec_commDto) throws Exception {
		return rec_commDao.rm_insert(rec_commDto);
	}
	
	@Override
	public List<rec_commentDto> rm_getList(Integer rec_num,CommSearchCondition sc) throws Exception {
		return rec_commDao.rm_selectAll(rec_num,sc);
	}
	
	@Override
	public rec_commentDto rm_read(Integer rec_comm_num) throws Exception{
		rec_commentDto rec_commDto = rec_commDao.rm_select(rec_comm_num);
		return rec_commDto;
	}
	
	@Override
	public List<rec_commentDto> rm_getPage(Map map) throws Exception{
		return rec_commDao.rm_selectPage(map);
	}
	
	@Override
	public int rm_modify(rec_commentDto rec_commDto) throws Exception{
		return rec_commDao.rm_update(rec_commDto);
	}
}
