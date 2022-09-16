<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sign Up</title>
<style type="text/css">
.outer {
	width: 20%;
	position: fixed;
	margin: 20px 0px 0px 30px;
	border: 1px solid Silver transparent;
	font-size: 14px;
	resize: none !important;
	overflow: auto;
}

.textarea {
	resize: none !important;
}
</style>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.SignUpPage.signup.success.message"
	var="successMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.tomainpage.button"
	var="toMainPageButton"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.welcome.message"
	var="welcomeMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.empty.field.error.message"
	var="emptyFieldMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.invalid.field.error.message"
	var="invalidFieldMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.login.exist.error.message"
	var="loginExistMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.email.exist.error.message"
	var="emailExistMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.login.field"
	var="login"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.password.field"
	var="password"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.email.field"
	var="email"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.date.field"
	var="date"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.gender.field"
	var="gender"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.info.field"
	var="info"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.gender.field.value1"
	var="genderValue1"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.gender.field.value2"
	var="genderValue2"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.signup.button"
	var="signUpButton"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.back.button"
	var="backButton"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.login.help.message"
	var="helpLogin"></fmt:message>
<fmt:message bundle="${locale}" key="locale.SignUpPage.password.help.message"
	var="helpPassword"></fmt:message>
</head>
<body>
	<div class="outer">

		<c:if test="${not(param.SignUpDone eq null)}">
			<br>
			<font size=4px> ${successMessage}
			</font>
			<br>
			<br>
			<form action="FrontController" method="get">
				<div class="inner4">
					<input type="submit" name="signup" value="${toMainPageButton}">
					<input type="hidden" name="check" value="GO_TO_BASIC_LAYOUT">
				</div>
			</form>

		</c:if>

		<c:if test="${param.SignUpDone eq null}">

			<h2>
				<br> ${welcomeMessage}
			</h2>

			<br>

			<form action="FrontController" method="get">
				<div class="inner1">
					<c:if test="${requestScope.BasicSignUpError.login eq false}">
						<font color="red"> ${emptyFieldMessage} </font>
						
					</c:if>

					<c:if test="${requestScope.FullSignUpError.loginValid eq false}">
						<font color="red"> ${invalidFieldMessage} </font>
						
					</c:if>
					
					<c:if test="${requestScope.FullSignUpError.loginAlreadyExist eq false}">
						<font color="red"> ${loginExistMessage}</font>
						
					</c:if>
					
					<input id="login" type="text" placeholder="${helpLogin}" name="login"
						value=${requestScope.user.login}> <label for="login">${login}</label><br>
				</div>

				<br>

				<div class="inner2">
					<c:if test="${requestScope.BasicSignUpError.password eq false}">
						<font color="red"> ${emptyFieldMessage} </font>
						
					</c:if>

					<c:if test="${requestScope.FullSignUpError.password eq false}">
						<font color="red"> ${invalidFieldMessage} </font>
						
					</c:if>
					
					<input id="password" type="password" placeholder="${helpPassword}" name="password"
						value=${requestScope.user.password}> <label for="password">${password}</label><br>
				</div>

				<br>

				<div class="inner3">
					<c:if test="${requestScope.BasicSignUpError.email eq false}">
						<font color="red"> ${emptyFieldMessage} </font>
						
					</c:if>

					<c:if test="${requestScope.FullSignUpError.emailValid eq false}">
						<font color="red"> ${invalidFieldMessage} </font>
						
					</c:if>
					<c:if test="${requestScope.FullSignUpError.emailAlreadyExist eq false}">
						<font color="red"> ${emailExistMessage}</font>
						
					</c:if>
					<input id="email" type="email" name="email"
						value=${requestScope.user.email}> <label for="email">${email}</label><br>
				</div>

				<br>

				<div class="inner4">
					<c:if test="${requestScope.BasicSignUpError.dateofbirth eq false}">
						<font color="red"> ${emptyFieldMessage} </font>
						
					</c:if>

					<c:if test="${requestScope.FullSignUpError.dateofbirth eq false}">
						<font color="red"> ${invalidFieldMessage} </font>
						
					</c:if>
					<input id="date" type="date" required name="date"
						value=${requestScope.user.dateofbirth}> <label for="date">${date}</label><br>
				</div>

				<br>

				<div class="inner5">
					<c:if test="${requestScope.BasicSignUpError.gender eq false}">
						<font color="red"> ${emptyFieldMessage} </font>
						
					</c:if>

					<c:if test="${requestScope.FullSignUpError.gender eq false}">
						<font color="red"> ${invalidFieldMessage} </font>
						
					</c:if>
					<select id="gender" required name="gender"
						value=${requestScope.user.gender}>
						<option value="male">${genderValue1}</option>
						<option value="female">${genderValue2}</option>
					</select> <label for="gender">${gender}</label><br>
				</div>

				<br>

				<div class="inner6">
					<textarea id="info" name="comment" style="resize: none"
						value="${requestScope.user.additionalinfo}" >
					
				</textarea>
					<label for="info">${info}</label>
				</div>

				<br>

				<div class="inner7">
					<input type="submit" name="senduserinfo" value="${signUpButton}"> <input
						type="hidden" name="check" value="SIGNUP">
				</div>
			</form>
			<br>
			<br>
			<form action="FrontController" method="get">
				<div class="inner4">
					<input type="submit" name="infotoauthorize"
						value="${backButton}"> <input
						type="hidden" name="check" value="GO_TO_BASIC_LAYOUT">
				</div>
			</form>
		</c:if>
	</div>
</body>
</html>