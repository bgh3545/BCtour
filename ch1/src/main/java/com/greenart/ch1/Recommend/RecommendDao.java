package com.greenart.ch1.Recommend;

import java.util.List;
import java.util.Map;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

public interface RecommendDao {

	int r_deleteAll();

	int r_delete(Integer rec_num, String rec_writer) throws Exception;

	int r_insert(RecommendDto recDto) throws Exception;

	List<RecommendDto> r_selectAll() throws Exception;

	RecommendDto r_select(Integer rec_num) throws Exception;

	int r_increaseViewCnt(Integer rec_num) throws Exception;

	int r_count() throws Exception;

	int r_update(RecommendDto recDto) throws Exception;

	List<RecommendDto> r_selectPage(Map map) throws Exception;

	int r_searchResultCnt(SearchCondition sc) throws Exception;

	List<RecommendDto> r_searchSelectPage(SearchCondition sc) throws Exception;

	int r_increaseCommCnt(Integer rec_num) throws Exception;

	int r_decreaseCommCnt(Integer rec_num) throws Exception;

	int r_increaseRecCnt(Integer rec_num) throws Exception;

	int r_decreaseRecCnt(Integer rec_num) throws Exception;

	int r_increaseRecBool(Integer rec_num, String mem_id) throws Exception;

	int r_decreaseRecBool(Integer rec_num, String mem_id) throws Exception;

	RecommendDto rb_select(Integer rec_num, String mem_id) throws Exception;

	int rb_insert(RecommendDto recDto) throws Exception;

	List<RecommendDto> r_searchSelectRecommendPage(SearchCondition sc) throws Exception;

	int r_searchResultRecommendCnt(SearchCondition sc) throws Exception;

	List<RecommendDto> r_selectTop() throws Exception;

	RecommendDto r_selectReview(Integer pd_num, String id) throws Exception;
}