<html>
	<head>
		<meta name="Author" content="pisi79">
		<title>Home JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
	
		<div id="main" class="clear">
			<p>Welcome to the 'TemplateJSP'project.</p>
			<br/>
			<p>You can have lot of fun, here!</p>
		</div>
	
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
