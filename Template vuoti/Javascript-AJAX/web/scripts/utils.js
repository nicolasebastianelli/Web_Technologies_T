function myGetElementById(elementId) {
	var element;

	if (document.getElementById)
		element = document.getElementById(elementId);
	else
		element = document.all[elementId];

	return element;
}

function myGetXmlHttpRequest() {
	var xhr = false;
	var activeXoptions = new Array("Microsoft.XmlHttp", "MSXML4.XmlHttp", "MSXML3.XmlHttp", "MSXML2.XmlHttp", "MSXML.XmlHttp");
	try { 
		xhr = new XMLHttpRequest(); 
	} 
	catch (e) {
		console.log("new XMLHttpRequest() failed");
	}
	if (!xhr) {
		var created = false; 
		for (var i = 0; i < activeXoptions.length && !created; i++) {
			try {
				xhr = new ActiveXObject(activeXoptions[i]);
				created = true;
			} 
			catch (e) { 
				console.log("new ActiveXObject(" + activeXoptions[i] + ") failed");
			} 
		}
	}
	return xhr;
}

function myGetRequestParameter(parameterName) {
	var queryString = window.top.location.search.substring(1);
	var parameterName = parameterName + "=";
	if (queryString.length > 0) {
		begin = queryString.indexOf(parameterName);
		if (begin != -1) {
			begin += parameterName.length;
			end = queryString.indexOf("&", begin);
			if (end == -1) {
				end = queryString.length
			}
			return unescape(queryString.substring(begin, end));
		}
		return "null";
	}
}

function myGetChildByName(element, name) {
	
	if (element.childNodes)
		for (var i = 0; i < element.childNodes.length; i++)
			if (element.childNodes[i].name === name)
				return element.childNodes[i];
}
