package com.greenart.ch1.QuestionsAndAnswers;

import java.util.Date;
import java.util.Objects;

public class QuestionsDto {
	private Integer ques_num;
	private String ques_title;
	private String ques_content;
	private String ques_writer;
	private Date ques_date;
	private Integer ansbool;
	
	public Integer getQues_num() {
		return ques_num;
	}
	public void setQues_num(Integer ques_num) {
		this.ques_num = ques_num;
	}
	public String getQues_title() {
		return ques_title;
	}
	public void setQues_title(String ques_title) {
		this.ques_title = ques_title;
	}
	public String getQues_content() {
		return ques_content;
	}
	public void setQues_content(String ques_content) {
		this.ques_content = ques_content;
	}
	public String getQues_writer() {
		return ques_writer;
	}
	public void setQues_writer(String ques_writer) {
		this.ques_writer = ques_writer;
	}
	public Date getQues_date() {
		return ques_date;
	}
	public void setQues_date(Date ques_date) {
		this.ques_date = ques_date;
	}
	public Integer getAnsbool() {
		return ansbool;
	}
	public void setAnsbool(Integer ansbool) {
		this.ansbool = ansbool;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ansbool, ques_content, ques_date, ques_num, ques_title, ques_writer);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionsDto other = (QuestionsDto) obj;
		return Objects.equals(ansbool, other.ansbool) && Objects.equals(ques_content, other.ques_content)
				&& Objects.equals(ques_date, other.ques_date) && Objects.equals(ques_num, other.ques_num)
				&& Objects.equals(ques_title, other.ques_title) && Objects.equals(ques_writer, other.ques_writer);
	}
	@Override
	public String toString() {
		return "QusetionsDto [ques_num=" + ques_num + ", ques_title=" + ques_title + ", ques_content=" + ques_content
				+ ", ques_writer=" + ques_writer + ", ques_date=" + ques_date + ", ansbool=" + ansbool + "]";
	}
	public QuestionsDto(Integer ques_num, String ques_title, String ques_content, String ques_writer, Date ques_date,
			Integer ansbool) {
		super();
		this.ques_num = ques_num;
		this.ques_title = ques_title;
		this.ques_content = ques_content;
		this.ques_writer = ques_writer;
		this.ques_date = ques_date;
		this.ansbool = ansbool;
	}
	public QuestionsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
