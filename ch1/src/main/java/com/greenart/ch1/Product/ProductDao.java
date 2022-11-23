package com.greenart.ch1.Product;

import java.util.List;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.WishList.WishDto;

public interface ProductDao {



	int searchResultCnt(ProductSearchCondition psc,String pd_city) throws Exception;

	List<ProductDto> searchSelectPage(ProductSearchCondition psc, String pd_city) throws Exception;

	int insert(ProductDto dto) throws Exception;

	int deleteAll(int pd_num);

	String seoulList(String pd_city) throws Exception;

	List<ProductDto> selectAllPd_num() throws Exception;

	List<WishDto> selectWish(ProductSearchCondition psc, String id, String pd_city) throws Exception;

	ProductDto select(int pd_num) throws Exception;



	

	


	//ListDto orderByPrice(int pd_price) throws Exception;

}
