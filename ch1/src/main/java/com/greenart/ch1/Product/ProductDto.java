package com.greenart.ch1.Product;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ProductDto {
	private int pd_num;
	private String pd_city;
	private String	pd_img;
	private String	pd_title;
	private String pd_subtitle;
	private int pd_days;
	private int	pd_price;
	private String pd_transport;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date pd_departDay;
	private String pd_departStart;
	private String pd_departEnd;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date pd_deportDay;
	private String pd_deportStart;
	private String pd_deportEnd;
	private String pd_visitCity;
	private Integer pd_score;
	private Integer pd_scoreMember;
	
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getPd_city() {
		return pd_city;
	}
	public void setPd_city(String pd_city) {
		this.pd_city = pd_city;
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
	public String getPd_subtitle() {
		return pd_subtitle;
	}
	public void setPd_subtitle(String pd_subtitle) {
		this.pd_subtitle = pd_subtitle;
	}
	public int getPd_days() {
		return pd_days;
	}
	public void setPd_days(int pd_days) {
		this.pd_days = pd_days;
	}
	public int getPd_price() {
		return pd_price;
	}
	public void setPd_price(int pd_price) {
		this.pd_price = pd_price;
	}
	public String getPd_transport() {
		return pd_transport;
	}
	public void setPd_transport(String pd_transport) {
		this.pd_transport = pd_transport;
	}
	public Date getPd_departDay() {
		return pd_departDay;
	}
	public void setPd_departDay(Date pd_departDay) {
		this.pd_departDay = pd_departDay;
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
	public Date getPd_deportDay() {
		return pd_deportDay;
	}
	public void setPd_deportDay(Date pd_deportDay) {
		this.pd_deportDay = pd_deportDay;
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
	public String getPd_visitCity() {
		return pd_visitCity;
	}
	public void setPd_visitCity(String pd_visitCity) {
		this.pd_visitCity = pd_visitCity;
	}
	public Integer getPd_score() {
		return pd_score;
	}
	public void setPd_score(Integer pd_score) {
		this.pd_score = pd_score;
	}
	public Integer getPd_scoreMember() {
		return pd_scoreMember;
	}
	public void setPd_scoreMember(Integer pd_scoreMember) {
		this.pd_scoreMember = pd_scoreMember;
	}
	
	@Override
	public String toString() {
		return "ProductDto [pd_num=" + pd_num + ", pd_city=" + pd_city + ", pd_img=" + pd_img + ", pd_title=" + pd_title
				+ ", pd_subtitle=" + pd_subtitle + ", pd_days=" + pd_days + ", pd_price=" + pd_price + ", pd_transport="
				+ pd_transport + ", pd_departDay=" + pd_departDay + ", pd_departStart=" + pd_departStart
				+ ", pd_departEnd=" + pd_departEnd + ", pd_deportDay=" + pd_deportDay + ", pd_deportStart="
				+ pd_deportStart + ", pd_deportEnd=" + pd_deportEnd + ", pd_visitCity=" + pd_visitCity + ", pd_score="
				+ pd_score + ", pd_scoreMember=" + pd_scoreMember + "]";
	}
	
	public ProductDto(int pd_num, String pd_city, String pd_img, String pd_title, String pd_subtitle, int pd_days,
			int pd_price, String pd_transport, Date pd_departDay, String pd_departStart, String pd_departEnd,
			Date pd_deportDay, String pd_deportStart, String pd_deportEnd, String pd_visitCity, Integer pd_score,
			Integer pd_scoreMember) {
		super();
		this.pd_num = pd_num;
		this.pd_city = pd_city;
		this.pd_img = pd_img;
		this.pd_title = pd_title;
		this.pd_subtitle = pd_subtitle;
		this.pd_days = pd_days;
		this.pd_price = pd_price;
		this.pd_transport = pd_transport;
		this.pd_departDay = pd_departDay;
		this.pd_departStart = pd_departStart;
		this.pd_departEnd = pd_departEnd;
		this.pd_deportDay = pd_deportDay;
		this.pd_deportStart = pd_deportStart;
		this.pd_deportEnd = pd_deportEnd;
		this.pd_visitCity = pd_visitCity;
		this.pd_score = pd_score;
		this.pd_scoreMember = pd_scoreMember;
	}
	
	public ProductDto() {
		super();
	}
	
}



