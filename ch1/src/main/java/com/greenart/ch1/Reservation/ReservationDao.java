package com.greenart.ch1.Reservation;

import java.util.List;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.Product.ProductDto;
import com.greenart.ch1.WishList.WishDto;

public interface ReservationDao {

	List<ReservationDto> res_selectPage(ProductSearchCondition psc, String mem_id) throws Exception;

	int res_count(String mem_id) throws Exception;

	int res_insert(String mem_id, ReservationDto reservationDto) throws Exception;

	int res_reservation(String mem_id, Integer pd_num) throws Exception;

	int res_deleteRequest(String mem_id, Integer pd_num) throws Exception;

	int res_delete(String mem_id, Integer pd_num) throws Exception;

	ReservationDto res_select(String mem_id, Integer pd_num) throws Exception;

	int res_modify(String mem_id, ReservationDto reservationDto) throws Exception;


}
