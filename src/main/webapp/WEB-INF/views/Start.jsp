
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Shinhan BlockChain Project</title>
	
	
	<!-- <script src="../js/jquery-3.6.0.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	
	<script type="text/javascript">

		function goSend(){
			$.ajax({
				url : 'http://localhost:8080/AutoBlock/home.do',
				method : 'POST',
				data : "EMP_NAME=" + $("#EMP_NAME").val() ,
				success : function(data)	{
					location.href = "/AutoBlock/home";
					
				},
				error:function(request, status, error){
					alert("error");
				}
			});
		}
</script>
	
</head>
<body>
<h1 align="center">
	부채증명원 발급 하기
</h1>
	
	<form action="home.do" method="get">
		<label for="name">이름 : </label>
		<input type="text" name="name">
		<button type="submit">부채증명원 생성하기</button>
	</form>
	
</body>
</html>
