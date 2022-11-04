<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loginoutlink" value="${sessionScope.id==null? '/logIn1/logIn1':'/logIn1/logOut1'}"/>
<c:set var="loginout" value="${sessionScope.id==null? '로그인':'로그아웃' }"/>
<c:set var="signinLink" value="${sessionScope.id==null? '/register/add':''}"/>
<c:set var="signin" value="${sessionScope.id==null? '회원가입':'' }"/>
<c:set var="MyPageLink" value="${sessionScope.id==null? '':'/myPage/myPage'}"/>
<c:set var="MyPage" value="${sessionScope.id==null? '':'마이페이지' }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h2>댓글</h2>
	<input type="text" name="comment" id="comment">
	<button id="sendBtn" type="button">등록</button>
	<button id="modBtn" type="button">수정</button>
	<h2>서버로부터 온 데이터</h2>
	<div id="commentList"></div>
	<script src="http://code:jquery.com/jquery-latest.sj"></script>
	<script>
		let comm_num=224;
		
		let showList = function(comm_num){
			let comment = ${'input[name=comment]'}.val("");
		$.ajax({
			type:'GET',
			url:'/ch1/board/comments?comm_num='+comm_num
			success:function(result){
				$("#commentList").html(toHtml(result));
			},
			error:function(){alert("error")}
		});
		}
		
		$(document).ready(function(){
			showList(comm_num);
			$("#sendBtn").click(function(){
				let comment = $('input[name=comment]').val();
			if(comment.trim()==''){
				alert("입력해주세요");
				return;
			}
			$.ajax({
				type:'POST',
				url:'/ch1/board/comments/?comm_num='+comm_num,
						headers:{"content-type":"application/json"},
						data:JSON.stringify({comm_num:comm_num, comment:comment}),
						success:function(result){
							alert(result);
							showList(comm_num);
						},
						error:function(){alert("error")}
			});
			});
			$("#commentList").on("click",".modBtn", (function(){
				let comm_comm_num = $(this).parent().attr('data-comm_comm_num');
	        	let comment = $("span.comment", $(this).parent()).text();
	        	$('input[name=comment]').val(comment);
	        	$("#modBtn").attr("data-comm_comm_num", comm_comm_num);
	        	 $("#modBtn").click(function(){
	 		    	let comment = $('input[name=comment]').val();
	 		    	if(comment.trim()==''){
	 		    		alert("입력해주세요");
	 		    		return;
	 		    	}
	 		    	$.ajax({
			            type:'PATCH',       // 요청 메서드
			            url: '/ch1/board/comments/'+comm_comm_num,  // 요청 URI
			            headers: {"content-type" : "application/json"},
			            data : JSON.stringify({comm_comm_num:comm_comm_num, comment:comment}),
			            success : function(result){
			            	alert(result);
			            	showList(bno);
			        
			            },
			}))
		})
	</script>
</body>
</html>