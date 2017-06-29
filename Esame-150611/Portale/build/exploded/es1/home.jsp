<%@ page session="true"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="beans.LoginInfo"%>
<html>
<head>
<title>Login jsp</title>
<link rel="stylesheet" href="styles/default.css" type="text/css"></link>
</head>
<body>
	<%
	if(request.getSession().getAttribute(LoginInfo.LOGIN_INFO)!=null){
		getServletContext().getRequestDispatcher("/portale.jsp").forward(request, response);
	}
		String message = null;
		if ((message = (String) request.getAttribute(LoginServlet.LOGIN_ERROR_MESSAGE)) != null)
			message = "Login error: " + message;
		else
			message = "Insert your credentialis";
	%>
	<p><%=message%></p>
	<script src="scripts/forms.js"></script>
	<form name="login" action="loginservlet"
		onSubmit="validateLoginForm(this)" method="post">
		<label for="username">Nome</label> <input type="text"
			name="username" placeholder="nome" required> <br /> <label
			for="password">Cognome</label> <input type="text"
			name="password" placeholder="cognome" required> <br /> <input
			type="submit" value="Login">
	</form>
</body>
</html>
