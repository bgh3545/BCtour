package com.greenart.ch1.Product;

public interface ProductService {

	int write(ProductDto listDto) throws Exception;

	int increaseBuyCnt(Integer pd_num) throws Exception;

	int decreaseBuyCnt(Integer pd_num) throws Exception;

}
