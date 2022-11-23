package com.greenart.ch1.WishList;

import java.util.List;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;

public interface WishDao {

	int w_deleteAll(String id);

	int w_delete(Integer pd_num, String id) throws Exception;

	int w_addWish(Integer pd_num, String id) throws Exception;

	int w_insert(WishDto wishDto) throws Exception;

	List<WishDto> w_selectAll(String id) throws Exception;

	WishDto w_select(Integer pd_num, String id) throws Exception;

	int w_count(String id) throws Exception;

	List<WishDto> w_selectPage(String id, ProductSearchCondition sc) throws Exception;

	List<WishDto> w_selectWishPage(String id, ProductSearchCondition sc) throws Exception;

}