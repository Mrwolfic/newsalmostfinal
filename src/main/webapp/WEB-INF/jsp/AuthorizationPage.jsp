<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Authorization</title>
<style type="text/css">

   .outer{ 
    width : 20%;
    position: fixed; 
	margin: 20px 0px 0px 30px;
	border: 1px solid Silver transparent;
	font-size: 14px;
	overflow: auto;
	
}

</style>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.AuthorizationPage.welcome.message"
	var="welcomeMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.AuthorizationPage.error.message"
	var="errorMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.AuthorizationPage.field.login"
	var="login"></fmt:message>
<fmt:message bundle="${locale}" key="locale.AuthorizationPage.field.password"
	var="password"></fmt:message>
<fmt:message bundle="${locale}" key="locale.AuthorizationPage.authorize.button"
	var="authorize"></fmt:message>
<fmt:message bundle="${locale}" key="locale.AuthorizationPage.back.button"
	var="back"></fmt:message>
</head>
<body>
	<div class="outer">
		<h2>
			<br> ${welcomeMessage}
		</h2>
		
		<br>
		<c:if test="${not(param.AuthenticationError eq null)}">	
		<font color="red"> 	 
		 ${errorMessage}
		 </font> 
		</c:if>
	    
		<form action="FrontController" method="get">
			<div class="inner1">
				<input id="login" type="text" name="login"> <label
					for="login">${login}</label><br>
			</div>
			
			<br>
			
			<div class="inner2">
				<input id="password" type="password" name="password">
				<label for="password">${password}</label><br>
			</div>
			
			<br>
			
			<div class="inner3">
				<input type="submit" name="infotoauthorize" value="${authorize}">
				<input type="hidden" name="check" value="AUTHORIZE">
			</div>
		</form>
		<br>		
			<br>
			<form action="FrontController" method="get">
			<div class="inner4">
				<input type="submit" name="infotoauthorize" value="${back}">
				<input type="hidden" name="check" value="GO_TO_BASIC_LAYOUT">
			</div>
			</form>
		
	</div>
</body>
</html>