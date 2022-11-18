package com.greenart.ch1.Recommend;

import java.util.Date;
import java.util.Objects;

public class rec_commentDto {
	private Integer rec_num;
	private String rec_comm_num;
	private String rec_comm_content;
	private String rec_comm_modicontent;
	private String rec_comm_writer;
	private Date rec_comm_date;
	private Date rec_comm_up;
	public Integer getRec_num() {
		return rec_num;
	}
	public void setRec_num(Integer rec_num) {
		this.rec_num = rec_num;
	}
	public String getRec_comm_num() {
		return rec_comm_num;
	}
	public void setRec_comm_num(String rec_comm_num) {
		this.rec_comm_num = rec_comm_num;
	}
	public String getRec_comm_content() {
		return rec_comm_content;
	}
	public void setRec_comm_content(String rec_comm_content) {
		this.rec_comm_content = rec_comm_content;
	}
	public String getRec_comm_modicontent() {
		return rec_comm_modicontent;
	}
	public void setRec_comm_modicontent(String rec_comm_modicontent) {
		this.rec_comm_modicontent = rec_comm_modicontent;
	}
	public String getRec_comm_writer() {
		return rec_comm_writer;
	}
	public void setRec_comm_writer(String rec_comm_writer) {
		this.rec_comm_writer = rec_comm_writer;
	}
	public Date getRec_comm_date() {
		return rec_comm_date;
	}
	public void setRec_comm_date(Date rec_comm_date) {
		this.rec_comm_date = rec_comm_date;
	}
	public Date getRec_comm_up() {
		return rec_comm_up;
	}
	public void setRec_comm_up(Date rec_comm_up) {
		this.rec_comm_up = rec_comm_up;
	}
	@Override
	public int hashCode() {
		return Objects.hash(rec_comm_content, rec_comm_date, rec_comm_modicontent, rec_comm_num, rec_comm_up,
				rec_comm_writer, rec_num);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		rec_commentDto other = (rec_commentDto) obj;
		return Objects.equals(rec_comm_content, other.rec_comm_content)
				&& Objects.equals(rec_comm_date, other.rec_comm_date)
				&& Objects.equals(rec_comm_modicontent, other.rec_comm_modicontent)
				&& Objects.equals(rec_comm_num, other.rec_comm_num) && Objects.equals(rec_comm_up, other.rec_comm_up)
				&& Objects.equals(rec_comm_writer, other.rec_comm_writer) && Objects.equals(rec_num, other.rec_num);
	}
	@Override
	public String toString() {
		return "rec_commentDto [rec_num=" + rec_num + ", rec_comm_num=" + rec_comm_num + ", rec_comm_content="
				+ rec_comm_content + ", rec_comm_modicontent=" + rec_comm_modicontent + ", rec_comm_writer="
				+ rec_comm_writer + ", rec_comm_date=" + rec_comm_date + ", rec_comm_up=" + rec_comm_up + "]";
	}
	public rec_commentDto(Integer rec_num, String rec_comm_num, String rec_comm_content, String rec_comm_modicontent,
			String rec_comm_writer, Date rec_comm_date, Date rec_comm_up) {
		super();
		this.rec_num = rec_num;
		this.rec_comm_num = rec_comm_num;
		this.rec_comm_content = rec_comm_content;
		this.rec_comm_modicontent = rec_comm_modicontent;
		this.rec_comm_writer = rec_comm_writer;
		this.rec_comm_date = rec_comm_date;
		this.rec_comm_up = rec_comm_up;
	}
	public rec_commentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
