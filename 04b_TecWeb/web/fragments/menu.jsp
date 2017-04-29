<div id="menu">
	<ul id="tabs">
		<li <%= request.getRequestURI().contains("home") ? "style=\"background-color: #aaa;\"" : ""%>>
			<a href="<%= request.getContextPath() %>/pages/home.jsp">Home</a>
		</li>
		<li <%= request.getRequestURI().contains("catalogue") ? "style=\"background-color: #aaa;\"" : ""%>>
			<a href="<%= request.getContextPath() %>/pages/catalogue.jsp">Manage catalogue</a>
		</li>
		<li <%= request.getRequestURI().contains("cart") ? "style=\"background-color: #aaa;\"" : ""%>>
			<a href="<%= request.getContextPath() %>/pages/cart.jsp">Manage cart</a>
		</li>
		<li <%= request.getRequestURI().contains("checkout") ? "style=\"background-color: #aaa;\"" : ""%>>
			<a href="<%= request.getContextPath() %>/pages/checkout.jsp">Checkout</a>
		</li>
	</ul>
</div>