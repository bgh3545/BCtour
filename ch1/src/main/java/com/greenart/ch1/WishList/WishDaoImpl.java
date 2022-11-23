package com.greenart.ch1.WishList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Repository
public class WishDaoImpl implements WishDao{
	@Autowired
	SqlSession session;
	String namespace="com.greenart.ch1.";
	
	@Override
	public int w_deleteAll(String id) {
		return session.delete(namespace+"w_deleteAll",id);
	}
	
	@Override
	public int w_delete(Integer pd_num, String id) throws Exception{
		Map map= new HashMap();
		map.put("pd_num", pd_num);
		map.put("id", id);
		
		return session.delete(namespace+"w_delete",map);
	}
	
	@Override
	public int w_addWish(Integer pd_num, String id) throws Exception{
		Map map= new HashMap();
		map.put("pd_num", pd_num);
		map.put("id", id);
		
		return session.update(namespace+"w_addWish", map);
	}
	
	@Override
	public int w_insert(WishDto wishDto) throws Exception{
		return session.insert(namespace+"w_insert",wishDto);
	}
	
	@Override
	public List<WishDto> w_selectAll(String id) throws Exception{
		return session.selectList(namespace+"w_selectAll",id);
	}
	
	@Override
	public WishDto w_select(Integer pd_num,String id) throws Exception{
		Map map= new HashMap();
		map.put("pd_num", pd_num);
		map.put("id", id);
		return session.selectOne(namespace+"w_select", map);
	}
	
	@Override
	public int w_count(String id) throws Exception{
		return session.selectOne(namespace+"w_count",id);
	}
	
	@Override
	public List<WishDto> w_selectPage(String id, ProductSearchCondition psc) throws Exception{
		Map map= new HashMap();
		map.put("id", id);
		map.put("offset", psc.getOffset());
		map.put("pageSize", psc.getPageSize());
		return session.selectList(namespace+"w_selectPage",map);
	}
	
	@Override
	public List<WishDto> w_selectWishPage(String id, ProductSearchCondition psc) throws Exception{
		Map map= new HashMap();
		map.put("id", id);
		map.put("offset", psc.getOffset());
		map.put("pageSize", psc.getPageSize());
		return session.selectList(namespace+"w_selectWishPage",map);
	}
}
