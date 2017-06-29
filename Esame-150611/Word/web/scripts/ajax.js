
function parsificaJSON( jsonText ) {
	var stato = JSON.parse(jsonText);
	return stato;
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


function ControllaRigaAJAX(uri, element, xhr) {
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

function ControllaRiga(uri,element) {
	var xhr = myGetXmlHttpRequest();
	if (xhr)
		ControllaRigaAJAX(uri,element,xhr);
}

function ValidaRiga(input,element){
	var numero = parseInt(input);
	var uri ="wordProcessing?input="+numero+"&num=0";
	ControllaRiga(uri,element);
	uri ="wordProcessing?input="+numero+"&num=1";
	ControllaRiga(uri,element);
	uri ="wordProcessing?input="+numero+"&num=2";
	ControllaRiga(uri,element);
	uri ="wordProcessing?input="+numero+"&num=3";
	ControllaRiga(uri,element);
	uri ="wordProcessing?input="+numero+"&num=4";
	ControllaRiga(uri,element);
}
