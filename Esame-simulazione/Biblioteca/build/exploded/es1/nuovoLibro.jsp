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
<title>Inserisci</title>
<link type="text/css" href="styles/default.css" rel="stylesheet" />
</head>
<body>
	<a href="elencoLibri.jsp">Vai a elenco</a>
	
	<jsp:useBean id="libroSession" class="beans.Libro" scope="session" />
	<%
		if(libroSession.getAutore()==null&&libroSession.getTitolo()==null&&libroSession.getIsbn()==null){
		libroSession.setAutore("");
		libroSession.setTitolo("");
		libroSession.setIsbn("");
		}
	%>
	<h1>Inserisci libri</h1>
	<fieldset>
		<form method="post" action="salvaNuovoLibro">
			<label>Autore: </label><input type="text" name="autore" value="<%= libroSession.getAutore()%>"><br>
			<br> <label>Titolo: </label><input type="text" name="titolo" value="<%=libroSession.getTitolo() %>"><br>		
			<br> <label>ISBN: </label><input type="text" name="isbn" value="<%=libroSession.getIsbn() %>"><br>
			<br> <input type="submit" name="save" value="save">
			<br> <input type="submit" name="finalizza" value="finalizza">
		</form>
	</fieldset>

</body>
</html>
