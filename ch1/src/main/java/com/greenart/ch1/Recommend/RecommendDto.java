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
	private Integer rec_scorePoint;
	private Integer rec_scoreCheck;
	private Integer pd_num;
	
	public RecommendDto(String rec_title, String rec_content, String rec_writer) {
		super();
		this.rec_title = rec_title;
		this.rec_content = rec_content;
		this.rec_writer = rec_writer;
	}

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

	public Integer getRecbool() {
		return recbool;
	}

	public void setRecbool(Integer recbool) {
		this.recbool = recbool;
	}

	public Integer getRec_scorePoint() {
		return rec_scorePoint;
	}

	public void setRec_scorePoint(Integer rec_scorePoint) {
		this.rec_scorePoint = rec_scorePoint;
	}

	public Integer getRec_scoreCheck() {
		return rec_scoreCheck;
	}

	public void setRec_scoreCheck(Integer rec_scoreCheck) {
		this.rec_scoreCheck = rec_scoreCheck;
	}

	public Integer getPd_num() {
		return pd_num;
	}

	public void setPd_num(Integer pd_num) {
		this.pd_num = pd_num;
	}

	@Override
	public String toString() {
		return "RecommendDto [rec_num=" + rec_num + ", rec_title=" + rec_title + ", rec_content=" + rec_content
				+ ", rec_writer=" + rec_writer + ", mem_id=" + mem_id + ", rec_view=" + rec_view + ", rec_comment="
				+ rec_comment + ", rec_recommend=" + rec_recommend + ", rec_reg_date=" + rec_reg_date + ", rec_up_date="
				+ rec_up_date + ", recbool=" + recbool + ", rec_scorePoint=" + rec_scorePoint + ", rec_scoreCheck="
				+ rec_scoreCheck + ", pd_num=" + pd_num + "]";
	}

	public RecommendDto(Integer rec_num, String rec_title, String rec_content, String rec_writer, String mem_id,
			int rec_view, int rec_comment, int rec_recommend, Date rec_reg_date, Date rec_up_date, Integer recbool,
			Integer rec_scorePoint, Integer rec_scoreCheck, Integer pd_num) {
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
		this.rec_scorePoint = rec_scorePoint;
		this.rec_scoreCheck = rec_scoreCheck;
		this.pd_num = pd_num;
	}

	public RecommendDto() {
		super();
	}
	
	
}
