package com.greenart.ch1.PageHandlerAndSearchCondition;

public class CommPageHandler {
	private CommSearchCondition csc;
	private int totalCnt;
	private int naviSize=10;
	private int totalPage;
	private int beginPage;
	private int endPage;
	private boolean showPrev;
	private boolean showNext;
	
	public CommPageHandler(int totalCnt, CommSearchCondition csc) {
		this.totalCnt=totalCnt;
		this.csc=csc;
		doPaging(totalCnt,csc);
	}
	
	public void doPaging(int totalCnt, CommSearchCondition csc) {
		this.totalCnt= totalCnt;
		
		totalPage= (int)Math.ceil(totalCnt/(double)csc.getCommPageSize());
		beginPage= (csc.getCommPage()-1)/naviSize*naviSize+1;
		endPage= Math.min(beginPage+naviSize-1, totalPage);
		showPrev= beginPage!= 1;
		showNext= endPage!= totalPage;
	}

	public CommSearchCondition getCsc() {
		return csc;
	}

	public void setCsc(CommSearchCondition csc) {
		this.csc = csc;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getNaviSize() {
		return naviSize;
	}

	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isShowPrev() {
		return showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	public boolean isShowNext() {
		return showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}

	@Override
	public String toString() {
		return "PageHandler [csc=" + csc + ", totalCnt=" + totalCnt + ", naviSize=" + naviSize + ", totalPage="
				+ totalPage + ", beginPage=" + beginPage + ", endPage=" + endPage + ", showPrev=" + showPrev
				+ ", showNext=" + showNext + "]";
	}

	public CommPageHandler(CommSearchCondition csc, int totalCnt, int naviSize, int totalPage, int beginPage, int endPage,
			boolean showPrev, boolean showNext) {
		super();
		this.csc = csc;
		this.totalCnt = totalCnt;
		this.naviSize = naviSize;
		this.totalPage = totalPage;
		this.beginPage = beginPage;
		this.endPage = endPage;
		this.showPrev = showPrev;
		this.showNext = showNext;
	}

	public CommPageHandler() {
		super();
	}


	
}