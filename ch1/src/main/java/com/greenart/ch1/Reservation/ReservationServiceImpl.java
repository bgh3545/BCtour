package com.greenart.ch1.Reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.Product.ProductDto;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;
	
	@Override
	public List<ReservationDto> res_selectPage(ProductSearchCondition psc, String mem_id) throws Exception{
		return reservationDao.res_selectPage(psc, mem_id);
	}
	
	@Override
	public int res_count(String mem_id) throws Exception{
		return reservationDao.res_count(mem_id);
	}
	
	@Override
	public ReservationDto res_select(String mem_id, Integer pd_num) throws Exception{
		return reservationDao.res_select(mem_id, pd_num);
	}
	
	@Override
	public int res_insert(String mem_id, ReservationDto reservationDto) throws Exception{
		return reservationDao.res_insert(mem_id, reservationDto);
	}
	
	@Override
	public int res_modify(String mem_id, ReservationDto reservationDto) throws Exception{
		return reservationDao.res_modify(mem_id, reservationDto);
	}
	
	@Override
	public int res_reservation(String mem_id, Integer pd_num) throws Exception{
		return reservationDao.res_reservation(mem_id, pd_num);
	}
	
	@Override
	public int res_deleteRequest(String mem_id, Integer pd_num) throws Exception{
		return reservationDao.res_deleteRequest(mem_id, pd_num);
	}
	
	@Override
	public int res_delete(String mem_id, Integer pd_num) throws Exception{
		return reservationDao.res_delete(mem_id, pd_num);
	}
	
}
