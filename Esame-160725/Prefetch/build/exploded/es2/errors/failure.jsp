<%@ page contentType="text/html; charset=US-ASCII" %>
<%@ page isErrorPage="true" %>

<html>
	<head>
		<meta name="Author" content="Serafini Filippo">
		<title>Error JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>
	
	<body>	
		<div id="err" class="clear">
			<p>
				<b>An exception was raised!</b><br/>
				<%= exception.toString() %>
			</p>
			<p>
				<b>Exception message is:</b><br/>
				<%= exception.getMessage() %>
			</p>
			<p>
				<b>Stacktrace is:</b><br/>
				<%
				exception.printStackTrace(new java.io.PrintWriter(out));
				exception.printStackTrace();
				%>
			</p>
		</div>	
	</body>
</html>
