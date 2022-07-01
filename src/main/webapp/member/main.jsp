<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>main.jsp</title>
	<script type="text/javascript">
		function withDrawConfirm(){
			var bool = confirm("정말로 탈퇴하시겠습니까?");
			if(bool){
				location.href="withdraw.do?userid="+"${loginUser.userid}";
			}else{
				return;
			}
		}
	</script>
</head>
<body>
	<table>
		<tr><td>${loginUser.name}(${loginUser.userid})님이 로그인 하셨습니다.</td></tr>
		<tr><td>email : ${loginUser.email}</td></tr>
		<tr><td>phone : ${loginUser.phone}</td></tr>
		<tr>
			<td>
				<input type="button" value="로그아웃" onClick="location.href='logout.do'">
				<!-- 어떤 태그이든 onClick 속성을 써서 페이지 이동을 하고자 한다면, 
				위와 같이 location.href를 onClick 속성에 지정해서 이동합니다. onClick 속성에는
				페이지 이동 기능이 없기 때문에 페이지만 쓴다고 이동하지 않으니 반드시 
				location.href로 페이지를 지정해주세요.-->
				<input type="button" value="회원정보변경" 
					   onClick="location.href='update.do?userid=${loginUser.userid}'">
				<%-- EL 또는 JSP의 '<%= %>'는 문자 데이터들 사이에 끼어들어서 조합 문자 데이터 구성이 가능 --%>
				<input type="button" value="회원 탈퇴" onClick="withDrawConfirm();">
			</td>
		</tr>
	</table>
	
	<br><br>
	<c:if test="${loginUser.admin==1}">
		<table align="left" width="800" bgcolor="black" cellspacing="1">
			<tr bgcolor="white">
				<th>아이디</th><th>이름</th><th>전화번호</th>
				<th>이메일</th><th>등급</th><th>등급변경</th>
			</tr>
			<c:forEach items="${mList}" var="member">
				<tr bgcolor="white" align="center">
					<td>${member.userid}</td><td>${member.name}</td>
					<td>${member.phone}</td><td>${member.email}</td>
					<td>
						<c:choose>
							<c:when test="${member.admin==1}">관리자</c:when>
							<c:otherwise>일반사용자</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${member.admin==1}">
								<input type="button" value="일반으로 변경"
								onClick="location.href='editadmin.do?userid=${member.userid}&admin=${member.admin}'">
							</c:when>
							<c:otherwise>
								<input type="button" value="관리자로 변경"
								onClick="location.href='editadmin.do?userid=${member.userid}&admin=${member.admin}'">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>