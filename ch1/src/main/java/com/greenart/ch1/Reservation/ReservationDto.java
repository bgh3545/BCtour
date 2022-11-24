package com.greenart.ch1.Reservation;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservationDto {
	private Integer res_num;
	private Integer pd_num;
	private String mem_id;
	private Integer totalMember;
	private Integer totalPrice;
	private Integer state;
	private Date res_reg_date;
	private Date res_update;
	private String	pd_title;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date pd_departDay;
	
	
	public ReservationDto(Integer res_num, Integer pd_num, String mem_id, Integer totalMember, Integer totalPrice,
			Integer state, Date res_reg_date, Date res_update, String pd_title, Date pd_departDay) {
		super();
		this.res_num = res_num;
		this.pd_num = pd_num;
		this.mem_id = mem_id;
		this.totalMember = totalMember;
		this.totalPrice = totalPrice;
		this.state = state;
		this.res_reg_date = res_reg_date;
		this.res_update = res_update;
		this.pd_title = pd_title;
		this.pd_departDay = pd_departDay;
	}
	public String getPd_title() {
		return pd_title;
	}
	public void setPd_title(String pd_title) {
		this.pd_title = pd_title;
	}
	public Date getPd_departDay() {
		return pd_departDay;
	}
	public void setPd_departDay(Date pd_departDay) {
		this.pd_departDay = pd_departDay;
	}
	public Integer getRes_num() {
		return res_num;
	}
	public void setRes_num(Integer res_num) {
		this.res_num = res_num;
	}
	public Integer getPd_num() {
		return pd_num;
	}
	public void setPd_num(Integer pd_num) {
		this.pd_num = pd_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getTotalMember() {
		return totalMember;
	}
	public void setTotalMember(Integer totalMember) {
		this.totalMember = totalMember;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getRes_reg_date() {
		return res_reg_date;
	}
	public void setRes_reg_date(Date res_reg_date) {
		this.res_reg_date = res_reg_date;
	}
	public Date getRes_update() {
		return res_update;
	}
	public void setRes_update(Date res_update) {
		this.res_update = res_update;
	}
	@Override
	public String toString() {
		return "ReservationDto [res_num=" + res_num + ", pd_num=" + pd_num + ", mem_id=" + mem_id + ", totalMember="
				+ totalMember + ", totalPrice=" + totalPrice + ", state=" + state + ", res_reg_date=" + res_reg_date
				+ ", res_update=" + res_update + ", pd_title=" + pd_title + ", pd_departDay=" + pd_departDay + "]";
	}
	public ReservationDto(Integer res_num, Integer pd_num, String mem_id, Integer totalMember, Integer totalPrice,
			Integer state, Date res_reg_date, Date res_update) {
		super();
		this.res_num = res_num;
		this.pd_num = pd_num;
		this.mem_id = mem_id;
		this.totalMember = totalMember;
		this.totalPrice = totalPrice;
		this.state = state;
		this.res_reg_date = res_reg_date;
		this.res_update = res_update;
	}
	public ReservationDto() {
		super();
	}

}



