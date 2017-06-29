
function parsificaJSON( jsonText) {
	var res = JSON.parse(jsonText);
	var risultato= "<h5>Pokemon visibili</h5>"
	for ( i in res[1]) {
		risultato += "<label>"+res[1][i].name+"</label><br>"
	}
	risultato+= "<h5>Pokemon futuri</h5>"
		for ( i in res[0]) {
			risultato += "<label>"+res[0][i].name+"</label><br>"
		}
    return risultato;
}


function callback(xhr, element) {
	element.class = "content";
	if (xhr.readyState === 4) {
		if (xhr.status === 200) {
			if(xhr.responseText){
				var res=parsificaJSON(xhr.responseText)
	        	element.innerHTML = res;
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

function check(input1,input2,element){
		input1.value = input1.value.replace(/\D/g,'');
		input2.value = input2.value.replace(/\D/g,'');
		var uri ="filter?x="+input1.value+"&y="+input2.value;
		esegui(uri,element);
	}
