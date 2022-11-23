package com.greenart.ch1.Community;

import java.util.Date;
import java.util.Objects;

public class comm_commentDto {
	private Integer comm_num;
	private String comm_comm_num;
	private String comm_comm_content;
	private String comm_comm_modicontent;
	private String comm_comm_writer;
	private Date comm_comm_date;
	private Date comm_comm_up;
	
	
	
	public comm_commentDto(Integer comm_num, String comm_comm_num, String comm_comm_content,
			String comm_comm_modicontent, String comm_comm_writer, Date comm_comm_date, Date comm_comm_up) {
		super();
		this.comm_num = comm_num;
		this.comm_comm_num = comm_comm_num;
		this.comm_comm_content = comm_comm_content;
		this.comm_comm_modicontent = comm_comm_modicontent;
		this.comm_comm_writer = comm_comm_writer;
		this.comm_comm_date = comm_comm_date;
		this.comm_comm_up = comm_comm_up;
	}
	public String getComm_comm_modicontent() {
		return comm_comm_modicontent;
	}
	public void setComm_comm_modicontent(String comm_comm_modicontent) {
		this.comm_comm_modicontent = comm_comm_modicontent;
	}
	public Integer getComm_num() {
		return comm_num;
	}
	public void setComm_num(Integer comm_num) {
		this.comm_num = comm_num;
	}
	public String getComm_comm_num() {
		return comm_comm_num;
	}
	public void setComm_comm_num(String comm_comm_num) {
		this.comm_comm_num = comm_comm_num;
	}
	public String getComm_comm_content() {
		return comm_comm_content;
	}
	public void setComm_comm_content(String comm_comm_content) {
		this.comm_comm_content = comm_comm_content;
	}
	public String getComm_comm_writer() {
		return comm_comm_writer;
	}
	public void setComm_comm_writer(String comm_comm_writer) {
		this.comm_comm_writer = comm_comm_writer;
	}
	public Date getComm_comm_date() {
		return comm_comm_date;
	}
	public void setComm_comm_date(Date comm_comm_date) {
		this.comm_comm_date = comm_comm_date;
	}
	public Date getComm_comm_up() {
		return comm_comm_up;
	}
	public void setComm_comm_up(Date comm_comm_up) {
		this.comm_comm_up = comm_comm_up;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(comm_comm_content, comm_comm_date, comm_comm_modicontent, comm_comm_num, comm_comm_up,
				comm_comm_writer, comm_num);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		comm_commentDto other = (comm_commentDto) obj;
		return Objects.equals(comm_comm_content, other.comm_comm_content)
				&& Objects.equals(comm_comm_date, other.comm_comm_date)
				&& Objects.equals(comm_comm_modicontent, other.comm_comm_modicontent)
				&& Objects.equals(comm_comm_num, other.comm_comm_num)
				&& Objects.equals(comm_comm_up, other.comm_comm_up)
				&& Objects.equals(comm_comm_writer, other.comm_comm_writer) && Objects.equals(comm_num, other.comm_num);
	}
	
	@Override
	public String toString() {
		return "comm_commentDto [comm_num=" + comm_num + ", comm_comm_num=" + comm_comm_num + ", comm_comm_content="
				+ comm_comm_content + ", comm_comm_modicontent=" + comm_comm_modicontent + ", comm_comm_writer="
				+ comm_comm_writer + ", comm_comm_date=" + comm_comm_date + ", comm_comm_up=" + comm_comm_up + "]";
	}
	
	public int getModicomment(Integer comm_num, String comm_comm_num, String comm_comm_modicontent, String comm_comm_writer) {
		return 1;
	}
	
	public comm_commentDto() {
		super();
	}
	
}
