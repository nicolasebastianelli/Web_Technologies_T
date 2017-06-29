<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.Libro"%>
<%@ page import="java.util.List"%>
<script type="text/javascript" src="scripts/ajax.js"></script>
<script type="text/javascript" src="scripts/json.js"></script>
<script type="text/javascript" src="scripts/utils.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Author" content="Nicola Sebastianelli">
<title>Admin</title>
<link type="text/css" href="styles/default.css" rel="stylesheet" />
</head>
<body>
	<a href="login.jsp">Vai a login</a>
		<h1>Titolo</h1>
	<fieldset>
		<form >
			<label>Autore 1: </label><input type="text" id="autore1"><br>
			<label>Autore 2: </label><input type="text" id="autore2"><br>
			<label>Autore 3: </label><input type="text" id="autore3"><br>
			<br> <input type="button" value="cerca" onclick="cerca(myGetElementById('autore1').value,myGetElementById('autore2').value,myGetElementById('autore3').value,myGetElementById('output'))">
		</form>
	</fieldset>
	<div id="output"></div>
</body>
</html>