/**
 * Funzione di callback esterna, 
 * non definita in linea durante l'assegnamento ad onreadystatechange.
 * 
 * Sostituzione dell'innerHTML di un elemento
 * con il contenuto testuale disponibile al completamento della
 * richiesta AJAX.
 *
 * Nota: esempi, tutorial e snippet di codice in rete spesso chiamano 
 * la richiesta XmlHttpRequest semplicemente come 'oggetto ajax'
 */
function substituteInnerHTML(ajax, theElement) {

	// designiamo la formattazione della zona dell'output
	theElement.class = "content";

	// verifica dello stato
	if ( ajax.readyState === 2 ) {
	    	theElement.innerHTML = "Richiesta inviata...";
	} // if 2

	else if ( ajax.readyState === 3 ) {
    		theElement.innerHTML = "Ricezione della risposta...";
	} // else if 3

	else if ( ajax.readyState === 4 ) {

		// verifica della risposta da parte del server
	        if ( ajax.status === 200 ) {
	        	// operazione avvenuta con successo
	        	theElement.innerHTML = ajax.responseText;
	        } // if 200
        
	        else {
	        	// errore di caricamento
	        	theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
	        	theElement.innerHTML += "Errore riscontrato: " + ajax.statusText;
	        } // else (if ! 200)

	}// else if 4 
    
}// substituteInnerHTML()



/*
 * Imposta il contenuto testuale disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX per browser legacy
 */
function caricaTestoIframe(theUri,theHolder) {
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
}// caricaTestoIframe()



/*
 * Imposta il contenuto testuale disponibile presso theUri
 * come html interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function caricaTestoAJAX(theUri, theElement, theXhr) {
    
	// impostazione della funzione di callback
	theXhr.onreadystatechange = function() {
		// VA INVOCATA IN QUESTO MODO AFFINCHE' I RIFERIMENTI
		// AI SUOI ARGOMENTI NON VADANO PERSI !!!!
		substituteInnerHTML(theXhr, theElement);
	};

	// impostazione richiesta asincrona in GET
	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// sostituzione dell'header "connection" (default = "keep alive")
	theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(null);

}// caricaTestoAJAX()



/*
 * Scarica un contenuto testuale dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function caricaTesto(uri,e) {

	// variabili di funzione
	var
		// assegnazione oggetto XMLHttpRequest
		xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		caricaTestoAJAX(uri,e,xhr); 
	else 
		caricaTestoIframe(uri,e); 

}// caricaTesto()
