package com.greenart.ch1.Reservation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Repository
public class ReservationDaoImpl implements ReservationDao      {
	@Autowired
	SqlSession session;

	String namespace = "com.greenart.ch1.";
	
	@Override
	public List<ReservationDto> res_selectPage(ProductSearchCondition psc, String mem_id) throws Exception{
		Map map = new HashMap();
		map.put("mem_id", mem_id);
		map.put("offset", psc.getOffset());
		map.put("pageSize",psc.getPageSize());
		
		return session.selectList(namespace + "res_selectPage",map);
	}
	
	@Override
	public ReservationDto res_select(String mem_id, Integer pd_num) throws Exception{
		Map map = new HashMap();
		map.put("mem_id", mem_id);
		map.put("pd_num", pd_num);
		
		return session.selectOne(namespace+"res_select", map);
	}
	
	@Override
	public int res_insert(String mem_id, ReservationDto reservationDto) throws Exception{
		Map map = new HashMap();
		map.put("mem_id", mem_id);
		map.put("pd_num", reservationDto.getPd_num());
		map.put("totalMember", reservationDto.getTotalMember());
		map.put("totalPrice", reservationDto.getTotalPrice());
		map.put("pd_departDay", reservationDto.getPd_departDay());
		
		return session.insert(namespace + "res_insert",map);
	}
	
	@Override
	public int res_modify(String mem_id, ReservationDto reservationDto) throws Exception{
		Map map = new HashMap();
		map.put("mem_id", mem_id);
		map.put("pd_num", reservationDto.getPd_num());
		map.put("totalMember", reservationDto.getTotalMember());
		map.put("totalPrice", reservationDto.getTotalPrice());
		
		return session.update(namespace+"res_modify", map);
	}
	
	@Override
	public int res_reservation(String mem_id, Integer pd_num) throws Exception{
		Map map = new HashMap();
		map.put("mem_id", mem_id);
		map.put("pd_num", pd_num);
		
		return session.update(namespace+"res_reservation", map);
	}
	
	@Override
	public int res_deleteRequest(String mem_id, Integer pd_num) throws Exception{
		Map map = new HashMap();
		map.put("mem_id", mem_id);
		map.put("pd_num", pd_num);
		
		return session.update(namespace+"res_deleteRequest", map);
	}
	
	@Override
	public int res_delete(String mem_id, Integer pd_num) throws Exception{
		Map map = new HashMap();
		map.put("mem_id", mem_id);
		map.put("pd_num", pd_num);
		
		return session.update(namespace+"res_delete", map);
	}
	
	@Override
	public List<ReservationDto> res_reservationSelect(String mem_id, SearchCondition sc) throws Exception{
		Map map = new HashMap();
		map.put("mem_id", mem_id);
		map.put("offset", sc.getOffset());
		map.put("pageSize",sc.getPageSize());
		return session.selectList(namespace+"res_reservationSelect",map);
	}
	
	@Override
	public List<ReservationDto> res_reservationSelectManage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"res_reservationSelectManage", sc);
	}
	
	@Override
	public List<ReservationDto> res_reservationRequestManage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"res_reservationRequestManage", sc);
	}
	
	@Override
	public List<ReservationDto> res_reservationCancleRequestManage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"res_reservationCancleRequestManage", sc);
	}
	
	@Override
	public int res_count(String mem_id) throws Exception{
		return session.selectOne(namespace + "res_count",mem_id);
	}
	
	@Override
	public int res_countManage() throws Exception{
		return session.selectOne(namespace + "res_countManage");
	}
	
	@Override
	public int res_countReservationRequest() throws Exception{
		return session.selectOne(namespace + "res_countReservationRequest");
	}
	
	@Override
	public int res_countCancleRequest() throws Exception{
		return session.selectOne(namespace + "res_countCancleRequest");
	}
	
}
