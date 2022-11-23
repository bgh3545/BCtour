package com.greenart.ch1.WishList;

import java.util.List;
import java.util.Map;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

public interface WishService {

	int w_getCount(String id) throws Exception;

	int w_remove(Integer pd_num, String id) throws Exception;

	int w_addWish(Integer pd_num, String id) throws Exception;

	int w_writer(WishDto wishDto) throws Exception;

	List<WishDto> w_getList(String id) throws Exception;

	WishDto w_read(Integer pd_num, String id) throws Exception;

	List<WishDto> w_getPage(String id, ProductSearchCondition sc) throws Exception;

	List<WishDto> w_getWishPage(String id, ProductSearchCondition psc) throws Exception;
}