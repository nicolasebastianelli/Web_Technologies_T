var globalreq1;
var globalreq2;

function parsificaJSON( jsonText) {
	var resp = JSON.parse(jsonText);
	if(globalreq1==-1)
		globalreq1 = parseFloat(resp);
	else
		globalreq2 = parseFloat(resp);
}


function callback(xhr, element) {
	element.class = "content";
	if (xhr.readyState === 4) {
		if (xhr.status === 200) {
			if(xhr.responseText){
				parsificaJSON(xhr.responseText)
				if(globalreq1==-1||globalreq2==-1)
					element.innerHTML = "In attesa di risultato";
				else
					element.innerHTML = globalreq1*globalreq2;
			}
		}
		else {

		}
	}
}


function eseguiAJAX(uri, element, xhr) {
	xhr.onreadystatechange = function() { callback(xhr, element); };
	try {
		xhr.open("get", uri, true);
	}
	catch(e) {
		alert(e);
	}
	xhr.setRequestHeader("connection", "close");
	xhr.send(null);
}

function esegui(uri,element) {
	var xhr = myGetXmlHttpRequest();
	if (xhr)
		eseguiAJAX(uri,element,xhr);
}

function calcola(x,y,element){
		element.innerHTML="";
		y.value = y.value.replace(/[^0-9]/g,'')
		x.value = x.value.replace(',','.')
		x.value = x.value.replace(/[^0-9\.]/g,'')
	 	var yint = parseInt(y.value);	 	
	 	var xreal = parseFloat(x.value);
		if(!isNaN(yint) && !isNaN(xreal)){
		var uri2 ="esp?x="+x.value+"&y="+Math.trunc(yint/2);
		var uri1 ="esp?x="+x.value+"&y="+(yint-Math.trunc(yint/2));
		globalreq1=-1
		globalreq2=-1
		esegui(uri1,element);
		esegui(uri2,element);
	}
		else
			{
			element.innerHTML="Valori in input non validi";
			}
}
function isEmpty(str) {
    return (!str || 0 === str.length);
}