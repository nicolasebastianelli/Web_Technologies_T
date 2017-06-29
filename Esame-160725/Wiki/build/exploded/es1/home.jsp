<%@page import="com.google.gson.Gson"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="true"%>
<%@ page import="java.util.List"%>
<script type="text/javascript" src="scripts/ajax.js"></script>
<script type="text/javascript" src="scripts/json.js"></script>
<script type="text/javascript" src="scripts/utils.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Author" content="Nicola Sebastianelli">
<title>Modifica Capitoli</title>
<link type="text/css" href="styles/default.css" rel="stylesheet" />
</head>
<body>
<%! Gson gson = new Gson(); %>
<%
String res =(String)request.getAttribute("libro");
String original = gson.fromJson(res, String.class);
if(res==null)
	res=gson.toJson("");
String cap = request.getParameter("cap");
if(cap==null)
	cap="1";
%>
	<h1>Modifica Capitoli</h1>
	<fieldset>
		<form method="get" action="servlet">
			<label>Capitolo: </label><input type="text" name="capitolo" id="capitolo" value=<%=cap %> required onkeyup="checkVal(myGetElementById('capitolo'))"><br>
			<br> <label>Contenuto: </label><textarea cols="4" rows="4" name="contenuto"><%= gson.fromJson(res, String.class) %></textarea>
			<textarea cols="4" rows="4" style="display:none" name="original"><%= original%></textarea>
			<input type="submit" name="cerca" value="cerca"><br>
			<br> <input type="submit" name="inserisci" value="inserisci">
		</form>
	</fieldset>
</body>
</html>
