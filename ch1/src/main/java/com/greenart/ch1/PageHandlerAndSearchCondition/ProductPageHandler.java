package com.greenart.ch1.PageHandlerAndSearchCondition;

import java.util.Objects;

public class ProductPageHandler {
	private ProductSearchCondition psc;
	private int totalCnt;
	private int naviSize=10;
	private int totalPage;
	private int beginPage;
	private int endPage;
	private boolean showPrev;
	private boolean showNext;
	
	public ProductPageHandler(int totalCnt, ProductSearchCondition psc) {
		this.totalCnt=totalCnt;
		this.psc=psc;
		doPaging(totalCnt,psc);
	}
	
	public void doPaging(int totalCnt, ProductSearchCondition psc) {
		this.totalCnt= totalCnt;
		
		totalPage= (int)Math.ceil(totalCnt/(double)psc.getPageSize());
		beginPage= (psc.getPage()-1)/naviSize*naviSize+1;
		endPage= Math.min(beginPage+naviSize-1, totalPage);
		showPrev= beginPage!= 1;
		showNext= endPage!= totalPage;
	}

	public ProductSearchCondition getPsc() {
		return psc;
	}

	public void setPsc(ProductSearchCondition psc) {
		this.psc = psc;
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
	public int hashCode() {
		return Objects.hash(beginPage, endPage, naviSize, psc, showNext, showPrev, totalCnt, totalPage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPageHandler other = (ProductPageHandler) obj;
		return beginPage == other.beginPage && endPage == other.endPage && naviSize == other.naviSize
				&& Objects.equals(psc, other.psc) && showNext == other.showNext && showPrev == other.showPrev
				&& totalCnt == other.totalCnt && totalPage == other.totalPage;
	}

	@Override
	public String toString() {
		return "ProductPageHandler [psc=" + psc + ", totalCnt=" + totalCnt + ", naviSize=" + naviSize + ", totalPage="
				+ totalPage + ", beginPage=" + beginPage + ", endPage=" + endPage + ", showPrev=" + showPrev
				+ ", showNext=" + showNext + "]";
	}

	public ProductPageHandler(ProductSearchCondition psc, int totalCnt, int naviSize, int totalPage, int beginPage,
			int endPage, boolean showPrev, boolean showNext) {
		super();
		this.psc = psc;
		this.totalCnt = totalCnt;
		this.naviSize = naviSize;
		this.totalPage = totalPage;
		this.beginPage = beginPage;
		this.endPage = endPage;
		this.showPrev = showPrev;
		this.showNext = showNext;
	}

	public ProductPageHandler() {
		super();
	}
	
	
}