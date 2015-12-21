<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib uri="/WEB-INF/tag.tld" prefix="mct"%>
<html>
<head>
<c:set var="title" value="Edit Client" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>

</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<h2>
		<fmt:message key="edit_client_page.form_name" />
		= "${client.id}"
	</h2>
	<br>
	<hr />
	<form action="controller" method="post">
		<c:set var="client" value="${requestScope.client}"></c:set>
		<input type="hidden" name="command" value="changeClient" /> <input
			type="hidden" name="id" value="${client.id}" />
		<table>
			<tr>
				<td><fmt:message key="edit_client_page.login" />*:</td>
				<td><input type="text" name="login" readonly
					value="${client.login}" required></td>
			</tr>
			<tr>
				<td><fmt:message key="edit_client_page.email" />:</td>
				<td><input type="text" name="email" value="${client.email}"
					required></td>
			</tr>
			<tr>
				<td><fmt:message key="edit_client_page.password" /></td>
				<td><input type="password" name="password" pattern=".{5,}"
					value="${client.password}" required></td>
			</tr>
			<tr>
				<td><fmt:message key="edit_client_page.firstname" />:</td>
				<td><input type="text" name="firstName"
					value="${client.firstName}" required></td>
			</tr>
			<tr>
				<td><fmt:message key="edit_client_page.secondname" />:</td>
				<td><input type="text" name="surname" value="${client.surname}"
					required></td>
			</tr>
			<tr>
				<td><fmt:message key="edit_client_page.surname" />:</td>
				<td><input type="text" name="secondName"
					value="${client.secondName}" required></td>
			</tr>
			<tr>
				<mct:getCountUserRoleInfo />
			</tr>
			<tr>
				<td>Sale: %</td>
				<td><input type="number" name="sale" min="0" max="100"
					value="0" required></td>
			</tr>
		</table>
		<br>
		<c:if test="${not empty success}">
			<div class="success">${success}</div>
		</c:if>
		<c:if test="${not empty wrong}">
			<div class="msg">${wrong}</div>
		</c:if>
		<hr />
		<input type="submit"
			value="<fmt:message key="edit_client_page.button.edit"/>" /> <input
			type="button"
			onclick="location.href='controller?command=viewAllClients';"
			value="<fmt:message key="edit_client_main_page.button.back_to_clients"/>" />
	</form>

</body>
</html>