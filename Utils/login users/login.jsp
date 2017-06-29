<%@ page session="true"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@page import="servlets.LoginServlet"%>

<html>
<head>
<title>Login jsp</title>
<link rel="stylesheet" href="styles/default.css" type="text/css"></link>
</head>
<body>
	<%
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
		<label for="usermail">Email</label> <input type="email"
			name="usermail" placeholder="yourname@email.com" required> <br />
		<label for="username">Username</label> <input type="text"
			name="username" placeholder="username" required> <br /> <label
			for="password">Password</label> <input type="password"
			name="password" placeholder="password" required> <br /> <input
			type="submit" value="Login">
	</form>
</body>
</html>
