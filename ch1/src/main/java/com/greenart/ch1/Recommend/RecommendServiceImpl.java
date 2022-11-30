package com.greenart.ch1.Recommend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Autowired
	RecommendDao recDao;
	
	@Override
	public int r_getCount() throws Exception{
		return recDao.r_count();
	}
	
	@Override
	public int r_remove(Integer rec_num,String rec_writer) throws Exception{
		return recDao.r_delete(rec_num, rec_writer);
	}
	
	@Override
	public int r_writer(RecommendDto recDto) throws Exception {
		return recDao.r_insert(recDto);
	}
	
	@Override
	public List<RecommendDto> r_getList() throws Exception {
		return recDao.r_selectAll();
	}
	
	@Override
	public List<RecommendDto> r_getTopList() throws Exception {
		return recDao.r_selectTop();
	}
	
	@Override
	public RecommendDto r_read(Integer rec_num) throws Exception{
		RecommendDto recommandDto = recDao.r_select(rec_num);
		recDao.r_increaseViewCnt(rec_num);
		return recommandDto;
	}
	
	@Override
	public int r_increaseCommCnt(Integer rec_num) throws Exception{
		return recDao.r_increaseCommCnt(rec_num);
	}
	
	@Override
	public int r_decreaseCommCnt(Integer rec_num) throws Exception{
		return recDao.r_decreaseCommCnt(rec_num);
	}
	
	@Override
	public int r_increaseRecCnt(Integer rec_num) throws Exception{
		return recDao.r_increaseRecCnt(rec_num);
	}
	
	@Override
	public int r_decreaseRecCnt(Integer rec_num) throws Exception{
		return recDao.r_decreaseRecCnt(rec_num);
	}
	
	@Override
	public int r_increaseRecBool(Integer rec_num, String mem_id) throws Exception{
		return recDao.r_increaseRecBool(rec_num, mem_id);
	}
	
	@Override
	public int r_decreaseRecBool(Integer rec_num, String mem_id) throws Exception{
		return recDao.r_decreaseRecBool(rec_num, mem_id);
	}

	@Override
	public RecommendDto rb_read(Integer rec_num, String mem_id) throws Exception{
		RecommendDto recommandDto = recDao.rb_select(rec_num, mem_id);
		return recommandDto;
	}
	
	@Override
	public int rb_write(RecommendDto recDto) throws Exception {
		return recDao.rb_insert(recDto);
	}
	
	@Override
	public List<RecommendDto> r_getPage(Map map) throws Exception{
		return recDao.r_selectPage(map);
	}
	
	@Override
	public int r_modify(RecommendDto recDto) throws Exception{
		return recDao.r_update(recDto);
	}
	
	@Override
	public int r_getSearchResultCnt(SearchCondition sc) throws Exception{
		return recDao.r_searchResultCnt(sc);
	}
	
	@Override
	public int r_getSearchResultRecommendCnt(SearchCondition sc) throws Exception{
		return recDao.r_searchResultRecommendCnt(sc);
	}
	
	@Override
	public List<RecommendDto> r_getSearchResultPage(SearchCondition sc) throws Exception{
		return recDao.r_searchSelectPage(sc);
	}
	
	@Override
	public List<RecommendDto> r_getSearchResultRecommendPage(SearchCondition sc) throws Exception{
		return recDao.r_searchSelectRecommendPage(sc);
	}
	
	@Override
	public RecommendDto r_selectReview(Integer pd_num, String id) throws Exception{
		return recDao.r_selectReview(pd_num, id);
	}
}
