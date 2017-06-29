function callback(xhr, element) {
	element.class = "content";
	if (xhr.readyState === 4) {
		if (xhr.status === 200) {
			// Success
		}
		else {
			// Fail
		}
	}
}

function FUNZIONEIframe(uri,holder) {
	holder.innerHTML = '<iframe src="'+uri+'">Your browser do not support frames</iframe>';
}

function FUNZIONEAJAX(uri, element, xhr) {
	theXhr.onreadystatechange = function() { callback(xhr, element); };
	try {
		theXhr.open("get", uri, true);
	}
	catch(e) {
		alert(e);
	}
	theXhr.setRequestHeader("connection", "close");
	theXhr.send(null);
}

function FUNZIONE(uri,element) {
	var xhr = myGetXmlHttpRequest();
	if (xhr)
		FUNZIONEAJAX(uri,element,xhr);
	else
		FUNZIONEIframe(uri,element); 
}