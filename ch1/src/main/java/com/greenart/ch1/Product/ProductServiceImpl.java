package com.greenart.ch1.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;
	
	@Override
	public int write(ProductDto productDto) throws Exception{
		return productDao.insert(productDto);		
	}
	
	@Override
	public int increaseBuyCnt(Integer pd_num) throws Exception{
		return productDao.increaseBuyCnt(pd_num);		
	}
	
	@Override
	public int decreaseBuyCnt(Integer pd_num) throws Exception{
		return productDao.decreaseBuyCnt(pd_num);		
	}
}
