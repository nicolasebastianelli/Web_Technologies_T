<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
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
<jsp:useBean id="listaop"  class="beans.ListaOp" scope="application"></jsp:useBean>
<%!
Gson gson = new Gson();
%>
<% if(request.getSession().getAttribute(LoginInfo.LOGIN_INFO)==null){
	%>
	<jsp:forward page="/login.jsp"></jsp:forward>
	<%
}
else{
	Operazione op = gson.fromJson((String) request.getAttribute("operation"), Operazione.class);
	if(op!=null){
	if (op.getOperator()=='x'){
		op.setRes(op.getOp1()*op.getOp2());
	}
	else {
		op.setRes(op.getOp1()+op.getOp2());
	}
	LoginInfo info = (LoginInfo)request.getSession().getAttribute(LoginInfo.LOGIN_INFO);
	if(! listaop.getListaop().containsKey(info.getCouple())){
		Set<Operazione> op2 = new HashSet<Operazione>();
		op2.add(op);
		listaop.getListaop().put(info.getCouple(), op2);
	}
	else{
		listaop.getListaop().get(info.getCouple()).add(op);
	}
	}
	%>
	<jsp:forward page="/calcola.jsp?calc=null"></jsp:forward>
	<%
}
%>
</body>
</html>