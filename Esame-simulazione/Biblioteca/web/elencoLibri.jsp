<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.Libro"%>
<%@ page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Author" content="Nicola Sebastianelli">
<title>elenco libri</title>
<link type="text/css" href="styles/default.css" rel="stylesheet" />
</head>
<body>
<jsp:useBean id="libri" class="beans.Libri" scope="application" />
	<a href="nuovoLibro.jsp">Vai a nuovo libro</a>
		<h1>Elenco libro</h1>
	<fieldset>
		<form >
			<label>Autore: </label><input type="text" name="autore"><br>
			<br> <input type="submit" name="cerca" value="cerca">
		</form>
	</fieldset>
	<div id="output">
		<%
			if(request.getParameter("cerca")!= null){
				int i =0 ;
				for(Libro libro : libri.getLibri())
					if(libro.getAutore().equals(request.getParameter("autore"))){
						i++;
						%>
						<h5>Libro<%=i %></h5><br>
						<label>Autore: <%= libro.getAutore() %></label><br>
						<label>Titolo:  <%= libro.getTitolo() %></label><br>
						<label>ISBn:  <%= libro.getIsbn() %></label><br>
						<%
					}
			}
		%>
	</div>
</body>
</html>