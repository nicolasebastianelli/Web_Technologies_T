<%@page import="org.jabsorb.JSONSerializer"%>
<%@page import="beans.ImuCF"%>

<%
		JSONSerializer serializer = new JSONSerializer();
		try {
			// inizializza i tipi serializzatori forniti di default 
			serializer.registerDefaultSerializers(); 
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		float rendita = Float.parseFloat(request.getParameter("rendita"));
		String cf = request.getParameter("cf");
		float imu = rendita * 160 * 0.004F;
		
		ImuCF res = new ImuCF( cf,imu);
%>

<%= serializer.toJSON(res) %>
