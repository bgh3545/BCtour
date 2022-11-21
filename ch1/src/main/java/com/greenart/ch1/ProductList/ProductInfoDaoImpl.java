package com.greenart.ch1.ProductList;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductInfoDaoImpl implements ProductInfoDao {
	@Autowired
	SqlSession session;
	
	String namespace="com.greenart.ch1.";
			
	@Override
	public List<ProductInfoDto> selectAll() throws Exception{
		return session.selectList(namespace+"productInfoSelectAll");
	}
	
	@Override
	public ProductInfoDto select(int pd_num) throws Exception{
		return session.selectOne(namespace+"productInfoSelect",pd_num);
		
	}
}
