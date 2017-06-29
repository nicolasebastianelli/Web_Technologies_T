<%@page import="com.google.gson.Gson"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.Libro"%>
<%@ page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Author" content="Nicola Sebastianelli">
<title>Admin</title>
<link type="text/css" href="styles/default.css" rel="stylesheet" />
</head>
<body>
	<a href="cerca.jsp">Vai a cerca</a>
	<%
if(request.getParameter("logout")!=null && request.getParameter("logout").equals("logout")){
	request.getSession().setAttribute("login", null);
}
if(request.getParameter("user")!=null && request.getParameter("password")!=null && request.getParameter("log")!=null && request.getParameter("log").equals("LogIn")){
if(request.getParameter("user").equals("admin")&& request.getParameter("password").equals("admin"))
    request.getSession().setAttribute("login",request.getParameter("user"));
}

if(request.getSession().getAttribute("login")!=null){
	if(request.getParameter("inserisci")!= null && request.getParameter("inserisci").equals("inserisci") 
			&& request.getParameter("autore")!= null && request.getParameter("titolo")!= null &&
			request.getParameter("editore")!= null && request.getParameter("isbn")!= null	){
	Libro libro = new Libro(request.getParameter("autore"),request.getParameter("titolo"),request.getParameter("editore"),request.getParameter("isbn"));
	Gson gson = new Gson();
	String res = gson.toJson(libro);
	request.setAttribute("res", res);
	request.getRequestDispatcher("salvaNuovoLibro").forward(request, response);
	}
	else{
	%>
	<a style="float: right" href="?logout=logout">Esci</a>
	<h1>Inserisci libri</h1>
	<fieldset>
		<form method="post" action="login.jsp">
			<label>Autore: </label><input type="text" name="autore"><br>
			<br> <label>Titolo: </label><input type="text" name="titolo"><br>
			<br> <label>Editore: </label><input type="text" name="editore"><br>
			<br> <label>ISBN: </label><input type="text" name="isbn"><br>
			<br> <input type="submit" name="inserisci" value="inserisci">
		</form>
	</fieldset>
	<%
	}
}
else{
%>

	<h1>Login</h1>
	<fieldset>
		<form method="post" action="login.jsp">
			<label>User: </label><input type="text" name="user"><br>
			<br> <label>Password: </label><input type="text" name="password"><br>
			<br> <input type="submit" name="log" value="LogIn">
		</form>
	</fieldset>
	<%
}
%>
</body>
</html>
