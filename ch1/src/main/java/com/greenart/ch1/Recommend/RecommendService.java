package com.greenart.ch1.Recommend;

import java.util.List;
import java.util.Map;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

public interface RecommendService {

	int r_getCount() throws Exception;

	int r_remove(Integer rec_num, String writer) throws Exception;

	int r_writer(RecommendDto recDto) throws Exception;

	List<RecommendDto> r_getList() throws Exception;

	RecommendDto r_read(Integer rec_num) throws Exception;

	List<RecommendDto> r_getPage(Map map) throws Exception;

	int r_modify(RecommendDto recDto) throws Exception;

	int r_getSearchResultCnt(SearchCondition sc) throws Exception;

	List<RecommendDto> r_getSearchResultPage(SearchCondition sc) throws Exception;

	int r_increaseCommCnt(Integer rec_num) throws Exception;

	int r_decreaseCommCnt(Integer rec_num) throws Exception;

	int r_increaseRecCnt(Integer rec_num) throws Exception;

	int r_decreaseRecCnt(Integer rec_num) throws Exception;

	int r_decreaseRecBool(Integer rec_num, String mem_id) throws Exception;

	int r_increaseRecBool(Integer rec_num, String mem_id) throws Exception;

	RecommendDto rb_read(Integer rec_num, String mem_id) throws Exception;

	int rb_write(RecommendDto recDto) throws Exception;

	List<RecommendDto> r_getSearchResultRecommendPage(SearchCondition sc) throws Exception;

	int r_getSearchResultRecommendCnt(SearchCondition sc) throws Exception;

	List<RecommendDto> r_getTopList() throws Exception;

	RecommendDto r_selectReview(Integer pd_num, String id) throws Exception;
}