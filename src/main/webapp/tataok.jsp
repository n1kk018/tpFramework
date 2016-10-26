<%@ page language="java" contentType="text/html; charset=Utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Welcome to action1!!!!</h3>
	<p>Success!!!!!</p>
	<form name="action1Form" id="form-1" action="http://localhost:8083/Framework/test/action1.perform" method="post">
		<select id="datalist-1" name="civility">
			<option value="Mr">Monsieur</option>
			<option value="Ms">Madame</option>
		</select>
		<label for="input-1">Firstname:</label>
		<input id="input-1" name="firstname" type="text"/>
		<label for="input-2">Lastname:</label>
		<input id="input-2" name="lastname" type="text"/>
		<input type="submit" value="Submit" id="button-1"/>
	</form>
</body>
</html>