package com.greenart.ch1.Product;

import java.util.List;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.WishList.WishDto;

public interface ProductDao {



	int searchResultCnt(ProductSearchCondition psc) throws Exception;

	List<ProductDto> searchSelectPage(ProductSearchCondition psc, String pd_city) throws Exception;

	int insert(ProductDto dto) throws Exception;

	int deleteAll(int pd_num);

	String seoulList(String pd_city) throws Exception;

	List<ProductDto> selectAllPd_num() throws Exception;

	List<WishDto> selectWish(ProductSearchCondition psc, String id, String pd_city) throws Exception;

	ProductDto select(int pd_num) throws Exception;

	int increaseBuyCnt(Integer pd_num) throws Exception;

	int decreaseBuyCnt(Integer pd_num) throws Exception;

	List<WishDto> pd_buyCntSelect(ProductSearchCondition psc, String id, String pd_city) throws Exception;

	List<WishDto> pd_searchSelect(ProductSearchCondition psc, String id) throws Exception;

	int p_citySelectResultCnt(ProductSearchCondition psc, String pd_city) throws Exception;

	ProductDto pd_reviewSelect(int pd_num, String id) throws Exception;

	int pd_updateProduct(ProductDto productDto) throws Exception;

	int pd_scoreInsert(ProductDto productDto, String id) throws Exception;

	ProductDto pd_scoreSelect(Integer pd_num, String id) throws Exception;

	int setScore(Integer pd_num, String id, Integer pd_scorePoint) throws Exception;

	int addScore(Integer pd_num, Integer pd_scorePoint) throws Exception;
	
	int deleteScore(Integer pd_num, Integer pd_scorePoint) throws Exception;

	int p_buyCntResultCnt(ProductSearchCondition psc, String pd_city) throws Exception;

	List<ProductDto> pd_buyCntTop() throws Exception;

}
