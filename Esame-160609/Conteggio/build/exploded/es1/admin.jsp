<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.temporal.TemporalUnit"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.Conteggi"%>
<%@ page import="beans.Conteggio"%>
<%@ page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="Author" content="Nicola Sebastianelli">
		<title>Conteggi Globali</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"/>	
</head>
<body>
<jsp:useBean id="conteggiApplication" class="beans.Conteggi" scope="application" />
<a href="start.jsp">Vai a ricerca</a>
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
	<h1>Conteggi Globali</h1>
	<fieldset>
		<%
		int index=1;
			if(conteggiApplication.getConteggi().size()!=0)
				for(Conteggio conteggio : conteggiApplication.getConteggi()){					
					if(conteggio.getOraRicerca().isAfter(LocalDateTime.now().minus(30, ChronoUnit.MINUTES))){
						
		%>
					<p>- Ricerca <%=index %>:</p>
					<p>  File: <%=conteggio.getNomeFile() %></p>
					<p>  Carattere: <%=conteggio.getCarattere() %></p>
					<p>  Numero di occorrenze: <%=conteggio.getNumeroOccorrenze() %></p>
					<p>  Ora di ricerca: <%=conteggio.getOraRicerca().toLocalTime().toString() %></p>
			<% index++;
			}					
				}
					else{
			%>
			<h4>Nessuna ricerca entro 30 minuti trovata</h4>
	<%
		}
	}
else{
%>

<h1>Login</h1>
		<fieldset>
			<form method="post" action="admin.jsp">
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