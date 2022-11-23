package com.greenart.ch1.QuestionsAndAnswers;

import java.util.Date;
import java.util.Objects;

public class AnswerDto {
	private Integer ans_num;
	private String ans_title;
	private String ans_content;
	private String ans_writer;
	private Date ans_date;
	
	public Integer getAns_num() {
		return ans_num;
	}
	public void setAns_num(Integer ans_num) {
		this.ans_num = ans_num;
	}
	public String getAns_title() {
		return ans_title;
	}
	public void setAns_title(String ans_title) {
		this.ans_title = ans_title;
	}
	public String getAns_content() {
		return ans_content;
	}
	public void setAns_content(String ans_content) {
		this.ans_content = ans_content;
	}
	public String getAns_writer() {
		return ans_writer;
	}
	public void setAns_writer(String ans_writer) {
		this.ans_writer = ans_writer;
	}
	public Date getAns_date() {
		return ans_date;
	}
	public void setAns_date(Date ans_date) {
		this.ans_date = ans_date;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ans_content, ans_date, ans_num, ans_title, ans_writer);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerDto other = (AnswerDto) obj;
		return Objects.equals(ans_content, other.ans_content) && Objects.equals(ans_date, other.ans_date)
				&& Objects.equals(ans_num, other.ans_num) && Objects.equals(ans_title, other.ans_title)
				&& Objects.equals(ans_writer, other.ans_writer);
	}
	@Override
	public String toString() {
		return "AnswerDto [ans_num=" + ans_num + ", ans_title=" + ans_title + ", ans_content=" + ans_content
				+ ", ans_writer=" + ans_writer + ", ans_date=" + ans_date + "]";
	}
	public AnswerDto(Integer ans_num, String ans_title, String ans_content, String ans_writer, Date ans_date) {
		super();
		this.ans_num = ans_num;
		this.ans_title = ans_title;
		this.ans_content = ans_content;
		this.ans_writer = ans_writer;
		this.ans_date = ans_date;
	}
	public AnswerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
