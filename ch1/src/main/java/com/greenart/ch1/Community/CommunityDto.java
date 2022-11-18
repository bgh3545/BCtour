package com.greenart.ch1.Community;

import java.util.Date;
import java.util.Objects;

public class CommunityDto {
	private Integer comm_num;
	private String comm_title;
	private String comm_content;
	private String comm_writer;
	private int comm_view;
	private int comm_comment;
	private Date comm_reg_date;
	private Date comm_up_date;
	
	public Integer getComm_num() {
		return comm_num;
	}
	public void setComm_num(Integer comm_num) {
		this.comm_num = comm_num;
	}
	public String getComm_title() {
		return comm_title;
	}
	public void setComm_title(String comm_title) {
		this.comm_title = comm_title;
	}
	public String getComm_content() {
		return comm_content;
	}
	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	public String getComm_writer() {
		return comm_writer;
	}
	public void setComm_writer(String comm_writer) {
		this.comm_writer = comm_writer;
	}
	public int getComm_view() {
		return comm_view;
	}
	public void setComm_view(int comm_view) {
		this.comm_view = comm_view;
	}
	public int getComm_comment() {
		return comm_comment;
	}
	public void setComm_comment(int comm_comment) {
		this.comm_comment = comm_comment;
	}
	public Date getComm_reg_date() {
		return comm_reg_date;
	}
	public void setComm_reg_date(Date comm_reg_date) {
		this.comm_reg_date = comm_reg_date;
	}
	public Date getComm_up_date() {
		return comm_up_date;
	}
	public void setComm_up_date(Date comm_up_date) {
		this.comm_up_date = comm_up_date;
	}
	@Override
	public int hashCode() {
		return Objects.hash(comm_comment, comm_content, comm_num, comm_reg_date, comm_title, comm_up_date, comm_view,
				comm_writer);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommunityDto other = (CommunityDto) obj;
		return comm_comment == other.comm_comment && Objects.equals(comm_content, other.comm_content)
				&& Objects.equals(comm_num, other.comm_num) && Objects.equals(comm_reg_date, other.comm_reg_date)
				&& Objects.equals(comm_title, other.comm_title) && Objects.equals(comm_up_date, other.comm_up_date)
				&& comm_view == other.comm_view && Objects.equals(comm_writer, other.comm_writer);
	}
	@Override
	public String toString() {
		return "CommunityDto [comm_num=" + comm_num + ", comm_title=" + comm_title + ", comm_content=" + comm_content
				+ ", comm_writer=" + comm_writer + ", comm_view=" + comm_view + ", comm_comment=" + comm_comment
				+ ", comm_reg_date=" + comm_reg_date + ", comm_up_date=" + comm_up_date + "]";
	}
	public CommunityDto(Integer comm_num, String comm_title, String comm_content, String comm_writer, int comm_view,
			int comm_comment, Date comm_reg_date, Date comm_up_date) {
		super();
		this.comm_num = comm_num;
		this.comm_title = comm_title;
		this.comm_content = comm_content;
		this.comm_writer = comm_writer;
		this.comm_view = comm_view;
		this.comm_comment = comm_comment;
		this.comm_reg_date = comm_reg_date;
		this.comm_up_date = comm_up_date;
	}
	
	
	
	public CommunityDto(String comm_title, String comm_content, String comm_writer) {
		super();
		this.comm_title = comm_title;
		this.comm_content = comm_content;
		this.comm_writer = comm_writer;
	}
	public CommunityDto() {
		super();
	}
	
}
