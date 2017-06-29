var Mapping= new Object();

function callback(xhr, element,input) {
	element.class = "content";
	if (xhr.readyState === 4) {
		if (xhr.status === 200) {
			if(xhr.responseText){
				var res=xhr.responseXML;
				if(res!=""){
					Mapping[input]=res;
					element.innerHTML = "File: "+input+".xml trovato elemento inserito";
					element.hidden=false;
				}
			}
		}
		else {

		}
	}
}


function eseguiAJAX(uri, element, xhr,input) {
	xhr.onreadystatechange = function() { callback(xhr, element,input); };
	try {
		xhr.open("get", uri, true);
	}
	catch(e) {
		alert(e);
	}
	xhr.setRequestHeader("connection", "close");
	xhr.send(null);
}

function esegui(uri,element,input) {
	var xhr = myGetXmlHttpRequest();
	if (xhr)
		eseguiAJAX(uri,element,xhr,input);
}

function checkCitta(input,element){
		element.innerHTML="";
		var uri ="servlet?input="+input;
		esegui(uri,element,input);
	}
	
	function checkMaxMin(input){
		input.value= input.value.replace(/\D/g,'');
	}
	
	function leggiContenuto(item, nomeNodo) {
		return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
	};

	function parsificaXml( input,max,min ) {
		   
		var xml = Mapping[input]
		var 	items = xml.getElementsByTagName("casa"),

			// Predisponiamo una struttura dati in cui memorrizzare le informazioni di interesse
			itemNodes = new Array(),

			// la variabile di ritorno, in questo esempio, e' testuale
			risultato = "";

		// ciclo di lettura degli elementi
		for (    var a = 0, b = items.length;    a < b;   a++   ) {
			// [length al posto di push serve per evitare errori con vecchi browser]
			itemNodes[a] = new Object();
			itemNodes[a].indirizzo = leggiContenuto(items[a],"indirizzo");
			itemNodes[a].prezzo = leggiContenuto(items[a],"prezzo");
		}// for ( items )

		// non resta che popolare la variabile di ritorno
		// con una lista non ordinata di informazioni

		// apertura e chiusura della lista sono esterne al ciclo for 
		// in modo che eseguano anche in assenza di items
		risultato = "<ul>";

		for( var c = 0; c < itemNodes.length; c++ ) {
			if(parseInt(max)>parseInt(itemNodes[c].prezzo)&&parseInt(min)<parseInt(itemNodes[c].prezzo)){
			risultato += '<li class="item">' + itemNodes[c].indirizzo +'<br/>';
			risultato += itemNodes[c].prezzo +"<br/>";
		}
		};

		// chiudiamo la lista creata
		risultato += "</ul>";

	     // restituzione dell'html da aggiungere alla pagina
	     return risultato;

	}// parsificaXml()	
	
	function show(input,max,min,element){
		element.innerHTML =parsificaXml(input,max,min);		
	}

