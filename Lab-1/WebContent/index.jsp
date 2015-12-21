<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lab-1</title>
</head>
<body>
	
	<form action="calc" name="calc">
		<input type="text" name="first" id="first"/><br>
		<input type="text" name="second" id="second"/><br>
		<select name="operation" id="operation">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
		</select>
		<br>
		<input type="submit" value="Calculate"/>
	</form>
	${result}<br>
	
</body>
</html>