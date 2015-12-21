<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<head>
<c:set var="title" value="Login" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>

</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div id="edit-box">
		<h2><fmt:message key="login_client_page.form_name"/></h2>
		<form action="controller" method="post">
		<input type="hidden" name="command" value="login.do"/>
			<table>
				<tr>
					<td><fmt:message key="login_client_page.login"/></td>
					<td><input name="login" value="${requestScope.login}"  maxlength="25" /></td>
				</tr>
				<tr>
					<td><fmt:message key="login_client_page.password"/></td>
					<td><input type = "password" name="password" value="${requestScope.password}"  maxlength="15"/></td>
				</tr>
			</table>
			<input type="submit" value="<fmt:message key="login_client_page.title"/>">
		</form>
		<c:if test="${not empty success}">
			<div class="success">${success}</div>
		</c:if>
		<c:if test="${not empty wrong}">
			<div class="msg">${wrong}</div>
		</c:if>
	</div>
</body>
</html>