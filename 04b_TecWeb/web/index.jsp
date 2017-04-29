<%@ page session="true"%>
<html>
   <head>
      <title>Start Web Application</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
  		<meta http-equiv="refresh" content="0;URL=stillAServlet">
   </head>
   <body>

      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the Servlet starts up the environment,
      upon the first request.
      This message avoid letting the user linger without knowing what's going on.
      -->
 
      <p>
      	Please wait for the web application to start... &nbsp;
		<img alt="wait" title="wait" src="images/wait.gif"/>
      </p>

   </body>
</html>

<!-- 
Diversamente dall'effetto ottenuto con gli attributi http-equiv, 
questo redirect avviene lato server, prima che il flusso di output 
sia restituito al client: il messaggio di attesa non e' visibile!
-->


