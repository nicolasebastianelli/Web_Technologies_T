<%@page import="java.util.Map"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.Scommesse"%>
<%@ page import="beans.Scommessa"%>
<%@ page import="java.util.List"%>
<%!double calcola(String vincente,  double importo){
	double result=0;
	if(vincente.toLowerCase().equals("italia")){
			result=importo*1.5;	
	}
	else{
			result=importo*1.2;
	}
	return result;
}


%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<meta name="Author" content="Nicola Sebastianelli">
		<title>Scommesse</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"/>	
	</head>
	<body>
	<jsp:useBean id="scommesseSession" class="beans.Scommesse" scope="session" />
	<jsp:useBean id="scommesseApplication" class="beans.Scommesse" scope="application" />
	
	<%
String vincitore="";
String perdente="";
Scommessa scommessa =new Scommessa();
double importo=0;
if ( request.getParameter("calcola") != null && request.getParameter("calcola").equals("calcola") ) {
	vincitore=request.getParameter("vincente");
	perdente=request.getParameter("perdente");
	try{
	importo= Double.parseDouble(request.getParameter("importo"));
	}
	catch(Exception e){ }
}
else if ( request.getParameter("conferma") != null && request.getParameter("conferma").equals("conferma")) {
	vincitore=request.getParameter("vincente").toLowerCase();
	perdente=request.getParameter("perdente").toLowerCase();
	scommessa.setVincitore(vincitore);
	scommessa.setPerdente(perdente);
	scommessa.setImporto(importo);
	try{
		scommessa.setImporto(Double.parseDouble(request.getParameter("importo")));
	}
	catch(Exception e){
		throw new IllegalArgumentException("Importo non valido");
	}
	scommessa.setVincita(calcola(scommessa.getVincitore(),scommessa.getImporto()));
	scommesseSession.getScommesse().add(scommessa);
	scommesseApplication.getScommesse().add(scommessa);
	String key;
	if(scommesseApplication.getPartite().get(vincitore+" "+perdente)!=null){
		key=vincitore+" "+perdente;
	}
	else if(scommesseApplication.getPartite().get(perdente+" "+vincitore)!=null){
		key=perdente+" "+vincitore;
	}
	else
	{
		scommesseApplication.getPartite().put(vincitore+" "+perdente, 0.0);
		key=vincitore+" "+perdente;
	}
	scommesseApplication.getPartite().put(key, scommesseApplication.getPartite().get(key)+scommessa.getImporto());
}
%>
<a href="scommesseGlobali.jsp">Vai a scommesse globali</a>
		<h1>Scommesse</h1>
		<fieldset>
			<form>
				<label>Vincente: </label><input type="text" name="vincente" value="<%=vincitore %>"><br><br>
				<label>Perdente: </label><input type="text" name="perdente" value="<%=perdente %>"><br><br>
				<label>Importo: </label><input type="text" name="importo" value="<%=importo %>"><br><br>
				<input type="submit" name="calcola" value="calcola" ><label name=vincita>  Vincita: <%= calcola(vincitore,importo) %> (&#8364;)</label><br><br>
				<input type="submit" name="conferma" value="conferma" ><br><br>
			</form>
		</fieldset>
		<% if(scommesseSession.getScommesse().size()>0){%>
				<h4>Scommesse confermate:</h4>
				<%
				}
				int index=1;
				for(Scommessa scommessa1 :scommesseSession.getScommesse()){
					%>
					<p>- SCOMMESSA <%=index %>:</p>
					<p>  Vincitore: <%=scommessa1.getVincitore() %></p>
					<p>  Perdente: <%=scommessa1.getPerdente() %></p>
					<p>  Importo: <%=scommessa1.getImporto() %></p>
					<p>  Vincita: <%=scommessa1.getVincita() %></p>
					<br><br>
					<%
					index ++;
				}
				%>
	</body>
</html>
