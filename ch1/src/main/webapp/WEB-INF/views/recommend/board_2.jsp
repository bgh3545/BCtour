<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:set var = "mypageLink" value="${sessionScope.id==null? '':'/myPage/myPage_main'}"/>
<c:set var = "mypage" value="${sessionScope.id==null? '':'마이 페이지'}"/>
<c:set var = "LoginOutlink" value="${sessionScope.id==null? '/logIn/logIn':'/logIn/logOut'}"/>
<c:set var = "LoginOut" value="${sessionScope.id==null? 'Login':'Logout'}"/>
<c:set var = "LoginOutlinkMypage" value="${sessionScope.id==null? '/BCsignup':''}"/>
<c:set var = "LoginMypage" value="${sessionScope.id==null? '회원가입':''}"/>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
    <title>비씨투어</title>
<link href="../resources/CSS/BCtourStyle.css?aas" rel="stylesheet"/>
<link rel="icon" href="${path }/resources/img/상단로고.jpg" />
</head>
<body>
	<div class="main">
    	<div class="allDiv">
        	<div class="header">
            	<ul>  
	   	 			<li><a href="<c:url value='${LoginOutlinkMypage}'/>">${LoginMypage}</a></li>
	   	 			<li><a href="<c:url value='${LoginOutlink}'/>">${LoginOut}</a></li>  
                </ul>
            </div>
            <div class="search" style="text-align: center;">
                <div class="logoDiv">
                    <a href="<c:url value='/'/>"><img class="logo" src='data:image/png;base64,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'/></a>
                </div>
                <form action="<c:url value='/searchProduct'/>">
                	<div class="searchEngine">
                        <input type="text" id="keyword" name="keyword" value="" placeholder="검색">
                        <button type="submit" class="b_searchBtn">검색</button>
                	</div>
                </form>
                <div class="mypageDiv">
                    <ul class="mypage">
                        <c:if test="${sessionScope.id != 'admin'}">
                            <li><a href="<c:url value='${mypageLink}'/>">${mypage}</a></li>
                            </c:if>
                            <c:if test="${sessionScope.id == 'admin'}">
                            <li><a href="<c:url value='/myPage/manage'/>">고객 관리</a></li>
                        </c:if>
                        <li><a id="select" href="<c:url value='/board/list_2'/>">여행일지</a></li>
                    </ul>
                </div>
            </div>
            <div class ="nav">
                <div id="column">
                    <div class="city">
                        <h2><a href="<c:url value='/board/list_1'/>">공지사항</a></h2>
                    </div>
                    <div class="city">
                        <h2><a id="select" href="<c:url value='/board/list_2'/>">여행일지</a></h2>
                    </div>
                    <div class="city">
                        <h2><a href="<c:url value='/board/list_3'/>">자유게시판</a></h2>
                    </div>
                </div>
                <div class="column2">
                    <div class="b_title">여행일지</div>
                    	<div class="b_writebtn">
                    		<button type="button" id="10recbtn" class="b_btnsize">10추</button>
                    	</div>
                    <div class="b_content">
                   		<div class="b_indextitle">
                    		<div class="b_rindexNum">번호</div>
                    		<div class="b_rindexName">제목</div>
                    		<div class="b_rindexNum">작성자</div>
                    		<div class="b_rindexNum">조회수</div>
                    		<div class="b_rindexNum">추천수</div>
                    		<div class="b_rindexDate">등록일</div>
                    	</div>
                    	<c:if test="${1 == page}">
                    	<c:forEach var="i" items="${notice}">
                    		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
							<fmt:formatDate value="${i.reg_Date}" pattern="yyyy-MM-dd" var="regDate" />
							<fmt:formatDate value="${i.reg_Date}" pattern="HH:mm" var="regTime" />
	                    	<div class="b_indextitle b_noticebgcolor">
	                    		<div class="b_rcontentNum">공지사항</div>
	                    		<div class="b_rcontentName"><a href="<c:url value='/board/read_1?bno=${i.bno}&page=${page}&pageSize=${pageSize}'/>">${i.title}</a></div>
	                    		<div class="b_rcontentNum">${i.writer}</div>
	                    		<div class="b_rcontentNum">${i.view_cnt}</div>
	                    		<div class="b_rcontentNum">&nbsp;</div>
	                    		<div class="b_rcontentDate">${today==regDate? regTime:regDate}</div>
	                    	</div>
                    	</c:forEach>
                    	</c:if>
                    	<c:forEach var="i" items="${rec}">
                    		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
							<fmt:formatDate value="${i.rec_reg_date}" pattern="yyyy-MM-dd" var="regDate" />
							<fmt:formatDate value="${i.rec_reg_date}" pattern="HH:mm" var="regTime" />
	                    	<div class="b_indextitle">
	                    		<div class="b_rcontentNum">${i.rec_num}</div>
	                    		<div class="b_rcontentName"><a href="<c:url value='/board/read_2?rec_num=${i.rec_num}&page=${page}&pageSize=${pageSize}'/>">${i.rec_title}&nbsp${i.rec_comment==0?'':[i.rec_comment]}</a></div>
	                    		<div class="b_rcontentNum">${i.rec_writer}</div>
	                    		<div class="b_rcontentNum">${i.rec_view}</div>
	                    		<div class="b_rcontentNum">${i.rec_recommend}</div>
	                    		<div class="b_rcontentDate">${today==regDate? regTime:regDate}</div>
	                    	</div>
                    	</c:forEach>
                    	<div class="b_pageNavi">
                    		<c:if test="${ph.showPrev}">
                    		<a href="<c:url value='/board/${addr}${ph.sc.getQueryString(ph.beginPage-1)}'/>"><div class="b_preNext">이전</div></a>
                    		</c:if>
                    		<c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    		<c:if test="${i == page}">
                    		<a href="<c:url value='/board/${addr}${ph.sc.getQueryString(i)}'/>"><div id="select" class="b_pageNum">${i}</div></a>
                    		</c:if>
                    		<c:if test="${i != page}">
                    		<a href="<c:url value='/board/${addr}${ph.sc.getQueryString(i)}'/>"><div class="b_pageNum">${i}</div></a>
                    		</c:if>
                    		</c:forEach>
                    		<c:if test="${ph.showNext}">
                    		<a href="<c:url value='/board/${addr}${ph.sc.getQueryString(ph.endPage+1)}'/>"><div class="b_preNext">다음</div></a>
                    		</c:if>
                    	</div>
                    	<div class="b_searchboardarea">
                    	<form action="<c:url value='/board/list_2'/>" class="b_searchform" method="get">
                    		<select class="b_searchnavi" name="option">
                    			<option value="A" ${ph.sc.option=='A' || ph.sc.option==''? "selected":""}>제목+내용</option>
                    			<option value="T" ${ph.sc.option=='T' ? "selected":""}>제목</option>
                    			<option value="W" ${ph.sc.option=='W' ? "selected":""}>작성자</option>
                    		</select>
                    		<input class="b_searchbar" name="keyword" type="text" value="${ph.sc.keyword}" placeholder="검색">
                    		<input type="submit" class="b_searchbutton" value="검색">
                    	</form>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<script>
		
		document.getElementById('10recbtn').addEventListener('click',e=>{
		window.location = "<c:url value='/board/rec10'/>";
		});
	
		let msg = "${msg}"
		if(msg == "del") alert("성공적으로 삭제되었습니다.")
		if(msg == "error") alert("삭제에 실패하였습니다.")
		if(msg == "write_error") alert("게시글 작성에 실패하였습니다. 다시 작성해 주세요")
		
	</script>
</body>
</html>