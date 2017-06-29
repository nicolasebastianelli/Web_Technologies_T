<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.Scommesse"%>
<%@ page import="beans.Scommessa"%>
<%@ page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="Author" content="Nicola Sebastianelli">
		<title>Scommesse Globali</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"/>	
</head>
<body>
<jsp:useBean id="scommesseApplication" class="beans.Scommesse" scope="application" />
<a href="scommesse.jsp">Vai a scommesse</a>
<%
if(request.getParameter("logout")!=null && request.getParameter("logout").equals("logout")){
	request.getSession().setAttribute("login", null);
}
if(request.getParameter("user")!=null && request.getParameter("password")!=null && request.getParameter("log")!=null&request.getParameter("user").equals("admin")&& request.getParameter("password").equals("admin") && request.getParameter("log").equals("LogIn")){
request.getSession().setAttribute("login",request.getParameter("user"));
}

if(request.getSession().getAttribute("login")!=null){
	%>
	<a style="float:right"href="?logout=logout">Esci</a>
	<h1>Scommesse Globali</h1>
	<fieldset>
			<form>
				<label>Squadra 1: </label><input type="text" name="team1" ><br><br>
				<label>Squadra 2: </label><input type="text" name="team2" ><br><br>
				<input type="submit" name="search" value="Cerca">
			</form>
		</fieldset>
		<%
			if(request.getParameter("search")!=null && request.getParameter("search").equals("Cerca") ){
				if(request.getParameter("team1")==null||request.getParameter("team1").equals("")||request.getParameter("team2")==null||request.getParameter("team2").equals(""))
				for(String key : scommesseApplication.getPartite().keySet()){
		%>
			<p>- Partita: <%=key %></p>
			<p>  Importo totale: <%=scommesseApplication.getPartite().get(key) %></p>
			<br>
			<% }
				else{
					String key1 = request.getParameter("team1").toLowerCase()+" "+request.getParameter("team2").toLowerCase();
					String key2 = request.getParameter("team2").toLowerCase()+" "+request.getParameter("team1").toLowerCase();
					if(scommesseApplication.getPartite().get(key1)!= null){
						%>
						<p>- Partita: <%=key1 %></p>
						<p>  Importo totale: <%=scommesseApplication.getPartite().get(key1) %></p>
						<br>
						<%
					}
					else if(scommesseApplication.getPartite().get(key2)!= null){
						%>
						<p>- Partita: <%=key2 %></p>
						<p> Importo totale: <%=scommesseApplication.getPartite().get(key2) %></p>
						<br>
						<%
					}
					else{
			%>
			<h4>Nessuna partita trovata</h4>
	<%
		}
	}
}
}
else{
%>

<h1>Login</h1>
		<fieldset>
			<form method="post" action="scommesseGlobali.jsp">
				<label>User: </label><input type="text" name="user" ><br><br>
				<label>Password: </label><input type="text" name="password" ><br><br>
				<input type="submit" name="log" value="LogIn">
			</form>
		</fieldset>
<%
}
%>
</body>
</html>