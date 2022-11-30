package com.greenart.ch1.Product;

import java.util.List;

public interface ProductService {

	int write(ProductDto listDto) throws Exception;

	int increaseBuyCnt(Integer pd_num) throws Exception;

	int decreaseBuyCnt(Integer pd_num) throws Exception;

	ProductDto pd_reviewSelect(Integer pd_num, String id) throws Exception;

	int updateProduct(ProductDto productDto) throws Exception;

	int pd_scoreInsert(ProductDto productDto, String id) throws Exception;

	ProductDto pd_scoreSelect(Integer pd_num, String id) throws Exception;

	int setScore(Integer pd_num, String id, Integer pd_scorePoint) throws Exception;

	int addScore(Integer pd_num, Integer pd_scorePoint) throws Exception;

	int deleteScore(Integer pd_num, Integer pd_scorePoint) throws Exception;

	List<ProductDto> pd_buyCntTop() throws Exception;

}
