package com.greenart.ch1.Recommend;

import java.util.List;
import java.util.Map;

import com.greenart.ch1.PageHandlerAndSearchCondition.CommSearchCondition;

public interface rec_commentDao {

	int rm_deleteAll();

	int rm_delete(Integer rec_comm_num, String rec_comm_writer) throws Exception;

	int rm_insert(rec_commentDto rec_commDto) throws Exception;

	List<rec_commentDto> rm_selectAll(Integer rec_num,CommSearchCondition sc) throws Exception;

	rec_commentDto rm_select(Integer rec_comm_num) throws Exception;

	int rm_count(Integer rec_num) throws Exception;

	int rm_update(rec_commentDto rec_commDto) throws Exception;

	List<rec_commentDto> rm_selectPage(Map map) throws Exception;

}