<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Single News</title>
<style type="text/css">
.navigation {
	position: absolute;
	margin: 10px 0px 0px 15px;
	font-size: 14px;
}

.outerSingleNews {
	height: 550px;
	position: relative;
	margin: 30px 0px 0px 35px;
	font-size: 14px;
}

.inner1SingleNews {
	margin: 20px 0px 0px 0px;
	overflow: auto;
	word-break: break-all;
}

.inner2SingleNews {
	margin: 100px 0px 0px 0px;
	overflow: auto;
	word-break: break-all;
}

.Saveform {
	position: absolute;
	left: 200px;
	height: 25px;
	width: 60px;
	margin-top: 282px;
}

.Cancelform {
	position: absolute;
	left: 300px;
	height: 25px;
	width: 90px;
	margin-top: 282px;
}

.ViewPageEditButton {
	position: absolute;
	left: 290px;
	height: 25px;
	width: 10px;
	margin-top: -320px;
}

.ViewPageDeleteButton {
	position: absolute;
	left: 260px;
	height: 25px;
	width: 10px;
	margin-top: -320px;
}
.ViewPageBackButton{
	position: absolute;
	left: 300px;
	height: 25px;
	width: 10px;
	margin-top: -320px;
}

.ErrorNews {
	padding-left: 110px;
	padding-bottom: 7px;
}

.NewsTitleField{
position: absolute;
margin: -23px 0px 0px 100px;
overflow: auto;
width: 530px;
}

.NewsDateField{
position: absolute;
margin: -22px 0px 0px 100px;
overflow: auto;
width: 530px;
}

.NewsBriefField{
position: absolute;
margin: -17px 0px 0px 100px;
overflow: auto;
width: 530px;
}

.NewsContentField{
position: absolute;
margin: -17px 0px 0px 100px;
overflow: auto;
width: 530px;

}

</style>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.News.navigation.root"
	var="navigationRoot"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.navigation.add"
	var="navigationAdd"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.navigation.edit"
	var="navigationEdit"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.navigation.view"
	var="navigationView"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.empty.field.error.message"
	var="emptyFieldErrorMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.toomuch.characters.error.message"
	var="tooMuchCharsErrorMessage"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.invalid.date.value.error.message"
	var="invalidDateValueErrorMessage"></fmt:message>	
<fmt:message bundle="${locale}" key="locale.News.title.field"
	var="title"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.date.field"
	var="date"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.brief.field"
	var="brief"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.content.field"
	var="content"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.save.button"
	var="saveButton"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.edit.button"
	var="editButton"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.cancel.button"
	var="cancelButton"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.delete.button"
	var="deleteButton"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.back.button"
	var="backButton"></fmt:message>	
<fmt:message bundle="${locale}" key="locale.News.title.help.message"
	var="helpTitle"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.brief.help.message"
	var="helpBrief"></fmt:message>
<fmt:message bundle="${locale}" key="locale.News.content.help.message"
	var="helpContent"></fmt:message>
</head>
<fmt:message bundle="${locale}" key="locale.News.date.pattern"
	var="datePattern"></fmt:message>
</head>
<body>

	<div class=navigation>
		<c:if test="${requestScope.News eq 'AddNews'}">
			<a href="Index.jsp">${navigationRoot}</a> >> ${navigationAdd}
		</c:if>
		<c:if test="${requestScope.News eq 'EditNews'}">
			<a href="Index.jsp">${navigationRoot}</a> >> ${navigationEdit}
		</c:if>
		<c:if test="${requestScope.News eq 'ViewNews'}">
			<a href="Index.jsp">${navigationRoot}</a> >> ${navigationView}
		</c:if>
	</div>

	<div class=outerSingleNews>
		<form action="FrontController" method="post">

			<div class=inner1SingleNews>

				<div class=ErrorNews>
					<c:if test="${requestScope.NewsBasicError.title eq false}">
						<font color="red"> ${emptyFieldErrorMessage} </font>
					</c:if>

					<c:if
						test="${requestScope.NewsFullError.fullNewsValidationResult.title eq false}">
						<font color="red"> ${tooMuchCharsErrorMessage} </font>
					</c:if>
				</div>


				<c:if test="${not(requestScope.News eq 'ViewNews')}">
					<label for="NewsTitle">${title}</label>
					<div class=NewsTitleField> <input type="text"
						name="NewsTitle" placeholder="${helpTitle}" size="60" value="${requestScope.NewsToEdit.title}">
					</div>
				</c:if>

				<c:if test="${requestScope.News eq 'ViewNews'}">
					<label for="NewsTitle">${title}</label>
					<div class=NewsTitleField> <c:out
							value="${requestScope.NewsToView.title}"></c:out>
					</div>
				</c:if>

			</div>

			<div class=inner1SingleNews>
				<div class=ErrorNews>
					<c:if test="${requestScope.NewsBasicError.date eq false}">
						<font color="red"> ${invalidDateValueErrorMessage} </font>
					</c:if>
				</div>


				<c:if test="${requestScope.News eq 'AddNews'}">
					<jsp:useBean id="now" class="java.util.Date" scope="page" />
					<label for="NewsDate">${date}</label>
					<div class=NewsDateField> <input type="datetime-local"
						name="NewsDate"
						value="${requestScope.NewsToView.date}"
						size="17">
					</div>
				</c:if>

				<c:if test="${requestScope.News eq 'EditNews'}">
					<label for="NewsDate">${date}</label>
					<div class=NewsDateField> <input type="datetime-local"
						name="NewsDate" size="7" value="${requestScope.NewsToEdit.date}">
					</div>
				</c:if>

				<c:if test="${requestScope.News eq 'ViewNews'}">
					<label for="NewsDate">${date}</label>
					<div class=NewsDateField>
							${requestScope.NewsToView.date.format(DateTimeFormatter.ofPattern(datePattern))}</div>
				</c:if>
			</div>

			<div class=inner1SingleNews>
				<div class=ErrorNews>
					<c:if test="${requestScope.NewsBasicError.brief eq false}">
						<font color="red"> ${emptyFieldErrorMessage} </font>
					</c:if>

					<c:if
						test="${requestScope.NewsFullError.fullNewsValidationResult.brief eq false}">
						<font color="red"> ${tooMuchCharsErrorMessage} </font>
					</c:if>
				</div>


				<c:if test="${not(requestScope.News eq 'ViewNews')}">
					<label for="Brief" style='padding-up: 40px'>${brief}</label>
					<div class=NewsBriefField> <textarea name="Brief"
							rows="6" placeholder="${helpBrief}" cols="60" style="resize: none"><c:out
								value="${requestScope.NewsToEdit.brief}"></c:out></textarea>
					</div>
				</c:if>

				<c:if test="${requestScope.News eq 'ViewNews'}">
					<label for="Brief" style='padding-up: 40px'
						style='padding-right: 72px'>${brief}</label>
					<div class=NewsBriefField><c:out
							value="${requestScope.NewsToView.brief}"></c:out> </div>
				</c:if>

			</div>

			<div class=inner2SingleNews>
				<div class=ErrorNews>
					<c:if test="${requestScope.NewsBasicError.content eq false}">
						<font color="red"> ${emptyFieldErrorMessage} </font>
					</c:if>

					<c:if
						test="${requestScope.NewsFullError.fullNewsValidationResult.content eq false}">
						<font color="red"> ${tooMuchCharsErrorMessage} </font>
					</c:if>
				</div>


				<c:if test="${not(requestScope.News eq 'ViewNews')}">
					<label for="Content" style='padding-up: 40px'>${content}</label>
					<div class=NewsContentField> <textarea name="Content"
							rows="18" placeholder="${helpContent}" cols="60" style="resize: none"><c:out
								value="${requestScope.NewsToEdit.content}"></c:out></textarea>
					</div>
				</c:if>

				<c:if test="${requestScope.News eq 'ViewNews'}">
					<label for="Content" style='padding-up: 40px'>${content}</label>
					<div class=NewsContentField> <c:out
							value="${requestScope.NewsToView.content}"></c:out>
					</div>
				</c:if>

			</div>

			<div class="Saveform">

				<c:if test="${requestScope.News eq 'EditNews'}">
					<input type="submit" name="editnews" value="${saveButton}">
					<input type="hidden" name="check" value="EDIT_NEWS">
					<input type="hidden" name="NewsID" value="${NewsID}">
				</c:if>

				<c:if test="${requestScope.News eq 'AddNews'}">

					<input type="submit" name="savenews" value="${saveButton}">
					<input type="hidden" name="check" value="SAVE_NEWS">
				</c:if>

				<div class="ViewPageEditButton">
					<c:if test="${(requestScope.News eq 'ViewNews')&&(sessionScope.role eq 'admin')}">

						<input type="submit" name="editnews" value="${editButton}">
						<input type="hidden" name="check" value="GO_TO_EDIT_NEWS_PAGE">
						<input type="hidden" name="NewsID" value="${NewsID}">
					</c:if>
				</div>
			</div>
		</form>

		<form action="FrontController" method="post">
			<div class="Cancelform">
				<c:if test="${not(requestScope.News eq 'ViewNews')}">
					<input type="submit" name="cancelsavingnews" value="${cancelButton}">
					<input type="hidden" name="check" value="CANCEL_SAVE_NEWS">
				</c:if>

				<div class="ViewPageDeleteButton">
					<c:if test="${(requestScope.News eq 'ViewNews')&&(sessionScope.role eq 'admin')}">
						<input type="submit" name="deletenews" value="${deleteButton}">
						<input type="hidden" name="check" value="DELETE_NEWS">
						<input type="hidden" name="NewsCheckBox" value="${NewsID}">
					</c:if>
				</div>
				<div class="ViewPageBackButton">
					<c:if test="${(requestScope.News eq 'ViewNews')&&(not(sessionScope.role eq 'admin'))}">
						<input type="submit" name="back" value="${backButton}">
						<input type="hidden" name="check" value="CANCEL_SAVE_NEWS">
					</c:if>
				</div>

			</div>
		</form>
	</div>
</body>
</html>