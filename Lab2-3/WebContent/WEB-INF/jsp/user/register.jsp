<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<head>
<c:set var="title" value="Register" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>

<script type="text/javascript">
	
<%@ include file="../script/register.js" %>
</script>

</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div id="edit-box">
		<form action="controller" method="post" onsubmit="return validate_form ();" id="register_form">
		<input type="hidden" name="command" value="register"/>
		<h2><fmt:message key="register_client_page.form_name"/></h2>
			<table>
				<tr>
					<td><fmt:message key="register_client_page.email" />:</td>
					<td><input name="email" id="email" value="${client.email}" maxlength="35"/></td>
				</tr>
				<tr>
					<td><fmt:message key="register_client_page.login" />:</td>
					<td><input name="login" value="${client.login}"  maxlength="25" required/></td>
				</tr>
				<tr>
					<td><fmt:message key="register_client_page.password" />:</td>
					<td><input type="password" name="password" value="${client.password}" pattern=".{5,}" maxlength="15"/></td>
				</tr>
				<tr>
					<td><fmt:message key="register_client_page.confpass" />:</td>
					<td><input type="password" name="password2" value="${client.password}" maxlength="15"/></td>
				</tr>
				<tr>
					<td><fmt:message key="register_client_page.firstname" />:</td>
					<td><input name="firstName" value="${client.firstName}"  maxlength="15" required/></td>
				</tr>
				<tr>
					<td><fmt:message key="register_client_page.secondname" />:</td>
					<td><input name="secondname" value="${client.secondName}"  maxlength="15" required/></td>
				</tr>
				<tr>
					<td><fmt:message key="register_client_page.surname" />:</td>
					<td><input name="surname" value="${client.surname}"  maxlength="15" required/></td>
				</tr>
			</table>
			<input type="submit" value="<fmt:message key="register_client_page.title" />" />
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