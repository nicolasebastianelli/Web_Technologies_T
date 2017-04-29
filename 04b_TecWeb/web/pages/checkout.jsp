<%@ page errorPage="../errors/failure.jsp"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<%@ page import="it.unibo.tw.web.beans.Cart"%>
<%@ page import="java.util.List"%>
<html>
<head>
<meta name="Author" content="pisi79">
<title>Checkout JSP</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/styles/default.css"
	type="text/css" />
</head>

<body>

	<%@ include file="../fragments/header.jsp"%>
	<%@ include file="../fragments/menu.jsp"%>
	<jsp:useBean id="cart" class="it.unibo.tw.web.beans.Cart"
		scope="session" />
	<%! 
		void confirm(Cart cart) {	
			for (Item itemInCart : cart.getItems()) {
				cart.getItems().remove(itemInCart);
			}
		}
	%>
	<div id="main" class="clear">
		<div id="left"
			style="float: left; width: 48%; border-right: 1px solid grey">
			<%
			if ( request.getParameter("confirm") != null && request.getParameter("confirm").equals("submit item") ) {
				confirm(cart);
			}
			%>
			<p>Current Cart:</p>
			<table class="formdata">
				<tr>
					<th style="width: 33%">Description</th>
					<th style="width: 33%">Price</th>
					<th style="width: 33%">Selected quantity</th>
				</tr>
				<%
					Item[] cartitems = cart.getItems().toArray(new Item[0]);
					for (Item anItem : cartitems) {
				%>
				<tr>
					<td><%=anItem.getDescription()%></td>
					<td><%=anItem.getPrice()%> &#8364;</td>
					<td><%=anItem.getQuantity()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div id="right"
			style="float: right; width: 48%; border-right: 1px solid grey">

			<p>Checkout:</p>
			<form>
			<table class="formdata">
				<tr>
					<th style="width: 50%">Description</th>
					<th style="width: 50%">Total Price</th>
				</tr>
				<%
					for (Item anItem : cartitems) {
				%>
				<tr>
					<td><%=anItem.getDescription()%> x <%=anItem.getQuantity()%></td>
					<td><%=anItem.getPrice()*anItem.getQuantity()%> &#8364;</td>
				</tr>
				<%
					}
				%>
				<tr>
					<th colspan="2" text-align="center"><b>Total:</b></th>
				</tr>
				<tr>
					<td colspan="2" text-align="center"><b> <%
						double totalp=0 ;
						for (Item anItem : cartitems) {
							totalp = totalp +anItem.getPrice()*anItem.getQuantity();
						}
					%> <%=totalp%>&#8364;
					</b></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" name="confirm" value="submit item" style="width:100%"/>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>

	<%@ include file="../fragments/footer.jsp"%>

</body>
</html>
