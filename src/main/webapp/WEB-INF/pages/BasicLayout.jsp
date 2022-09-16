<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News project</title>
<style type="text/css">
html, body {
	height: 100%;
	width: 1000px;
	border: 1px solid Silver;
}

* {
	margin: 0 auto;
}

.header {
	position: fixed;
	height: 120px;
	width: 1000px;
	border: 1px solid Silver;
	background-color: White;
	margin-left: -1px;
	margin-right: -1px;
	margin-top: -1px;
}

.prname {
	margin-left: 400px;
	margin-top: 39px;
	font-size: 38px;
}

.leftpart, .content {
	bottom: 16px;
	position: absolute;
	top: 120px;
	overflow: auto;
}

.leftpart {
	width: 20%;
	border: 1px solid Silver transparent;
	font-size: 32px;
	
	margmargin-left: -1px;in-right: -1px;
	margin-top: 2px;
}

.content {
	left: 20%;
	right: 0;
	top: 128px;
	bottom: 16 px;
	border: 1px solid Silver;
	font-size: 38px;
	position: absolute;
	width: 700px;
}

.footer {
	position: absolute;
	bottom: 0px;
	height: 16px;
	width: 1000px;
	border: 1px solid Silver;
	text-align: center;
	font-size: 14px;
}

.localization {
	margin-left: 840px;
	margin-top: 10px;
}

.localization li {
	font-size: 14px;
	display: inline; 
    padding: 5px;
}
</style>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.BasicLayout.buttonname.en"
	var="en_button"></fmt:message>
<fmt:message bundle="${locale}" key="locale.BasicLayout.buttonname.ru"
	var="ru_button"></fmt:message>
<fmt:message bundle="${locale}" key="locale.BasicLayout.projectname"
	var="projectname"></fmt:message>
<fmt:message bundle="${locale}" key="locale.BasicLayout.footer"
	var="footer"></fmt:message>
</head>
<body>
	<div class=header>
		<div class=prname>${projectname}</div>

		<div class=localization>
			<ul>			
				<li><a href="FrontController?check=CHANGE_LOCALE&locale=en">
				<c:out value="${en_button}" />
				</a></li>
				
				<li><a href="FrontController?check=CHANGE_LOCALE&locale=ru">
				<c:out value="${ru_button}" />
				</a></li>
			</ul>
		</div>

	</div>

	<div class=leftpart>

		<c:if
			test="${(param.auth_or_sign eq null)&&(requestScope.auth_or_sign eq null)&&(sessionScope.role eq null)}">
			<c:import url="/WEB-INF/jsp/ChoosePath.jsp" />
		</c:if>
		<c:if
			test="${(param.auth_or_sign eq 'authorize')||(requestScope.auth_or_sign eq 'authorize')&&(sessionScope.role eq null)}">
			<c:import url="/WEB-INF/jsp/AuthorizationPage.jsp" />
		</c:if>
		<c:if
			test="${(param.auth_or_sign eq 'signup')||(requestScope.auth_or_sign eq 'signup')&&(sessionScope.role eq null)}">
			<c:import url="/WEB-INF/jsp/SignUpPage.jsp" />
		</c:if>


		<c:if test="${not(sessionScope.role eq null)}">
			<c:import url="/WEB-INF/pages/UserMenu.jsp" />
		</c:if>


	</div>

	<div class=footer>"${footer}"(c)</div>

	<div class=content>
		<c:if
			test="${(not(sessionScope.role eq null)&& not (requestScope.News eq null))}">
			<c:import url="/WEB-INF/jsp/News.jsp" />
		</c:if>

		<c:if
			test="${not (requestScope.News eq 'AddNews')&&(requestScope.News eq null)}">
			<c:import url="/WEB-INF/jsp/NewsList.jsp" />
		</c:if>
	</div>



</body>
</html>