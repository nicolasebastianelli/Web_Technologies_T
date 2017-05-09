<?xml version='1.0' encoding='ISO-8859-1'?>
<%@ page errorPage="../errors/failure.jsp"%>
<%@ page session="false"%>
<%@ page import="it.unibo.tw.web.beans.Feed"%>
<%@ page import="it.unibo.tw.web.beans.FeedDb"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<jsp:useBean id="feedDb" class="it.unibo.tw.web.beans.FeedDb"  scope = "application"/>
<%
	// recupero il tipo di categoria cercata dai parametri della richiesta
	String category = request.getParameter("category");
	String firstFeed;
	// recupero i feed corrispondenti da database
	try{
		firstFeed = feedDb.getCategories(category).get(0);
	}
	catch(Exception e){
		firstFeed="";
	}
	if (category.equals(""))
	{
		firstFeed="";
	}
%>
<%
response.setHeader("Content-Type","application/xml");
%>
<categoria><%= firstFeed %></categoria>