<%@ page session="true"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="beans.LoginInfo"%>
<script src="scripts/ajax.js"></script>
<script src="scripts/utils.js"></script>

<html>
<head>
<title>Portale</title>
<link rel="stylesheet" href="styles/default.css" type="text/css"></link>
</head>
<body>
<%
	if(request.getSession().getAttribute(LoginInfo.LOGIN_INFO)==null){
		request.setAttribute("loginError", "user not logged in");
		getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
	}
%>
	<h1>Portale</h1>
	<fieldset>
	<form >
		<label>URL 1:</label><input type= "text" id="url1"><br>
		<label>URL 2:</label><input type= "text" id="url2"><br>
		<label>URL 3:</label><input type= "text" id="url3"><br>
		<input type="button" value="Conferma"id="conferma" onclick="checkUrl(myGetElementById('url1').value,myGetElementById('url2').value,myGetElementById('url3').value,myGetElementById('output'))">
	</form>
	</fieldset>
	<div id="output"></div>
</body>
</html>
