<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>

<style type="text/css">
.outer {
	width: 250px;
	height: 220px;
	position: fixed;
	margin: 20px 0px 0px 30px;
	border: 1px solid Silver;
	font-size: 14px;
	overflow: auto;
	background-color: #DCDCDC;
}

.inner1 {
	width: 180px;
	height: 120px;
	margin: 20px 0px 0px 60px;
	position: fixed;
	border: 1px solid Silver;
	font-size: 14px;
	overflow: auto;
	background-color: #FFFFFF;
}

.form {
	margin: 150px 0px 0px 160px;
}

.title {
	margin: 10px 0px 0px 10px;
	border: 1px solid Silver;
	width: 237px;
	height: 35px;
	text-align: center;
	background-color: #696969;
}

.list {
	margin: 25px 0px 0px 20px
}

.userText {
	font-size: 20px;
	text-align: center;
	margin-top: 30px;
}
</style>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.UserMenu.title"
	var="menuTitle"></fmt:message>
<fmt:message bundle="${locale}" key="locale.UserMenu.link.newslist"
	var="linkNewsList"></fmt:message>
<fmt:message bundle="${locale}" key="locale.UserMenu.link.addnews"
	var="linkAddNews"></fmt:message>
<fmt:message bundle="${locale}" key="locale.UserMenu.signout.button"
	var="signOutButton"></fmt:message>
</head>
<body>
	<div class="outer">
		<div class="title">
			<font size=5px color="#FFFFFF">${menuTitle} </font>
		</div>
		<div class="inner1">

			<div class="list">

				<ul>
					<c:if
						test="${(sessionScope.role eq 'admin')||(sessionScope.role eq 'user')}">
						<li><a href="FrontController?check=GO_TO_BASIC_LAYOUT">${linkNewsList}</a>
						<br>
					</c:if>
					
					<c:if
						test="${sessionScope.role eq 'admin'}">
						<li><a href="FrontController?check=GO_TO_ADD_NEWS_PAGE">${linkAddNews}</a>
					</c:if>
				</ul>
			</div>
		</div>
		<form class="form" action="FrontController" method="get">
			<input type="submit" name="signout" value="${signOutButton}">
			<input type="hidden" name="check" value="SIGNOUT">
		</form>
	</div>
</body>
</html>