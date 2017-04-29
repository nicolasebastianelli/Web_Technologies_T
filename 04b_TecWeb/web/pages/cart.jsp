<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Catalogue"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<%@ page import="it.unibo.tw.web.beans.Cart"%>
<%@ page import="java.util.List"%>

<!-- metodi richiamati nel seguito -->
<%!void add(Catalogue catalogue,Cart cart, Item item) {

		boolean alreadyInCatalogue = false;

		for (Item itemInCart : cart.getItems()) {
			if (itemInCart.getDescription().equals(item.getDescription())) {
				alreadyInCatalogue = true;
				for (Item itemInCatalogue : catalogue.getItems()) {
					if (itemInCatalogue.getDescription().equals(itemInCart.getDescription())) {
						if(itemInCatalogue.getQuantity()>0){
							itemInCatalogue.setQuantity(itemInCatalogue.getQuantity() - 1);
							itemInCart.setQuantity(itemInCart.getQuantity() + 1);
						}
					}
				}
				break;
			}
		}

		if (!alreadyInCatalogue) {
			int nelem = cart.getItems().size();
			for (Item itemInCatalogue : catalogue.getItems()) {
				if (itemInCatalogue.getDescription().equals(item.getDescription())) {
					itemInCatalogue.setQuantity(itemInCatalogue.getQuantity() - 1);
				}
			}
			cart.put(item, nelem+1);

		}

	}

	void removec(Catalogue catalogue,Cart cart, String description) {
		for (Item itemInCart : cart.getItems()) {
			if (itemInCart.getDescription().equals(description)) {
				itemInCart.setQuantity(itemInCart.getQuantity() - 1);
				for (Item itemInCatalogue : catalogue.getItems()) {
					if (itemInCatalogue.getDescription().equals(itemInCart.getDescription())) {
						itemInCatalogue.setQuantity(itemInCatalogue.getQuantity() + 1);
					}
				}
			}
			if (itemInCart.getQuantity() == 0) {
				cart.getItems().remove(itemInCart);
				break;
			}
		}

	}%>

<!-- codice html restituito al client -->
<html>
<head>
<meta name="Author" content="pisi79">
<title>Cart JSP</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/default.css"
	type="text/css" />
</head>

<body>

	<%@ include file="../fragments/header.jsp"%>
	<%@ include file="../fragments/menu.jsp"%>

	<div id="main" class="clear">

		<jsp:useBean id="catalogue" class="it.unibo.tw.web.beans.Catalogue"
			scope="application" />
		<jsp:useBean id="cart" class="it.unibo.tw.web.beans.Cart"
			scope="session" />

		<%
			String description = request.getParameter("description");

			if (description != null && !description.equals("")) {

				if (description.contains(" ")) {
					throw new Exception("Blanks are not allowed in the description field!");
				}

				if (request.getParameter("add") != null && request.getParameter("add").equals("ok")) {
					Item item = new Item();
					item.setDescription(description);
					item.setPrice(Double.parseDouble(request.getParameter("price")));
					item.setQuantity(1);
					add(catalogue,cart, item);
				} else if (request.getParameter("remove") != null && request.getParameter("remove").equals("ok")) {
					removec(catalogue, cart, description);
				}

			}
		%>
		<div id="left"
			style="float: left; width: 48%; border-right: 1px solid grey">

			<p>Current catalogue:</p>
			<table class="formdata">
				<tr>
					<th style="width: 31%">Description</th>
					<th style="width: 31%">Price</th>
					<th style="width: 31%">Available quantity</th>
					<th style="width: 7%"></th>
				</tr>
				<%
					Item[] items = catalogue.getItems().toArray(new Item[0]);
					for (Item anItem : items) {
				%>
				<tr>
					<td><%=anItem.getDescription()%></td>
					<td><%=anItem.getPrice()%> &#8364;</td>
					<td><%=anItem.getQuantity()%></td>
					<td><a
						href="?add=ok&description=<%=anItem.getDescription()%>&price=<%=anItem.getPrice()%>">ADD</a>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div id="right" style="float: right; width: 48%;">

			<p>Current Cart:</p>
			<table class="formdata">
				<tr>
					<th style="width: 31%">Description</th>
					<th style="width: 31%">Price</th>
					<th style="width: 31%">Selected quantity</th>
					<th style="width: 7%"></th>
				</tr>
				<%
					Item[] cartitems = cart.getItems().toArray(new Item[0]);
					for (Item anItem : cartitems) {
				%>
				<tr>
					<td><%=anItem.getDescription()%></td>
					<td><%=anItem.getPrice()%> &#8364;</td>
					<td><%=anItem.getQuantity()%></td>
					<td><a
						href="?remove=ok&description=<%=anItem.getDescription()%>">
							<img src="../images/remove.gif" alt="remove" />
					</a></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>

		</div>

		<div class="clear">
			<p>&nbsp;</p>
		</div>

	</div>

	<%@ include file="../fragments/footer.jsp"%>

</body>
</html>
