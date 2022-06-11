
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<title>Shinhan BlockChain Project</title>
	
	
	<!-- <script src="../js/jquery-3.6.0.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	
</head>
<body>
<h1 align="center">
	증명서 확인 페이지 
	
<style type="text/css" media="screen">
 #fig5_8 form dt {
  float: left;
  padding-right: 10px;
  width: 130px;
  }
</style>

</h1>	
	<div id="fig5_8">
	<form action="document.do" method="get">
	  	<dl>
	   		<dt><label for="name">이름 :</label></dt>
	     	<dd><input type="text" placeholder="이름을 입력하세요." style="width:600px;height:30px;font-size:15px;" name="name" /></dd>
	   		<dt><label for="email">Hash No :</label></dt>
	      	<dd><input type="text" placeholder="hash 값을 입력하세요." style="width:600px;height:30px;font-size:15px;" name="hash" /></dd>
	   		<dt><p><input align="center"  type="submit" style="width:300px;height:30px;font-size:15px;" value="부채증명원 확인하기" /></dt>
	  	</dl>
	</form>
	</div>


</body>
</html>
