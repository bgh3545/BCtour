package com.greenart.ch1.ProductList;

import java.util.List;

public interface ProductInfoDao {

	List<ProductInfoDto> selectAll() throws Exception;

	ProductInfoDto select(int pd_num) throws Exception;

}