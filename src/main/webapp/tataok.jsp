<%@ page language="java" contentType="text/html; charset=Utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Welcome to action1!!!!</h3>
	<p>Success!!!!!</p>
	<p style="color: red;">${error_message}</p>
	<form name="action1Form" id="form-1" action="http://localhost:8083/Framework/test/action1.perform" method="post">
		<label for="input-1">Civility:</label>
		<select id="datalist-1" name="civility">
			<option value="Mr" <c:out value="${param.civility=='Mr'?'selected=':''}" />>Monsieur</option>
			<option value="Ms" <c:out value="${param.civility=='Ms'?'selected=':''}" />>Madame</option>
		</select><br />
		<label for="input-1">Firstname:</label>
		<input id="input-1" name="firstname" type="text" value="${param.firstname}" /><br />
		<label for="input-2">Lastname:</label>
		<input id="input-2" name="lastname" type="text" value="${param.lastname}" /><br />
		<label for="input-2">Age:</label>
		<input id="input-3" name="age" type="text" value="${param.age}" /><br />
		<input type="submit" value="Submit" id="button-1"/>
	</form>
</body>
</html>