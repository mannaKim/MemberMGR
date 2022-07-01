<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	//이 파일을 실행시켜서 loginForm으로 이동! (loginForm에서 실행시키면 서블릿이 연결 안됨)
	//response.sendRedirect("member/loginForm.jsp"); //경로가 노출됨
	response.sendRedirect("login.do");
	//서블릿을 거쳐서 포워딩 된 페이지는 외부로 노출되지 않습니다.
	//로그인 폼의 주소 : http://localhost:8090/WEB08_MemberMGR/login.do
%>
</body>
</html>