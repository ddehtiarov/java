<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<td><fmt:message key="edit_client_page.role_id"/></td>
<c:choose>
	<c:when test="${sessionScope.client.id != client.id}">
		<td><select name="role">
				<c:set var="roleId" value="${client.roleId}" />
				<option value="0"
					<c:if test="${roleId eq 0}">selected=selected</c:if>>Admin</option>
				<option value="1"
					<c:if test="${client.roleId eq 1}">selected=selected</c:if>>Client</option>
		</select></td>
	</c:when>
	<c:when test="${sessionScope.client.id == client.id}">
		<td><input type="text" name="role" value="0" readonly></td>
	</c:when>
</c:choose>