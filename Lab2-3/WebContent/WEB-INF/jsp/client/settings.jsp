<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib uri="/WEB-INF/tag.tld" prefix="mct"%>

<html>
<head>
<c:set var="title" value="Edit profile" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>

</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<h2>
		<fmt:message key="settings_main_page.form_name" />
	</h2>
	<br>
	<hr />
	<form action="controller" method="post">
		<input type="hidden" name="id" value="${sessionScope.client.id}" />
		<c:set var="client" value="${sessionScope.client}"></c:set>
		<input type="hidden" name="command" value="changeClient" /> <input
			type="hidden" name="id" value="${client.id}" />
		<table>
			<tr>
				<td><fmt:message key="settings_main_page.login" />*:</td>
				<td><input type="text" name="login" readonly
					value="${client.login}"></td>
			</tr>
			<tr>
				<td><fmt:message key="settings_main_page.email" />:</td>
				<td><input type="text" name="email" value="${client.email}"
					required></td>
			</tr>
			<tr>
				<td><fmt:message key="settings_main_page.password" /></td>
				<td><input type="password" name="password"
					value="${client.password}" pattern=".{5,}" required></td>
			</tr>
			<tr>
				<td><fmt:message key="settings_main_page.firstname" />:</td>
				<td><input type="text" name="firstName"
					value="${client.firstName}" required></td>
			</tr>
			<tr>
				<td><fmt:message key="settings_main_page.secondname" />:</td>
				<td><input type="text" name="secondName"
					value="${client.secondName}" required></td>
			</tr>
			<tr>
				<td><fmt:message key="settings_main_page.surname" />:</td>
				<td><input type="text" name="surname" value="${client.surname}"
					required></td>
			</tr>
			<tr>
				<td><fmt:message key="settings_main_page.language" />:</td>
				<td><select name="locale">
						<option value="en" ${defaultLocale == 'en' ? 'selected' : ''}>English</option>
						<option value="ru" ${defaultLocale == 'ru' ? 'selected' : ''}><fmt:message
								key="settings_main_page.russian" /></option>
				</select></td>
			</tr>
		</table>
		<input type="hidden" name="role" value="${client.roleId}" readonly>
		<br>
		<c:if test="${not empty success}">
			<div class="success">${success}</div>
		</c:if>
		<c:if test="${not empty wrong}">
			<div class="msg">${wrong}</div>
		</c:if>
		<hr />
		<input type="submit"
			value="<fmt:message key="settings_main_page.button.edit"/>" />
	</form>

</body>
</html>
