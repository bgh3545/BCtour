package com.greenart.ch1;

import java.util.List;
import java.util.Map;

public interface CommunityService {

	int c_getCount() throws Exception;

	int c_remove(Integer comm_num, String writer) throws Exception;

	int c_writer(CommunityDto commDto) throws Exception;

	List<CommunityDto> c_getList() throws Exception;

	CommunityDto c_read(Integer comm_num) throws Exception;

	List<CommunityDto> c_getPage(Map map) throws Exception;

	int c_modify(CommunityDto commDto) throws Exception;

	int c_getSearchResultCnt(SearchCondition sc) throws Exception;

	List<CommunityDto> c_getSearchResultPage(SearchCondition sc) throws Exception;
}