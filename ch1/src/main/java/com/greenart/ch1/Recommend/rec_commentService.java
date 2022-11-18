package com.greenart.ch1.Recommend;

import java.util.List;
import java.util.Map;

import com.greenart.ch1.PageHandlerAndSearchCondition.CommSearchCondition;

public interface rec_commentService {

	int rm_getCount(Integer rec_num) throws Exception;

	int rm_remove(Integer rec_comm_num, String rec_comm_writer) throws Exception;

	int rm_write(rec_commentDto rec_commDto) throws Exception;

	List<rec_commentDto> rm_getList(Integer rec_num, CommSearchCondition sc) throws Exception;

	rec_commentDto rm_read(Integer rec_comm_num) throws Exception;

	List<rec_commentDto> rm_getPage(Map map) throws Exception;

	int rm_modify(rec_commentDto rec_commDto) throws Exception;

}