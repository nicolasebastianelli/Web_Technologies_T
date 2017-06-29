<%@page import="java.util.Map"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.Conteggi"%>
<%@ page import="beans.Conteggio"%>
<%@ page import="java.util.List"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<meta name="Author" content="Nicola Sebastianelli">
		<title>Conteggio</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"/>	
	</head>
	<body>
	<jsp:useBean id="conteggiSession" class="beans.Conteggi" scope="session" />
	
<a href="admin.jsp">Vai a admin</a>
		<h1>Scommesse</h1>
		<fieldset>
			<form method="post" action="dispatcher">
				<label>File: </label><input type="text" name="file"><br><br>
				<label>Carattere alfanumerico: </label><input type="text" name="car" maxlength=1 ><br><br>
				<input type="submit" name="conferma" value="conferma" ><br><br>
			</form>
				<% if(request.getAttribute("stato")!=null){
				%>
				<p>  Stato ricerca: <%=request.getAttribute("stato") %></p>
				<%
				}
				%>
		</fieldset>
		<% if(conteggiSession.getConteggi().size()>0){%>
				<h4>Ricerche confermate:</h4>
				<%
				}
				int index=1;
				for(Conteggio conteggio :conteggiSession.getConteggi()){
					%>
					<p>- Ricerca <%=index %>:</p>
					<p>  File: <%=conteggio.getNomeFile() %></p>
					<p>  Carattere: <%=conteggio.getCarattere() %></p>
					<p>  Numero di occorrenze: <%=conteggio.getNumeroOccorrenze() %></p>
					<p>  Ora di ricerca: <%=conteggio.getOraRicerca().toLocalTime().toString() %></p>
					<br><br>
					<%
					index ++;
				}
				%>
	</body>
</html>
