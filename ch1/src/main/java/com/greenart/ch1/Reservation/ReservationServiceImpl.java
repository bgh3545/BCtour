package com.greenart.ch1.Reservation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;
	
	@Override
	public List<ReservationDto> res_selectPage(ProductSearchCondition psc, String mem_id) throws Exception{
		return reservationDao.res_selectPage(psc, mem_id);
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
	
	@Override
	public List<ReservationDto> res_reservationSelect(String mem_id, SearchCondition sc) throws Exception{
		return reservationDao.res_reservationSelect(mem_id, sc);
	}
	
	@Override
	public List<ReservationDto> res_reservationSelectManage(SearchCondition sc) throws Exception{
		return reservationDao.res_reservationSelectManage(sc);
	}
	
	@Override
	public List<ReservationDto> res_reservationRequestManage(SearchCondition sc) throws Exception{
		return reservationDao.res_reservationRequestManage(sc);
	}
	
	@Override
	public List<ReservationDto> res_reservationCancleRequestManage(SearchCondition sc) throws Exception{
		return reservationDao.res_reservationCancleRequestManage(sc);
	}
	
	@Override
	public int res_count(String mem_id) throws Exception{
		return reservationDao.res_count(mem_id);
	}
	
	@Override
	public int res_countManage() throws Exception{
		return reservationDao.res_countManage();
	}
	
	@Override
	public int res_countReservationRequest() throws Exception{
		return reservationDao.res_countReservationRequest();
	}
	
	@Override
	public int res_countCancleRequest() throws Exception{
		return reservationDao.res_countCancleRequest();
	}
	
}
