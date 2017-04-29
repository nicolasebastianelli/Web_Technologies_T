<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.es1.beans.InsiemeDiArticoli"%>
<%@ page import="it.unibo.tw.es1.beans.Articolo"%>
<%@ page import="java.util.*"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<title>Gestisci Cliente</title>
	</head>

	<body>
		
		<h3>Gestisci Cliente</h3>
		
		<a href="<%=request.getContextPath()%>/statistiche.jsp">Vai a statistiche.jsp</a><br />
		<br />
		
		<jsp:useBean id="merceSelezionata" class="it.unibo.tw.es1.beans.InsiemeDiArticoli" scope="session" />
		<jsp:useBean id="merceVenduta" class="it.unibo.tw.es1.beans.InsiemeDiArticoli" scope="application" />
		
		<%
		String req = request.getParameter("req");
		if( req!=null){
			if(req.equals("aggiungiArticolo")){
				Vector<Articolo> articoli = merceSelezionata.getMerce();
				Articolo newAr = new Articolo();
				newAr.setId(request.getParameter("id"));
				newAr.setAmount(Integer.parseInt(request.getParameter("amount")));
				newAr.setPrice(Float.parseFloat(request.getParameter("price")));
				newAr.setDay(Integer.parseInt(request.getParameter("day")));
				articoli.add(newAr);
			}
			else if(req.equals("concludi")){
				Vector<Articolo> articoliSelezionati = merceSelezionata.getMerce();
				Vector<Articolo> articoliVenduti = merceVenduta.getMerce();
				articoliVenduti.addAll(articoliSelezionati);
				merceSelezionata.setMerce(new Vector<Articolo>());
			}
		}
		%>
		
		<form name="aggiungiArticoloForm" action="gestisciCliente.jsp" method="post">
			inserici un nuovo articolo:<br />
			id: <input type="text" name="id" value=""/> <br />
			amount: <input type="text" name="amount" value=""/> <br />
			price: <input type="text" name="price" value=""/> <br />
			day: <input type="text" name="day" value=""/> <br />
			<input type="submit" name="req" value="aggiungiArticolo"/><br />
			<input type="submit" name="req" value="concludi"/><br />
		</form>	
		<br />
		
		
		Elenco articoli selezionati:
		<ul>
		<li>id, amount, price, day </li>
		<%
			Vector<Articolo> articoliSelezionati = merceSelezionata.getMerce();
			for( int i=0; i<articoliSelezionati.size(); i++){
				Articolo ar = articoliSelezionati.elementAt(i); 
				%>
				<li><%= ar.getId() %>, <%= ar.getAmount() %>, <%= ar.getPrice() %>, <%= ar.getDay() %></li>
				<%
			}
		%>
		</ul>
		<br />
		<br />
		Elenco articoli venduti:
		<ul>
		<li>id, amount, price, day </li>
		<%
			Vector<Articolo> articoliVenduti = merceVenduta.getMerce();
			for( int i=0; i<articoliVenduti.size(); i++){
				Articolo ar = articoliVenduti.elementAt(i); 
				%>
				<li><%= ar.getId() %>, <%= ar.getAmount() %>, <%= ar.getPrice() %>, <%= ar.getDay() %></li>
				<%
			}
		%>
		</ul>
		
		
	</body>
</html>
