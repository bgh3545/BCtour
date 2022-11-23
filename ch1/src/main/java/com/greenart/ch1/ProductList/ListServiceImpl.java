package com.greenart.ch1.ProductList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListServiceImpl implements ListService {
	@Autowired
	ListDao listDao;
	
	@Override
	public int write(ListDto listDto) throws Exception{
		return listDao.insert(listDto);
		
	}
}
