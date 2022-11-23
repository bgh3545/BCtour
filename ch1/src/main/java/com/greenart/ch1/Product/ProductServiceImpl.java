package com.greenart.ch1.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao listDao;
	
	@Override
	public int write(ProductDto listDto) throws Exception{
		return listDao.insert(listDto);
		
	}
}
