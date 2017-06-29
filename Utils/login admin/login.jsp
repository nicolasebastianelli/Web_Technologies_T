<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.Bean"%>
<%@ page import="beans.Beans"%>
<%@ page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="Author" content="Nicola Sebastianelli">
		<title>Conteggi Globali</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"/>	
</head>
<body>
<jsp:useBean id="beansApplication" class="beans.Beans" scope="application" />
<a href="page.jsp">Vai a page</a>
<%
if(request.getParameter("logout")!=null && request.getParameter("logout").equals("logout")){
	request.getSession().setAttribute("login", null);
}
if(request.getParameter("user")!=null && request.getParameter("password")!=null && request.getParameter("log")!=null && request.getParameter("log").equals("LogIn")){
if(request.getParameter("user").equals("admin")&& request.getParameter("password").equals("admin"))
    request.getSession().setAttribute("login",request.getParameter("user"));
}

if(request.getSession().getAttribute("login")!=null){
	%>
	<a style="float:right"href="?logout=logout">Esci</a>
	<h1>Titolo</h1>
	<fieldset>
		<%
		int index=1;
			if(beansApplication.getBeans().size()!=0)
				for(Bean bean : beansApplication.getBeans()){
		%>
                    <p>- Elemento <%=index %>:</p>
					<p>  Param: <%=bean.get %></p>
					<p>  Param: <%=bean.get %></p>
					<p>  Param: <%=bean.get %></p>
					<p>  Parama: <%=bean.get %></p>
			<% index++;
			}					
				}
					else{
			%>
			<h4>Nulla trovato</h4>
	<%
		}
	}
else{
%>

<h1>Login</h1>
		<fieldset>
			<form method="post" action="admin.jsp">
				<label>User: </label><input type="text" name="user" ><br><br>
				<label>Password: </label><input type="text" name="password" ><br><br>
				<input type="submit" name="log" value="LogIn">
			</form>
		</fieldset>
<%
}
%>
</body>
</html>
