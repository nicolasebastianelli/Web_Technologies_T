
function parsificaJSON( jsonText ,input) {
	var riga = JSON.parse(jsonText);
	var risultato= "<h5>Ricerca "+input+" : </h5>"
	for ( i in riga) {
		risultato += "<label>"+riga[i].titolo+"</label><br>"
	}
	return risultato;
}


function callback(xhr, element,input) {
	element.class = "content";
	if (xhr.readyState === 4) {
		if (xhr.status === 200) {
			if(xhr.responseText){
				var res=parsificaJSON(xhr.responseText,input)
				if (element.children.length > 2) {
					element.children[0].remove();
				}
				var child = document.createElement("p");
				child.innerHTML= res;
				element.appendChild(child);
			}
			else {
				// non faccio niente
			}
		}
	}
	else {

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

function cerca(input1,input2,input3,element){
	if(input1==null||input2==null||input3==null)
		return;
	if(/[0-9]/.test(input1)||/[0-9]/.test(input2)||/[0-9]/.test(input3)){
		return;
	}
	var uri ="dispatcher?input1="+input1+"&input2="+input2+"&input3="+input3;
	var input = input1+" "+input2+" "+input3
	esegui(uri,element,input);
}
