package com.greenart.ch1.ProductList;

import java.util.Date;

public class ListDto {
	private int pd_num; 			//상품 번호
	private String pd_city;			//관광 도시
	private String	pd_img; 		//지역이미지
	private String	pd_title;		//지역이름
	private int	pd_price;			//관광 가격
	private String pd_subtitle;		//지역이름2
	private String pd_text;			//설명글
	private int pd_days;			//여행일수
	private int pd_viewcnt;			//상품 본 횟수
	private int wishlist;			//찜
	private String pd_totalDays;	//총 날짜 
	private String pd_transport;	//이동수단
	private String pd_departStart;	//출발 시간
	private String pd_departEnd;	//도착 시간
	private String pd_deportStart;	//나갈 시간 
	private String pd_deportEnd;	//도착 시간
	private String pd_city1;		// 
	private String pd_city2;
	private String pd_city3;
	private String pd_reserveUntil;
	public ListDto(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ListDto [pd_num=" + pd_num + ", pd_city=" + pd_city + ", pd_img=" + pd_img + ", pd_title=" + pd_title
				+ ", pd_price=" + pd_price + ", pd_subtitle=" + pd_subtitle + ", pd_text=" + pd_text + ", pd_days="
				+ pd_days + ", pd_viewcnt=" + pd_viewcnt + ", wishlist=" + wishlist + ", pd_totalDays=" + pd_totalDays
				+ ", pd_transport=" + pd_transport + ", pd_departStart=" + pd_departStart + ", pd_departEnd="
				+ pd_departEnd + ", pd_deportStart=" + pd_deportStart + ", pd_deportEnd=" + pd_deportEnd + ", pd_city1="
				+ pd_city1 + ", pd_city2=" + pd_city2 + ", pd_city3=" + pd_city3 + ", pd_reserveUntil="
				+ pd_reserveUntil + "]";
	}
	public String getPd_img() {
		return pd_img;
	}
	public void setPd_img(String pd_img) {
		this.pd_img = pd_img;
	}
	public String getPd_title() {
		return pd_title;
	}
	public void setPd_title(String pd_title) {
		this.pd_title = pd_title;
	}
	public int getPd_price() {
		return pd_price;
	}
	public void setPd_price(int pd_price) {
		this.pd_price = pd_price;
	}
	public String getPd_subtitle() {
		return pd_subtitle;
	}
	public void setPd_subtitle(String pd_subtitle) {
		this.pd_subtitle = pd_subtitle;
	}
	public String getPd_text() {
		return pd_text;
	}
	public void setPd_text(String pd_text) {
		this.pd_text = pd_text;
	}
	public int getPd_days() {
		return pd_days;
	}
	public void setPd_days(int pd_days) {
		this.pd_days = pd_days;
	}
	public int getPd_viewcnt() {
		return pd_viewcnt;
	}
	public void setPd_viewcnt(int pd_viewcnt) {
		this.pd_viewcnt = pd_viewcnt;
	}
	public int getWishlist() {
		return wishlist;
	}
	public void setWishlist(int wishlist) {
		this.wishlist = wishlist;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getPd_totalDays() {
		return pd_totalDays;
	}
	public void setPd_totalDays(String pd_totalDays) {
		this.pd_totalDays = pd_totalDays;
	}
	public String getPd_transport() {
		return pd_transport;
	}
	public void setPd_transport(String pd_transport) {
		this.pd_transport = pd_transport;
	}
	public String getPd_departStart() {
		return pd_departStart;
	}
	public void setPd_departStart(String pd_departStart) {
		this.pd_departStart = pd_departStart;
	}
	public String getPd_departEnd() {
		return pd_departEnd;
	}
	public void setPd_departEnd(String pd_departEnd) {
		this.pd_departEnd = pd_departEnd;
	}
	public String getPd_deportStart() {
		return pd_deportStart;
	}
	public void setPd_deportStart(String pd_deportStart) {
		this.pd_deportStart = pd_deportStart;
	}
	public String getPd_deportEnd() {
		return pd_deportEnd;
	}
	public void setPd_deportEnd(String pd_deportEnd) {
		this.pd_deportEnd = pd_deportEnd;
	}
	public String getPd_city1() {
		return pd_city1;
	}
	public void setPd_city1(String pd_city1) {
		this.pd_city1 = pd_city1;
	}
	public String getPd_city2() {
		return pd_city2;
	}
	public void setPd_city2(String pd_city2) {
		this.pd_city2 = pd_city2;
	}
	public String getPd_city3() {
		return pd_city3;
	}
	public void setPd_city3(String pd_city3) {
		this.pd_city3 = pd_city3;
	}
	public String getPd_reserveUntil() {
		return pd_reserveUntil;
	}
	public void setPd_reserveUntil(String pd_reserveUntil) {
		this.pd_reserveUntil = pd_reserveUntil;
	}
	public String getPd_city() {
		return pd_city;
	}
	public void setPd_city(String pd_city) {
		this.pd_city = pd_city;
	}


}



