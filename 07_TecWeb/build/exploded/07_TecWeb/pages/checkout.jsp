<%@page import="jdk.nashorn.internal.runtime.ListAdapter"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page import="it.unibo.tw.web.beans.Catalogue"%>
<%@ page import="it.unibo.tw.web.beans.Cart"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<%@page import="java.util.*" %>
<%@ page session="true"%>
<%! double total(Cart cart) {
	double total = 0;
	for ( Item item : cart.getItems() ) {
		total += item.getPrice() * cart.getOrder(item);
	}
	return total;
}
 %>
<html>
	<head>
		<meta name="Author" content="pisi79">
		<title>Checkout JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
	
		<jsp:useBean id="cart" class="it.unibo.tw.web.beans.Cart" scope="session" />
		<div id="main" class="clear">
		<form action="<%= request.getContextPath() %>/checkoutServlet" method="post">
			<div id="center" style="float: center; width: 80%">
			<% List<Cart> listacarrelli = (List<Cart>)request.getSession().getAttribute("listacarrello");
			Item[] cartItems = cart.getItems().toArray(new Item[0]);
			if(listacarrelli==null){ %>
				<p>Items in cart:</p>
				<table class="formdata">
					<tr>
						<th style="width: 33%">Id</th>
						<th style="width: 33%">Description</th>
						<th style="width: 33%">Price</th>
						<th style="width: 33%">Your order</th>
					</tr>
					<% 
					
					for( Item aCartItem : cartItems ){  
					%> 
						<tr>
							<td><%= aCartItem.getId() %></td>
							<td><%= aCartItem.getDescription() %></td>
							<td><%= aCartItem.getPrice() %> &#8364;</td>
							<td><%= cart.getOrder(aCartItem) %></td>
						</tr>
					<% 
					} 
					%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<br/>
				<p>
				Total: <span style="font-size: x-large; color: red;"><%= total(cart) %> &#8364;</span>
				</p>
				<br>
				<%}	else{
						for(Cart carrello : listacarrelli){
				%>	
				<table class="formdata">
				<tr>
						<th>
				 		<h1>Mail: <%=carrello.getMail()%></h1>
				 		</th>
				</tr>
					<tr>
						<th style="width: 33%">Id</th>
						<th style="width: 33%">Description</th>
						<th style="width: 33%">Price</th>
						<th style="width: 33%">Your order</th>
					</tr>
					<% 
					cartItems = carrello.getItems().toArray(new Item[0]);
					for( Item aCartItem : cartItems ){  
					%> 
						<tr>
							<td><%= aCartItem.getId() %></td>
							<td><%= aCartItem.getDescription() %></td>
							<td><%= aCartItem.getPrice() %> &#8364;</td>
							<td><%= carrello.getOrder(aCartItem) %></td>
						</tr>
					<% 
					} 
					%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>	
				<% 
					}
				}
				%>
				
				
				<p> Shipment information (* = mandatory): </p>
				<label>Email(*)</label>
				<input type="text" name="mail" value=""/>
				<br>
				<br>
				<input type="submit" name="confirm" value="Confirm order"/>
				<input type="submit" name="confirm" value="Show order"/>
				<input type="submit" name="confirm" value="Show all"/>
			</div>
			</form>	
		</div>
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
