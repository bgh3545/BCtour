package com.greenart.ch1.Community;

import java.util.List;
import java.util.Map;

public interface comm_commentService {

	int cm_getCount(Integer comm_num) throws Exception;

	int cm_remove(Integer comm_comm_num, String comm_comm_writer) throws Exception;

	int cm_write(comm_commentDto comm_commDto) throws Exception;

	List<comm_commentDto> cm_getList(Integer comm_num) throws Exception;

	comm_commentDto cm_read(Integer comm_comm_num) throws Exception;

	List<comm_commentDto> cm_getPage(Map map) throws Exception;

	int cm_modify(comm_commentDto comm_commDto) throws Exception;

}