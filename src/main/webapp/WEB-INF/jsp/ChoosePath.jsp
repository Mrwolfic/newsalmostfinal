<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ChoosePath</title>
<style type="text/css">

   .outer{ position: absolute; 
	margin: 20px 0px 0px 60px;
	font-size: 14px;
	text-align: center;
	overflow: auto;
}

</style>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.ChoosePath.hellomessage.part1"
	var="hellomessage1"></fmt:message>
<fmt:message bundle="${locale}" key="locale.ChoosePath.hellomessage.part2"
	var="hellomessage2"></fmt:message>
<fmt:message bundle="${locale}" key="locale.ChoosePath.authorize.button"
	var="authorize"></fmt:message>
<fmt:message bundle="${locale}" key="locale.ChoosePath.signup.button"
	var="signup"></fmt:message>
</head>
<body>

	<div class="outer">
		<h2>
			<br> ${hellomessage1}
			<br> ${hellomessage2}
		</h2>
		<br>
		
		<form action="FrontController" method="get">
			<div class="inner2">
				<input type="submit" name="authorization" value="${authorize}">
				<input type="hidden" name="check" value="GO_TO_AUTHRIZATION_PAGE">
			</div>
		</form>
		
		<br>
			
		<form action="FrontController" method="get">
			<div class="inner1">
				<input type="submit" name="signup" value="${signup}"> <br>
				<input type="hidden" name="check" value="GO_TO_SIGNUP_PAGE">
			</div>
		</form>

	</div>
</body>
</html>