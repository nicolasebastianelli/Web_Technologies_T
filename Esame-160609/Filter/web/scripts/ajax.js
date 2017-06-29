
function parsificaJSON( jsonText ,input) {
	var riga = JSON.parse(jsonText);
	var risultato= "<h5>Righe stringa "+input+" </h5>"
	for ( i in riga.righe) {
		risultato += "<label>"+riga.righe[i]+"</label><br>"
	}
    return risultato;
}


function callback(xhr, element,input) {
	element.class = "content";
	if (xhr.readyState === 4) {
		if (xhr.status === 200) {
			if(xhr.responseText){
				var res=parsificaJSON(xhr.responseText,input)
	        	element.innerHTML = res;
			}
		}
		else {

		}
	}
}


function ControllaRigaAJAX(uri, element, xhr,input) {
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

function ControllaRiga(uri,element,input) {
	var xhr = myGetXmlHttpRequest();
	if (xhr)
		ControllaRigaAJAX(uri,element,xhr,input);
}

function checkRiga(input,element){
	if(/^[a-zA-Z]{3,20}$/.test(input)){
		var uri ="filter?input="+input;
		ControllaRiga(uri,element,input);
	}
}