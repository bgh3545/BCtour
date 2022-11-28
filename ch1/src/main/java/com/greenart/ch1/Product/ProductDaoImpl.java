package com.greenart.ch1.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.WishList.WishDto;

@Repository
public class ProductDaoImpl implements ProductDao      {
	@Autowired
	SqlSession session;

	String namespace = "com.greenart.ch1.";

	   @Override
	   public List<ProductDto> searchSelectPage(ProductSearchCondition psc,String pd_city) throws Exception {
		  Map map = new HashMap();
		  map.put("pd_city",pd_city);
		  map.put("offset",psc.getOffset());
		  map.put("pageSize", psc.getPageSize());
		  map.put("keyword", psc.getKeyword());
		  map.put("option", psc.getOption());
	      return session.selectList(namespace + "p_searchSelectPage",map);
	   }
	   
	   @Override
	   public int searchResultCnt(ProductSearchCondition psc) throws Exception {
		   Map map = new HashMap();
		   map.put("keyword", psc.getKeyword());
		   map.put("option", psc.getOption());
	      return session.selectOne(namespace + "p_searchResultCnt",psc);
	   }
	   
	   @Override
	   public int p_citySelectResultCnt(ProductSearchCondition psc, String pd_city) throws Exception {
		   Map map = new HashMap();
		   map.put("pd_city",pd_city);
		   map.put("keyword", psc.getKeyword());
		   map.put("option", psc.getOption());
	      return session.selectOne(namespace + "p_citySelectResultCnt",map);
	   }
	   
	   @Override
	   public int insert(ProductDto dto) throws Exception{
		   return session.insert(namespace+"insertInfo",dto);
	   }
	   
	   @Override
	   public int deleteAll(int pd_num) {
			return session.delete(namespace+"p_deleteAll",pd_num);
	   }
	   
	  @Override
	   public String seoulList(String pd_city) throws Exception{
		   return session.selectOne(namespace+"seoulList",pd_city);
	   }
	  
	  @Override
	   public List<ProductDto> selectAllPd_num() throws Exception{
		   return session.selectList(namespace+"pd_numSelect");
	  }
		
	  @Override
	   public int increaseBuyCnt(Integer pd_num) throws Exception{
		   return session.update(namespace+"increaseBuyCnt", pd_num);
	  }
	  
	  @Override
	   public int decreaseBuyCnt(Integer pd_num) throws Exception{
		   return session.update(namespace+"decreaseBuyCnt", pd_num);
	  }
	  
	  @Override
	   public List<WishDto> selectWish(ProductSearchCondition psc, String id, String pd_city) throws Exception{
		   Map map = new HashMap();
		   map.put("id", id);
		   map.put("offset", psc.getOffset());
		   map.put("pageSize", psc.getPageSize());
		   map.put("pd_city", pd_city);
		   map.put("keyword", psc.getKeyword());
		   return session.selectList(namespace+"pd_wishSelect",map);	
		}
	  
	  @Override
	   public List<WishDto> pd_buyCntSelect(ProductSearchCondition psc, String id, String pd_city) throws Exception{
		   Map map = new HashMap();
		   map.put("id", id);
		   map.put("offset", psc.getOffset());
		   map.put("pageSize", psc.getPageSize());
		   map.put("pd_city", pd_city);
		   map.put("keyword", psc.getKeyword());
		   return session.selectList(namespace+"pd_buyCntSelect",map);	
		}
	  
	  @Override
	   public List<WishDto> pd_searchSelect(ProductSearchCondition psc, String id) throws Exception{
		   Map map = new HashMap();
		   map.put("id", id);
		   map.put("offset", psc.getOffset());
		   map.put("pageSize", psc.getPageSize());
		   map.put("keyword", psc.getKeyword());
		   return session.selectList(namespace+"pd_searchSelect",map);	
		}
	  
	  @Override
	   public ProductDto select(int pd_num) throws Exception{
	   return session.selectOne(namespace+"productInfoSelect",pd_num);	
		}
}
