package com.greenart.ch1.PageHandlerAndSearchCondition;

import java.util.Objects;

import org.springframework.web.util.UriComponentsBuilder;

public class CommSearchCondition {
	private Integer commPage= 1;
	private Integer commPageSize= 10;
	private String keyword= "";
	private String option;
	
	public Integer getCommOffset() {
		return (commPage-1)*commPageSize;
	}
	
	public String getQueryString(Integer commPage) {
		return UriComponentsBuilder.newInstance()
				.queryParam("commPage", commPage)
				.queryParam("commPageSize", commPageSize)
				.queryParam("option", option)
				.queryParam("keyword", keyword)
				.build().toString();
	}
	
	public String getQueryString() {
		return getQueryString(commPage);
	}

	public Integer getCommPage() {
		return commPage;
	}

	public void setCommPage(Integer commPage) {
		this.commPage = commPage;
	}

	public Integer getCommPageSize() {
		return commPageSize;
	}

	public void setCommPageSize(Integer commPageSize) {
		this.commPageSize = commPageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	@Override
	public String toString() {
		return "SearchCondition [commPage=" + commPage + ", pageSize=" + commPageSize + ", keyword=" + keyword + ", option="
				+ option + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(keyword, option, commPage, commPageSize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommSearchCondition other = (CommSearchCondition) obj;
		return Objects.equals(keyword, other.keyword) && Objects.equals(option, other.option)
				&& Objects.equals(commPage, other.commPage) && Objects.equals(commPageSize, other.commPageSize);
	}

	public CommSearchCondition(Integer commPage, Integer commPageSize, String keyword, String option) {
		super();
		this.commPage = commPage;
		this.commPageSize = commPageSize;
		this.keyword = keyword;
		this.option = option;
	}

	public CommSearchCondition() {
		super();
	}
	
}
