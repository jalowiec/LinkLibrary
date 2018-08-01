<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Strona główna</title>
</head>
<body>
<h1>Przegldarka miast</h1>
	<form action="ControllerServlet" method="post">
		<input type="text" placeholder="miasto" name="city">
		<br>
		<input type="text" placeholder="kod państwa" name="country">
		<br>
		<input type="text" placeholder="region" name="district">
		<br>
		<input type="number" placeholder="populacja" name="population">
		<br>
		Dodaj<input type="radio" name="option" value="add"> Usuń <input type="radio" name="option" value="delete">
		<br>
		<input type="submit" value="Wyślij">
	</form>
</body>
</html>