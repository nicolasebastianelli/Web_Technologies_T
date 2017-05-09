/*
 * Funzione per estrarre il contenuto CDATA presente
 * all'interno di un nodo XML 
 * [ad esempio su <guida>ajax</guida> restituisce solo ajax].
 *
 * Utile a far rimanere leggibile il codice di parsificaXml() 
 */
function leggiContenuto(item, nomeNodo) {
	return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
};

/*
 * Funzione che genera una lista XHTML 
 * con gli item presi dal testo RSS (linguaggio basato su xml)
 * ricevuto come argomento xml
 */
function parsificaXml( xml ) {

	// variabili di funzione
	var

	// Otteniamo la lista degli item dall'RSS 2.0 di edit
	items = xml.getElementsByTagName("item"),

	// Predisponiamo una struttura dati in cui memorrizzare le informazioni di interesse
	itemNodes = new Array(),

	// la variabile di ritorno, in questo esempio, e' testuale
	risultato = "";

	// ciclo di lettura degli elementi
	for (    var a = 0, b = items.length;    a < b;   a++   ) {
		// [length al posto di push serve per evitare errori con vecchi browser]
		itemNodes[a] = new Object();
		itemNodes[a].title = leggiContenuto(items[a],"title");
		itemNodes[a].description = leggiContenuto(items[a],"description");
		itemNodes[a].link = leggiContenuto(items[a],"link");
	}// for ( items )

	// non resta che popolare la variabile di ritorno
	// con una lista non ordinata di informazioni

	// apertura e chiusura della lista sono esterne al ciclo for 
	// in modo che eseguano anche in assenza di items
	risultato = "<ul>";

	for( var c = 0; c < itemNodes.length; c++ ) {
		risultato += '<li class="item"><strong>' + itemNodes[c].title +'</strong><br/>';
		risultato += itemNodes[c].description +"<br/>";
		risultato += '<a href="' + itemNodes[c].link + '">approfondisci</a><br/></li>';
	};

	// chiudiamo la lista creata
	risultato += "</ul>";

	// restituzione dell'html da aggiungere alla pagina
	return risultato;

}// parsificaXml()



/*
 * Funzione di callback
 */
function callback( theXhr, theElement ) {

	// designiamo la formattazione della zona dell'output
	theElement.class = "content";

	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
		theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
		theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			if ( theXhr.responseXML ) {
				theElement.innerHTML = parsificaXml(theXhr.responseXML);
			}// if XML
			else {
				// visualizzazione contenuto letto
				// evitando di scrivere la risposta 
				// in modo interpretabile dal browser
				theElement.innerHTML = "L'XML restituito dalla richiesta non e' valido.<br />" +
				theXhr.responseText.split('<').join("&lt;").split('>').join("&gt;");
			}// else (if ! XML)
		}// if 200

		else {
			// errore di caricamento
			theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
			theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
		}// else (if ! 200)
	}// if 4

} // callback();



/*
 * Imposta il contenuto disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX per browser legacy
 */
function caricaFeedIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function caricaFeedAJAX(theUri, theElement, theXhr) {

	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr, theElement); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(null);

} // caricaFeedAJAX()



/*
 * Scarica un contenuto xml dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function caricaFeed(uri,e) {

	// variabili di funzione
	var
	// assegnazione oggetto XMLHttpRequest
	xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		caricaFeedAJAX(uri,e,xhr); 
	else 
		caricaFeedIframe(uri,e); 

}// caricaFeed()
function caricaSuggerimenti(uri,e){

	var xhr = myGetXmlHttpRequest();

	xhr.onreadystatechange = function() { callbackCategoria(xhr, e); };

	try {
		xhr.open("get", uri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	xhr.setRequestHeader("connection", "close");

	// invio richiesta
	xhr.send(null);


}

function callbackCategoria( theXhr, theElement ) {

	// designiamo la formattazione della zona dell'output
	theElement.class = "content";

	if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			body=theXhr.responseXML.getElementsByTagName("categoria")[0].childNodes[0].nodeValue;
			if(body != "")
				theElement.value = body;
		}// if XML
	}// if 200

}

function doSomething(event){
	var x = event.keyCode;
	if(x!=8){
		caricaSuggerimenti("contents/suggerimento.jsp?category="+myGetElementById('category').value,  myGetElementById('category'));
	}
}
