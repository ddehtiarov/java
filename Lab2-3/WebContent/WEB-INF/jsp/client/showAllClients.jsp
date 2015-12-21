<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<head>
<c:set var="title" value="All Clients" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>

</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<h2>
		<fmt:message key="edit_client_main_page.form_name" />
	</h2>
	<br>
	<table id="t01"
		style="align: center; width: 1124px; text-align: center">
		<thead style="background: #227755">
			<tr>
				<th><fmt:message key="edit_client_main_page.email" /></th>
				<th><fmt:message key="edit_client_main_page.login" /></th>
				<th><fmt:message key="edit_client_main_page.firstname" /></th>
				<th><fmt:message key="edit_client_main_page.secondname" /></th>
				<th><fmt:message key="edit_client_main_page.surname" /></th>
				<th><fmt:message key="edit_client_main_page.role_id" /></th>
				<th colspan="2"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.clients}" var="client">
				<c:url var="editUrl"
					value="controller?command=editClientPage&id=${client.id}" />
				<c:url var="removeUrl"
					value="controller?command=deleteClientPage&id=${client.id}" />
				<tr>
					<td><c:out value="${client.email}" /></td>
					<td><c:out value="${client.login}" /></td>
					<td><c:out value="${client.firstName}" /></td>
					<td><c:out value="${client.surname}" /></td>
					<td><c:out value="${client.secondName}" /></td>
					<td><c:out value="${client.roleId}" /></td>
					<c:if test="${clientRole.name == 'admin'}">
						<td><a href="${editUrl}"><fmt:message
									key="edit_client_main_page.button.edit" /></a></td>
						<td><a href="${removeUrl}"><fmt:message
									key="edit_client_main_page.button.delete" /></a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${not empty error}">
		<div class="msg">${error}</div>
	</c:if>
</body>
</html>