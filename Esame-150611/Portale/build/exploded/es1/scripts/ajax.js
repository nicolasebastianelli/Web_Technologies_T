
function parsificaJSON( jsonText ,input) {
	var documenti = JSON.parse(jsonText);
	var risultato="";
	for ( var i in documenti.documenti) {
		risultato += "<label>"+documenti.documenti[i].nome+"</label><input type='checkbox' onchange='showDoc(myGetElementById('"+documenti.documenti[i].nome+"')'><br>"
	}
	for ( var i in documenti.documenti) {
		risultato +="<div id='"+documenti.documenti[i].nome+"' style='display=none'><div>"
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


function ControllaUrlAJAX(uri, element, xhr) {
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

function ControllaUrl(uri,element) {
	var xhr = myGetXmlHttpRequest();
	if (xhr)
		ControllaUrlAJAX(uri,element,xhr);
}

function checkUrl(url1,url2,url3,element){
	if(url1!=null&&url2!=null&&url3!=null){
		var uri ="downloadPage?url1="+url1+"&url2="+url2+"&url3="+url3;
		ControllaUrl(uri,element);
	}
}
function showDoc(doc){
	if(doc.checked)
		doc.style.display='block';
	else
		doc.style.display='none';
}
