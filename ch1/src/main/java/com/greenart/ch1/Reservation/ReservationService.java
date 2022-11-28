package com.greenart.ch1.Reservation;

import java.util.Date;
import java.util.List;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

public interface ReservationService {

	List<ReservationDto> res_selectPage(ProductSearchCondition psc, String mem_id) throws Exception;

	int res_count(String mem_id) throws Exception;

	int res_insert(String mem_id, ReservationDto reservationDto) throws Exception;

	int res_reservation(String mem_id, Integer pd_num) throws Exception;

	int res_deleteRequest(String mem_id, Integer pd_num) throws Exception;

	int res_delete(String mem_id, Integer pd_num) throws Exception;

	ReservationDto res_select(String mem_id, Integer pd_num) throws Exception;

	int res_modify(String mem_id, ReservationDto reservationDto) throws Exception;

	List<ReservationDto> res_reservationSelect(String mem_id, SearchCondition sc) throws Exception;

	List<ReservationDto> res_reservationSelectManage(SearchCondition sc) throws Exception;

	List<ReservationDto> res_reservationRequestManage(SearchCondition sc) throws Exception;

	List<ReservationDto> res_reservationCancleRequestManage(SearchCondition sc) throws Exception;

	int res_countManage() throws Exception;

	int res_countReservationRequest() throws Exception;

	int res_countCancleRequest() throws Exception;

}
