package com.greenart.ch1.Community;

import java.util.List;
import java.util.Map;

public interface comm_commentDao {

	int cm_deleteAll();

	int cm_delete(Integer comm_comm_num, String comm_comm_writer) throws Exception;

	int cm_insert(comm_commentDto comm_commDto) throws Exception;

	List<comm_commentDto> cm_selectAll(Integer comm_num) throws Exception;

	comm_commentDto cm_select(Integer comm_comm_num) throws Exception;

	int cm_count(Integer comm_num) throws Exception;

	int cm_update(comm_commentDto comm_commDto) throws Exception;

	List<comm_commentDto> cm_selectPage(Map map) throws Exception;

}