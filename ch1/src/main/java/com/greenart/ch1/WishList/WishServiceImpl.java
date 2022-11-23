package com.greenart.ch1.WishList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Service
public class WishServiceImpl implements WishService {
	
	@Autowired
	WishDao wishDao;
	
	@Override
	public int w_getCount(String id) throws Exception{
		return wishDao.w_count(id);
	}
	
	@Override
	public int w_remove(Integer pd_num,String id) throws Exception{
		return wishDao.w_delete(pd_num, id);
	}
	
	@Override
	public int w_addWish(Integer pd_num,String id) throws Exception{
		return wishDao.w_addWish(pd_num, id);
	}
	
	@Override
	public int w_writer(WishDto wishDto) throws Exception {
		return wishDao.w_insert(wishDto);
	}
	
	@Override
	public List<WishDto> w_getList(String id) throws Exception {
		return wishDao.w_selectAll(id);
	}
	
	@Override
	public WishDto w_read(Integer pd_num,String id) throws Exception{
		WishDto wishDto = wishDao.w_select(pd_num,id);
		return wishDto;
	}
	
	@Override
	public List<WishDto> w_getPage(String id, ProductSearchCondition psc) throws Exception{
		return wishDao.w_selectPage(id,psc);
	}
	
	@Override
	public List<WishDto> w_getWishPage(String id, ProductSearchCondition psc) throws Exception{
		return wishDao.w_selectWishPage(id,psc);
	}
}
