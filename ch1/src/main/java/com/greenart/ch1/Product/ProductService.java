package com.greenart.ch1.Product;

public interface ProductService {

	int write(ProductDto listDto) throws Exception;

	int increaseBuyCnt(Integer pd_num) throws Exception;

	int decreaseBuyCnt(Integer pd_num) throws Exception;

	ProductDto pd_reviewSelect(Integer pd_num, String id) throws Exception;

	int updateProduct(ProductDto productDto) throws Exception;

	int pd_scoreInsert(ProductDto productDto, String id) throws Exception;

	ProductDto pd_scoreSelect(Integer pd_num, String id) throws Exception;

}
