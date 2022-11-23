package com.greenart.ch1.Recommend;

import java.util.Date;
import java.util.Objects;

public class RecommendDto {
	private Integer rec_num;
	private String rec_title;
	private String rec_content;
	private String rec_writer;
	private String mem_id;
	private int rec_view;
	private int rec_comment;
	private int rec_recommend;
	private Date rec_reg_date;
	private Date rec_up_date;
	private Integer recbool;
	
	public Integer getRec_num() {
		return rec_num;
	}
	public void setRec_num(Integer rec_num) {
		this.rec_num = rec_num;
	}
	public String getRec_title() {
		return rec_title;
	}
	public void setRec_title(String rec_title) {
		this.rec_title = rec_title;
	}
	public String getRec_content() {
		return rec_content;
	}
	public void setRec_content(String rec_content) {
		this.rec_content = rec_content;
	}
	public String getRec_writer() {
		return rec_writer;
	}
	public void setRec_writer(String rec_writer) {
		this.rec_writer = rec_writer;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getRec_view() {
		return rec_view;
	}
	public void setRec_view(int rec_view) {
		this.rec_view = rec_view;
	}
	public int getRec_comment() {
		return rec_comment;
	}
	public void setRec_comment(int rec_comment) {
		this.rec_comment = rec_comment;
	}
	public int getRec_recommend() {
		return rec_recommend;
	}
	public void setRec_recommend(int rec_recommend) {
		this.rec_recommend = rec_recommend;
	}
	public Date getRec_reg_date() {
		return rec_reg_date;
	}
	public void setRec_reg_date(Date rec_reg_date) {
		this.rec_reg_date = rec_reg_date;
	}
	public Date getRec_up_date() {
		return rec_up_date;
	}
	public void setRec_up_date(Date rec_up_date) {
		this.rec_up_date = rec_up_date;
	}
	public int getRecbool() {
		return recbool;
	}
	public void setRecbool(int recbool) {
		this.recbool = recbool;
	}
	@Override
	public int hashCode() {
		return Objects.hash(mem_id, rec_comment, rec_content, rec_num, rec_recommend, rec_reg_date, rec_title,
				rec_up_date, rec_view, rec_writer, recbool);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecommendDto other = (RecommendDto) obj;
		return Objects.equals(mem_id, other.mem_id) && rec_comment == other.rec_comment
				&& Objects.equals(rec_content, other.rec_content) && Objects.equals(rec_num, other.rec_num)
				&& rec_recommend == other.rec_recommend && Objects.equals(rec_reg_date, other.rec_reg_date)
				&& Objects.equals(rec_title, other.rec_title) && Objects.equals(rec_up_date, other.rec_up_date)
				&& rec_view == other.rec_view && Objects.equals(rec_writer, other.rec_writer)
				&& recbool == other.recbool;
	}
	@Override
	public String toString() {
		return "RecommendDto [rec_num=" + rec_num + ", rec_title=" + rec_title + ", rec_content=" + rec_content
				+ ", rec_writer=" + rec_writer + ", mem_id=" + mem_id + ", rec_view=" + rec_view + ", rec_comment="
				+ rec_comment + ", rec_recommend=" + rec_recommend + ", rec_reg_date=" + rec_reg_date + ", rec_up_date="
				+ rec_up_date + ", recbool=" + recbool + "]";
	}
	public RecommendDto(Integer rec_num, String rec_title, String rec_content, String rec_writer, String mem_id,
			int rec_view, int rec_comment, int rec_recommend, Date rec_reg_date, Date rec_up_date, int recbool) {
		super();
		this.rec_num = rec_num;
		this.rec_title = rec_title;
		this.rec_content = rec_content;
		this.rec_writer = rec_writer;
		this.mem_id = mem_id;
		this.rec_view = rec_view;
		this.rec_comment = rec_comment;
		this.rec_recommend = rec_recommend;
		this.rec_reg_date = rec_reg_date;
		this.rec_up_date = rec_up_date;
		this.recbool = recbool;
	}
	public RecommendDto() {
		super();
	}
	
	public RecommendDto(String rec_title, String rec_content, String rec_writer) {
		super();
		this.rec_title = rec_title;
		this.rec_content = rec_content;
		this.rec_writer = rec_writer;
	}//RecommendDto에 제목, 내용, 작성자 입력시 해당 내용을 받아서 저장하는 메소드 생성
	
}
