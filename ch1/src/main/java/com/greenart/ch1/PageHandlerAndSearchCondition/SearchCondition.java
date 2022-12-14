package com.greenart.ch1.PageHandlerAndSearchCondition;

import java.util.Objects;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
	private Integer page= 1;
	private Integer pageSize= 10;
	private String keyword= "";
	private String option;
	
	public Integer getOffset() {
		return (page-1)*pageSize;
	}
	
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("pageSize", pageSize)
				.queryParam("option", option)
				.queryParam("keyword", keyword)
				.build().toString();
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
		return "SearchCondition [page=" + page + ", pageSize=" + pageSize + ", keyword=" + keyword + ", option="
				+ option + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(keyword, option, page, pageSize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchCondition other = (SearchCondition) obj;
		return Objects.equals(keyword, other.keyword) && Objects.equals(option, other.option)
				&& Objects.equals(page, other.page) && Objects.equals(pageSize, other.pageSize);
	}

	public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.option = option;
	}

	public SearchCondition() {
		super();
	}
	
}
