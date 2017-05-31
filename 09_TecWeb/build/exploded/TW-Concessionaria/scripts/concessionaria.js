
function checkTarga( targa, results ){
	
}

function parsificaJSON( jsonText ) {
	
	var macchina = JSON.parse(jsonText);
	var risultato = 'macchina.modello  = ' + macchina.modello  + ', macchina.colore = ' + macchina.colore;
    return risultato;

}// parsificaJSON()


/*
 * Funzione di callback
 */
function concessionariaCallback( theXhr, targa, resDiv ) {

	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
    	// non faccio niente
    	// theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    	// non faccio niente
		// theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
	        if ( theXhr.status === 200 ) {
	        	// operazione avvenuta con successo	
		        if ( theXhr.responseText ) {
		        	var li = resDiv.childNodes;
		        	if( li.length == 3 ){
		        		resDiv.removeChild(li[2]);
		        	}
		        		//resDiv.innerHTML = ... 
		        		resDiv.innerHTML =
		        		'<li>'+ 'targa = '+targa+', '
		        		+parsificaJSON(theXhr.responseText) +
		        		'</li>'+ resDiv.innerHTML;

				}
				else {
			    	// non faccio niente
				}
	        }
	        else {
	        	// errore di caricamento
	        	// non faccio niente nemmeno qui
	        }
	}// if 4
} // prodottoCallback();



/*
 * Usa tecniche AJAX attraverso la XmlHttpRequest fornita in theXhr
 */
function eseguiConcessionariaAJAX(uri, targa, resDiv, xhr){ 
    
	// impostazione controllo e stato della richiesta
	xhr.onreadystatechange = function() { concessionariaCallback(xhr, targa, resDiv); };

	// impostazione richiesta asincrona in GET del file specificato
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

} // eseguiProdottoAJAX()



function eseguiConcessionaria(uri, targa, resDiv) {

	// assegnazione oggetto XMLHttpRequest
	var xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		eseguiConcessionariaAJAX(uri, targa, resDiv, xhr); 

}// eseguiProdotto()


function deleteSpecialChar(targa , element){
	var targaval=targa.value.toLowerCase();
	var lastchar= targaval[targaval.length-1];
	if(lastchar<'a'||lastchar>'z'){
		if(lastchar>'9'||lastchar<'0')
			targaval=targaval.substring(0,targaval.length-1);
	}
	targa.value=targaval;
	if(targaval.length==7){
		eseguiConcessionaria("cercaMacchine?targa="+targaval,targaval,element);
		targa.value="";
	}
	
}
