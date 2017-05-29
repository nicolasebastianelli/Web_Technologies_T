
function parsificaJSON( jsonText ) {
	var imuCf = JSON.parse(jsonText);
	var risultato = "<li>imu = "+imuCf.imu+"         cf = "+imuCf.cf+"</li>" ;
    return risultato;
}


function callback(xhr, element) {
	element.class = "content";
	if (xhr.readyState === 4) {
		if (xhr.status === 200) {
			if(xhr.responseText){
				var res=parsificaJSON(xhr.responseText)
				var pre = "";
	        	var currentResults = element.childNodes.length;
	        	if( currentResults < 3 ){
	        		pre = element.innerHTML;
	        	}
	        	else{
	        		currentResults = 0;
	        	}
	        	element.innerHTML = pre + res;
			}
		}
		else {

		}
	}
}


function CalcolaImuAJAX(uri, element, xhr) {
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

function CalcolaImu(uri,element) {
	var xhr = myGetXmlHttpRequest();
	if (xhr)
		CalcolaImuAJAX(uri,element,xhr);
}

function checkImu(rendita, cf,element){
	if(rendita.length!=0 && cf.length==16){
		var uri ="calcolaImu.jsp?rendita="+rendita+"&cf="+cf;
		CalcolaImu(uri,element);
	}
}