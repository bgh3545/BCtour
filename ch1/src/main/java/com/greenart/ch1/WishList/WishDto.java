package com.greenart.ch1.WishList;

import java.util.Date;
import java.util.Objects;

public class WishDto {
	private Integer pd_wishNum;
	private String id;
	private Integer pd_num;
	private String pd_title;
	private String pd_subtitle;
	private Integer pd_days;
	private Integer pd_price;
	private Integer pd_wishCheck;
	private String pd_img;
	private Integer pd_totalScore;
	private Integer pd_totalScoreMember;
	
	public WishDto(Integer pd_wishNum, String id, Integer pd_num, String pd_title, String pd_subtitle, Integer pd_days,
			Integer pd_price, Integer pd_wishCheck, String pd_img, Integer pd_totalScore, Integer pd_totalScoreMember) {
		super();
		this.pd_wishNum = pd_wishNum;
		this.id = id;
		this.pd_num = pd_num;
		this.pd_title = pd_title;
		this.pd_subtitle = pd_subtitle;
		this.pd_days = pd_days;
		this.pd_price = pd_price;
		this.pd_wishCheck = pd_wishCheck;
		this.pd_img = pd_img;
		this.pd_totalScore = pd_totalScore;
		this.pd_totalScoreMember = pd_totalScoreMember;
	}
	public Integer getPd_totalScore() {
		return pd_totalScore;
	}
	public void setPd_totalScore(Integer pd_totalScore) {
		this.pd_totalScore = pd_totalScore;
	}
	public Integer getPd_totalScoreMember() {
		return pd_totalScoreMember;
	}
	public void setPd_totalScoreMember(Integer pd_totalScoreMember) {
		this.pd_totalScoreMember = pd_totalScoreMember;
	}
	public WishDto(Integer pd_wishNum, String id, Integer pd_num, String pd_title, String pd_subtitle, Integer pd_days,
			Integer pd_price, Integer pd_wishCheck, String pd_img) {
		super();
		this.pd_wishNum = pd_wishNum;
		this.id = id;
		this.pd_num = pd_num;
		this.pd_title = pd_title;
		this.pd_subtitle = pd_subtitle;
		this.pd_days = pd_days;
		this.pd_price = pd_price;
		this.pd_wishCheck = pd_wishCheck;
		this.pd_img = pd_img;
	}
	public String getPd_img() {
		return pd_img;
	}
	public void setPd_img(String pd_img) {
		this.pd_img = pd_img;
	}
	public Integer getPd_wishNum() {
		return pd_wishNum;
	}
	public void setPd_wishNum(Integer pd_wishNum) {
		this.pd_wishNum = pd_wishNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPd_num() {
		return pd_num;
	}
	public void setPd_num(Integer pd_num) {
		this.pd_num = pd_num;
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
	public Integer getPd_days() {
		return pd_days;
	}
	public void setPd_days(Integer pd_days) {
		this.pd_days = pd_days;
	}
	public Integer getPd_price() {
		return pd_price;
	}
	public void setPd_price(Integer pd_price) {
		this.pd_price = pd_price;
	}
	public Integer getPd_wishCheck() {
		return pd_wishCheck;
	}
	public void setPd_wishCheck(Integer pd_wishCheck) {
		this.pd_wishCheck = pd_wishCheck;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, pd_days, pd_num, pd_price, pd_subtitle, pd_title, pd_wishCheck, pd_wishNum);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishDto other = (WishDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(pd_days, other.pd_days)
				&& Objects.equals(pd_num, other.pd_num) && Objects.equals(pd_price, other.pd_price)
				&& Objects.equals(pd_subtitle, other.pd_subtitle) && Objects.equals(pd_title, other.pd_title)
				&& Objects.equals(pd_wishCheck, other.pd_wishCheck) && Objects.equals(pd_wishNum, other.pd_wishNum);
	}
	@Override
	public String toString() {
		return "WishDto [pd_wishNum=" + pd_wishNum + ", id=" + id + ", pd_num=" + pd_num + ", pd_title=" + pd_title
				+ ", pd_subtitle=" + pd_subtitle + ", pd_days=" + pd_days + ", pd_price=" + pd_price + ", pd_wishCheck="
				+ pd_wishCheck + ", pd_img=" + pd_img + ", pd_totalScore=" + pd_totalScore + ", pd_totalScoreMember="
				+ pd_totalScoreMember + "]";
	}
	public WishDto(Integer pd_wishNum, String id, Integer pd_num, String pd_title, String pd_subtitle, Integer pd_days,
			Integer pd_price, Integer pd_wishCheck) {
		super();
		this.pd_wishNum = pd_wishNum;
		this.id = id;
		this.pd_num = pd_num;
		this.pd_title = pd_title;
		this.pd_subtitle = pd_subtitle;
		this.pd_days = pd_days;
		this.pd_price = pd_price;
		this.pd_wishCheck = pd_wishCheck;
	}
	public WishDto() {
		super();
	}
	
	
	
}
