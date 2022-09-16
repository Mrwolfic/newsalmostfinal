<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Iterator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>News List</title>
<style type="text/css">
.navigation {
	position: absolute;
	margin: 10px 0px 0px 15px;
	font-size: 14px;
}

.newslist {
	width: 650px;
	position: absolute;
	margin: 50px 0px 0px 35px;
	font-size: 14px;
	overflow: auto;
}

.news {

	width: 640px;
}

.headerNewsList {
	display: table-cell;
}

.breif {
	margin: 20px 0px 0px 0px;
}

.menulist {
	margin: 10px 0px 0px 520px;
}

.dateplace {
	position: absolute;
	right: 40px;
}

.NewsListPageDeleteButton {
	margin-left: 570px;
	margin-top: 15px;
	margin-bottom: 20px;
}

.Pagination {
	font-size: 18px;
	margin-left: 20px;
	margin-top: 590px;
	width: 400px;
}
}
</style>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="locale" />
<fmt:message bundle="${locale}"
	key="locale.NewsList.news.navigation.root" var="root"></fmt:message>
<fmt:message bundle="${locale}"
	key="locale.NewsList.news.navigation.newslist" var="newslist"></fmt:message>
<fmt:message bundle="${locale}" key="locale.NewsList.nonews"
	var="nonews"></fmt:message>
<fmt:message bundle="${locale}" key="locale.NewsList.news.edit"
	var="edit"></fmt:message>
<fmt:message bundle="${locale}" key="locale.NewsList.news.view"
	var="view"></fmt:message>
<fmt:message bundle="${locale}" key="locale.NewsList.delete.button"
	var="delete"></fmt:message>
<fmt:message bundle="${locale}" key="locale.NewsList.date.format"
	var="datePattern"></fmt:message>
</head>
<body>

	<div class=navigation>
		<c:if test="${requestScope.News eq null}">
			<a href="Index.jsp">${root}</a> >> ${newslist}
		</c:if>
	</div>
	<c:if test="${not (requestScope.NewsList eq null)}">
		<div class=newslist>

			<form action="FrontController" method="post">

				<c:forEach var="news" items="${requestScope.NewsList}">
					<c:if test="${not(news eq null)}">
						<div class=news>
							<div class=headerNewsList>
								<c:out value="${news.title}" />
								<span class=dateplace>${news.date.format(DateTimeFormatter.ofPattern(datePattern))}</span>
							</div>

							<div class=breif>
								<c:out value="${news.brief}" />
							</div>

							<div class=menulist>
								<c:if test="${sessionScope.role eq 'admin'}">
									<span style='padding-left: 10px'> <a
										href="FrontController?check=GO_TO_EDIT_NEWS_PAGE&NewsID=${news.newsID}">${edit}</a>
									</span>
								</c:if>
								<c:if test="${not(sessionScope.role eq null)}">
									<span style='padding-left: 10px'> <a
										href="FrontController?check=GO_TO_VIEW_ONE_NEWS_PAGE&NewsID=${news.newsID}">${view}</a>
									</span>
								</c:if>
								<c:if test="${sessionScope.role eq 'admin'}">
									<span style='padding-left: 10px'> <input type="checkbox"
										name="NewsCheckBox" value="${news.newsID}">
									</span>
								</c:if>
							</div>
							<br>
						</div>
					</c:if>
				</c:forEach>
				<div class="NewsListPageDeleteButton">
					<c:if
						test="${(sessionScope.role eq 'admin')&&not(requestScope.NewsList eq null)}">
						<input type="submit" name="deletenews" value="${delete}">
						<input type="hidden" name="check" value="DELETE_NEWS">
					</c:if>
				</div>
			</form>
		</div>

		<div class=Pagination>
			<c:forEach var="page" items="${sessionScope.pageAmount}">
				<a
					href="FrontController?check=GO_TO_PORTION_NEWS_PAGE&PageID=${page}">${page}</a>
			</c:forEach>
		</div>
	</c:if>
	<br>
	<c:if test="${requestScope.NewsList eq null}">
		<span style='font-size: 24px'>${nonews}</span>
	</c:if>

	</div>
</body>
</html>