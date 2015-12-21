<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<head>
<c:set var="title" value="Delete Client" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>

</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="deleteClient.do" /> <input
			type="hidden" name="id" value="${client.id}" />
		<c:set var="client" value="${client}"></c:set>
		<c:choose>
			<c:when test="${sessionScope.client.id == client.id}">
				<br>
				<br>
				<h2>
					<fmt:message key="delete_client_page.title" />
				</h2>
			</c:when>
			<c:when test="${sessionScope.client.id != client.id}">
				<h1>
					<fmt:message key="delete_client_page.form_name" />
					= "${client.id}"?
				</h1>
				<table>
					<tr>
						<td><fmt:message key="delete_client_page.login" />:</td>
						<td>"${client.login}"</td>
					</tr>
					<tr>
						<td><fmt:message key="delete_client_page.email" />:</td>
						<td>"${client.email}"</td>
					</tr>
				</table>
				<input type="submit"
					value="<fmt:message key="delete_client_page.button.delete"/>" />
			</c:when>
		</c:choose>
		<hr />
		<input type="button"
			onclick="location.href='controller?command=viewAllClients';"
			value="<fmt:message key="delete_client_page.button.back_to_clients"/>" />

	</form>
</body>
</html>