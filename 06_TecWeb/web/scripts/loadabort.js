/*
 * In attesa del caricamento del contneuto AJAX
 * collochiamo una immagine di attesa al suo posto,
 * magari animata, per intrattenere l'utente
 */
function aggiungiImmagineAttesa( theElement ) {

	// variabili di funzione
	var 
		img_wait = myGetChildByName( theElement, "wait");

	// ovviamente lo facciamo solo una volta, 
	// successive richieste di aggiungere l'immagine sono ignorate
	if ( ! img_wait ) {
		// Effetto animato per ingannare l'attesa
		img_wait = document.createElement('img');
		img_wait.src = "images/wait.gif";
		img_wait.alt = "Attesa";
		img_wait.name = "wait";
		theElement.appendChild(img_wait);
	}

}// aggiungiImmagineAttesa()



/*
 * Ad attesa terminata rimuoviamo l'immagine di intrattenimento
 */
function rimuoviImmagineAttesa( theElement ) {

	// variabili funzione
	var 
		// recuperiamo l'elemento immagine
		img_wait = myGetChildByName( theElement, "wait");

	// se esiste lo rimuoviamo	
	if ( img_wait )
		// img_wait_to_remove.parentNode.removeChild(img_wait_to_remove);
		theElement.removeChild(img_wait);

}// rimuoviImmagineAttesa()



/*
 * In caso di timeout 
 * inseriamo un messaggio di fallimento al posto del contenuto atteso
 * e rimuoviamo eventuali intrattenitori
 */
function aggiungiMessaggioFallimento( theElement ) {

	// creiamo un elemento per avvertire l'utente
	// del fallimento della richiesta da aggiungere
	// a quello predisposto per mostrare il risultato.
	// Usiamo il metodo createElement() del document e
	// non innerHTML,che potrebbe riscrivere il link selezionato
	// annullando l'assegnazione del parametro fittizio.
	var sorry = myGetChildByName( theElement, "sorry");

	// ovviamente evitiamo messaggi duplicati
	if ( ! sorry ) {
		sorry = document.createElement("p");
		sorry.name = "sorry";
		sorry.innerHTML = 
			"Spiacente, richiesta fallita.<br />" +
			"Si prega di ritentare tra qualche istante.";
		theElement.appendChild(sorry);
		// e rimuoviamo l'immagine di intrattenimento, visto che l'attesa è finita (male)
		rimuoviImmagineAttesa( theElement )
	}

}// aggiungiMessaggioFallimento()



/*
 * Gestiamo l'attesa associando a ogni richiesta AJAX una funzione che
 * a intervalli di tempo prefissati ne verifica il completamento 
 * e che la abortisce in caso di superamento del tempo massimo di attesa
 */
function gestisciAttesa( ajax, theElement, inizioChiamata, massimaAttesa ) {

	// un solo gestore per richiesta
	if ( ajax.gestita ) 
		// non siamo noi, e' gia qualcun altro. restituiamo il controllo
		return;
	else 
		// lo faremo noi
		ajax.gestita = true;  

	// immagine per l'attesa
	aggiungiImmagineAttesa( theElement );

	// il controllo sul tempo trascorso deve essere
	// asincrono a questa funzione poiche' non e' detto
	// che il cambio di stato della richiesta
	// venga effettuato in tempi utili.
	// Una funzione apposita per la verifica
	// e' la soluzione piu' indicata
	verificaTempoTrascorso = function() {

		// se la richiesta si e' conclusa non eseguo
		if ( ajax.readyState == 4 ) {
			rimuoviImmagineAttesa( theElement );
			return;
		}

		// ogni chiamata asincrona a questa funzione
		// dovra'� verificare la durata dell'interazione
		// e' necessario quindi ridichiarare la variabile
		// al fine di ottenere il nuovo oggetto Date
		dataChiamata = new Date();

		// Se il tempo trascorso e' maggiore della massima attesa ...
		if((dataChiamata.getTime() - inizioChiamata) > massimaAttesa) {

			// ... interrompiamo la richiesta ed
			// informarmiamo l'utente di quanto avvenuto.

			// Quindi riassegnamo onreadystatechange ad una
			// funzione vuota, poiche' quest'evento sara'�
			// sollevato chiamando il metodo abort()
			ajax.onreadystatechange = function(){
				// se stiamo usando Firebug il browser fornisce l'oggetto console!
				if ( console )
					// possiamo scrivere qui messaggi di trace e di informazioni per lo sviluppo
					// con diversi livelli di gravita' (filtrabili, per non morire tra gli output!)
					//console.debug("Richiesta AJAX abortita"); // granularità piu' fine
					console.info("Richiesta AJAX abortita"); // messaggi informativi generici
					//console.warn("Richiesta AJAX abortita"); // avvisi di potenziali criticita'
					//console.error("Richiesta AJAX abortita"); // messaggi di errori
				return;
			};

			// e' possibile a questo punto richiamare il metodo abort
			// ed annullare le operazioni dell'oggetto XMLHttpRequest
			ajax.abort();

			// comunicazione del fallimento
			aggiungiMessaggioFallimento( theElement );
       
		} // if massima attesa superata

		// se invece il tempo è inferiore al timeout
		else {
			// si richiama questa stessa funzione, con un tempo
			// che non dovrà essere ne alto ne troppo basso.
			setTimeout(verificaTempoTrascorso, 100);
		}
	}

	// definita la funzione non resta che avviarla
	verificaTempoTrascorso();

} // gestisciAttesa()



/*
 * La solita funzione di callback
 */
function callback( theXhr, theElement ) {

	// Questa volta gestiamo anche l'attesa, non solo il risultato
	gestisciAttesa( theXhr, theElement, new Date().getTime(), 5000 );

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
       			theElement.innerHTML = '<p>'+theXhr.responseText+'</p>';
	        }// if 200

	        else {
	        	// errore di caricamento
	        	theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
	        	theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
	        }// if ! 200

	}// if 4

}// callback()



/*
 * Caricamento versione non AJAX
 */
function caricaParagrafoIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="'+theUri+'">Il tuo browser non supporta i frame</iframe>';
} // caricaParagrafoIframe()



/*
 * Caricamento versione AJAX
 */
function caricaParagrafoAJAX(theUri, theElement, theXhr) {
    
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

} // caricaParagrafoAJAX()



/*
 * Funzione di caricamento testo
 * che astrae i casi AJAX e non AJAX
 */
function caricaParagrafo(uri,e) {

	// variabili di funzione
	var
		// assegnazione oggetto XMLHttpRequest
		xhr = myGetXmlHttpRequest();

	if ( xhr ) { 
		caricaParagrafoAJAX(uri,e,xhr);
	}
	else { 
		caricaParagrafo(uri,e); 
	}

} // caricaParagrafo()
