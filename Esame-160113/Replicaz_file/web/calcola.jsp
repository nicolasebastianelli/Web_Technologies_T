<%@page import="beans.Operazione"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="beans.LoginInfo"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.*"%>
<script type="text/javascript" src="scripts/ajax.js"></script>
<script type="text/javascript" src="scripts/json.js"></script>
<script type="text/javascript" src="scripts/utils.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Author" content="Nicola Sebastianelli">
<title>Admin</title>
<link type="text/css" href="styles/default.css" rel="stylesheet" />
</head>
<body>
<%!
Gson gson = new Gson();
%>
<jsp:useBean id="listaop"  class="beans.ListaOp" scope="application"></jsp:useBean>
<% if(request.getSession().getAttribute(LoginInfo.LOGIN_INFO)==null)
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
else{
	if(request.getParameter("calc")!=null && request.getParameter("op1")!=null 
			&& request.getParameter("operazione")!=null&& request.getParameter("op2")!=null
			&& request.getParameter("calc").equals("calc"))
	{
		int op1 = Integer.parseInt(request.getParameter("op1"));
		int op2 = Integer.parseInt(request.getParameter("op2"));
		char operazione= request.getParameter("operazione").charAt(0);
		Operazione op = new Operazione(op1,op2,operazione);
		request.setAttribute("operation", gson.toJson(op));
		getServletContext().getRequestDispatcher("/calcolatrice.jsp").forward(request, response);
	}
		
%>
		<h1>Calcola</h1>
	<fieldset>
		<form method="get">
			<label>Operatore1: </label><input type="text" id="op1" name="op1"  onkeyup="check(myGetElementById('op1'))"><br>
			<label>Operando: </label><input type="radio" checked="checked" name="operazione" value="x">x  <input type="radio"  name="operazione" value="+">+<br>
			<label>Operatore2: </label><input type="text" id="op2" name="op2" onkeyup="check(myGetElementById('op2'))"><br>
			<br> <input type="submit" name="calc" value="calc">
		</form>
	</fieldset>
	<div id="output"></div>
	<%
	if(request.getSession().getAttribute(LoginInfo.LOGIN_INFO)!=null && listaop.getListaop() != null){
	LoginInfo info =(LoginInfo)request.getSession().getAttribute(LoginInfo.LOGIN_INFO);
	if (listaop.getListaop().get(info.getCouple())!= null){
	for(Operazione op : listaop.getListaop().get(info.getCouple())){
		%>
		<label><%=op.getOp1() %> <%=op.getOperator() %> <%=op.getOp2() %> = <%=op.getRes() %></label><br>
		<%
	}
	}
	}
}	
%>
</body>
</html>