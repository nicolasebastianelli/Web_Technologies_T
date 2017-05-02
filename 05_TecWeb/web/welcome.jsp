<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
	<head>
		<title>Javascript, DOM and DHTML events</title>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
	</head>

	<frameset rows="10%,70%,20%">
	
		<frame name="header"
			src="<%=request.getContextPath()%>/fragments/header.jsp">
	
		<frameset cols="25%,75%">
			<frame name="list"
				src="<%=request.getContextPath()%>/fragments/menu.jsp" />
			<frame name="content" src="<%=request.getContextPath()%>/pages/whoYouAre.html"/>
		</frameset>
	
		<frame name="footer"
			src="<%=request.getContextPath()%>/fragments/footer.jsp">
	
		<noframes>
		Your browser does not support frames. Click
		<a href="<%=request.getContextPath()%>/fragments/menu.jsp">here</a>
		for the list of available pages.
		</noframes>
	</frameset>

</html>
