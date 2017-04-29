<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<title>Statistiche</title>
	</head>

	<body>
		
		<h3>Statistiche</h3>
		
		<a href="<%=request.getContextPath()%>/gestisciCliente.jsp">Vai a gestisciCliente.jsp</a><br />
		<br />
		
		<form name="statisticheForm" action="statisticheServlet" method="post">
			inserisci i prarmetri della ricerca:<br />
			id: <input type="text" name="id" value=""/> <br />
			firstDay: <input type="text" name="firstDay" value=""/> <br />
			lastDay: <input type="text" name="lastDay" value=""/> <br />
			<input type="submit" name="req" value="calcola"/><br />
		</form>	
		<br />
		
		<% 
        Float guadagnoNoCookie = (Float)request.getAttribute("guadagnoRichiestaAttuale");
		if( guadagnoNoCookie != null ){
			// mostro il risultato della ricerca appena effettuata
			%>
    		Risultato:<br />
    		guadagno=<%= guadagnoNoCookie %><br />
    		<%
		}
		else{
			// mostro il risultato della ricerca effettuata l'ultima volta
			// e memorizzata tramite cookie
			String id = null;
			String firstDay = null;
			String lastDay = null;
			String guadagno = null;
	    	Cookie[] cookies = request.getCookies();
	    	if ( cookies != null && cookies.length > 0 ) {
				for ( Cookie cookie : cookies ) {
	        		if ( cookie.getName().equals("id") ) {
	        			id = cookie.getValue();
	        		}
	        		else if ( cookie.getName().equals("firstDay") ) {
	        			firstDay = cookie.getValue();
	        		}
	        		else if ( cookie.getName().equals("lastDay") ) {
	        			lastDay = cookie.getValue();
	        		}
	        		else if ( cookie.getName().equals("guadagno") ) {
	        			guadagno = cookie.getValue();
	        		}
	    		}
	    	}
	    	if ( id!=null && firstDay!=null && lastDay!=null && guadagno!=null ) {
	    		%>
	    		Ricerca precedente:<br />
	    		id=<%= id %><br />
	    		firstDay=<%= firstDay %><br />
	    		lastDay=<%= lastDay %><br />
	    		guadagno=<%= guadagno %><br />
	    		<%
	    	}
		}
    	%>		
		
	</body>
</html>
