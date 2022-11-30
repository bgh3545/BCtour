package com.greenart.ch1.Product;

import java.util.List;

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
	
	@Override
	public ProductDto pd_reviewSelect(Integer pd_num, String id) throws Exception{
		return productDao.pd_reviewSelect(pd_num, id);
	}
	
	@Override
	public List<ProductDto> pd_buyCntTop() throws Exception{
		return productDao.pd_buyCntTop();
	}
	
	@Override
	public int updateProduct(ProductDto productDto) throws Exception{
		return productDao.pd_updateProduct(productDto);
	}
	
	@Override
	public int pd_scoreInsert(ProductDto productDto, String id) throws Exception{
		return productDao.pd_scoreInsert(productDto, id);
	}
	
	@Override
	public ProductDto pd_scoreSelect(Integer pd_num, String id) throws Exception{
		return productDao.pd_scoreSelect(pd_num, id);
	}
	
	@Override
	public int setScore(Integer pd_num, String id, Integer pd_scorePoint) throws Exception{
		return productDao.setScore(pd_num, id, pd_scorePoint);
	}
	
	@Override
	public int addScore(Integer pd_num, Integer pd_scorePoint) throws Exception{
		return productDao.addScore(pd_num, pd_scorePoint);
	}
	
	@Override
	public int deleteScore(Integer pd_num, Integer pd_scorePoint) throws Exception{
		return productDao.deleteScore(pd_num, pd_scorePoint);
	}
}
