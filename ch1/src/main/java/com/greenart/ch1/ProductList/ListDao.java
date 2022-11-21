package com.greenart.ch1.ProductList;

import java.util.List;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;

public interface ListDao {



	int searchResultCnt(ProductSearchCondition psc,String pd_city) throws Exception;

	List<ListDto> searchSelectPage(ProductSearchCondition psc, String pd_city) throws Exception;

	int insert(ListDto dto) throws Exception;

	int deleteAll(int pd_num);

	String seoulList(String pd_city) throws Exception;



	

	


	//ListDto orderByPrice(int pd_price) throws Exception;

}
