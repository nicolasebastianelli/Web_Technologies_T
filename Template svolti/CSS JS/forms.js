function validateLoginForm(form) {
	for (var i = 0; i < form.elements.length; i++) {
		if (isBlank(form.elements[i]))
			return false;
	}

	if (!validEmail(form.elements.usermail))
		return false;

	return true;
}

function validateAjaxForm(form) {
	for (var i = 0; i < form.elements.length; i++) {
		if (isBlank(form.elements[i]))
			return;
	}
	// altri check
	var url = "ajaxservlet?param=...";
	// oppure
	var message = new Object();
	message.javaClass = "esame.beans.AjaxMessage";
	message.message = "ciao";
	var url2 = JSON.stringify(message);
	esegui(url, myGetElementById("response"));
}

function submitOperazioniForm(form) {
	for (var i = 0; i < form.elements.length; i++) {
		if (isBlank(form.elements[i]))
			return;
	}
	if (nonZeroFloatError(form.elements.op1)||nonZeroFloatError(form.elements.op2))
		return;
	var operazione = new Object();
	operazione.javaClass = "esame.beans.Operazione";
	operazione.op1= parseFloat(form.elements.op1.value);
	operazione.op2= parseFloat(form.elements.op2.value)
	var serializedObject = JSON.stringify(operazione);
	var urlRedirect = "dispatcherservlet?jsonOperazione="+serializedObject;
	window.location.replace(urlRedirect);
	return;
}

function formOnKeyUp(form) {
	// elimina dal campo qualsiasi carattere diverso da a-z A-Z e 0-9
	// elimina i caratteri dopo il settimo
	// invoca ajax in base a una certa condizione
	var stringa = form.elements.field.value;
	var stringavalida = "";
	for (var i = 0; i<stringa.length; i++){
		var c = stringa[i];
		if ( ((c>='a'&&c<='z')||(c>='A'&&c<='Z')||(c>='0'&&c<='9')) && stringavalida.length<7){
			stringavalida+=c;
		}
	}
	form.elements.field.value = stringavalida;
	if (stringavalida.length == 7) {
		var url = "ajaxservlet?param=..."
		esegui(url, myGetElementById("response"));
		targa.value = "";
	}
}

function containsForbiddendCharacters(myField, forbidden) {
	var stringa = myField.value;
	for (var i = 0; i<stringa.length; i++){
		if(forbidden.indexOf(forbidden[i])!=-1)
			return true;
	}
	return false;
}

function isBlank(myField) {
    // Check for non-blank field
    var result = false;
    if (myField.value && myField.value.trim() == "") {
        alert("Please enter a value for the '" + myField.name + "' field.");
        myField.focus();
        result = true;
    }
    return result;
}

function numberError(myField) {
	var result = false;
	if (myField.value.trim() == "" || isNaN(myField.value.trim())) {
		alert("Please enter a numeric value for the '" + myField.name
				+ "' field.");
		myField.focus();
		result = true;
	}
	return result;
}

// number must have less than minLen digits
function numberLengthError(myField, minLen) {
	var error = numberError(myField);
	if (!error) {
		if (myField.value.trim().length < minLen)
			alert("Please enter a number of at least" + minLen + " digits '"
					+ myField.name + "' field.");
		myField.focus();
		error = true;
	}
	return error;
}

//number must be int positive
function positiveIntError(myField) {
	var error = numberError(myField);
	if (!error) {
		var number = parseInt(myField.value.trim());
		if (number < 0) {
			alert("Please enter a positive value in the '" + myField.name
					+ "' field.");
			myField.focus();
			error = true;
		}
	}
	return error;
}

// number must be float positive
function positiveFloatError(myField) {
	var error = numberError(myField);
	if (!error) {
		var number = parseFloat(myField.value.trim());
		if (number < 0) {
			alert("Please enter a positive value in the '" + myField.name
					+ "' field.");
			myField.focus();
			error = true;
		}
	}
	return error;
}

// float number must be in range [minumium, maximum]
function numberInRangeError(myField, minimum, maximum) {
	var error = numberError(myField);
	if (!error) {
		var number = parseFloat(myField.value.trim());
		if (number < minimum || number > maximum) {
			alert("Please enter a value between " + minimum + " and " + maximum
					+ " in the '" + myField.name + "' field.");
			myField.focus();
			error = true;
		}
	}
	return error;
}

function validEmail(myField) {
	// Check for "valid" email: not empty, has "@" sign and "."
	var result = false;
	if (!isBlank(myField)) {
		var tempstr = new String(myField.value);
		var aindex = tempstr.indexOf("@");
		if (aindex > 0) {
			var pindex = tempstr.indexOf(".", aindex);
			if ((pindex > aindex + 1) && (tempstr.length > pindex + 1)) {
				result = true;
			} else {
				result = false;
			}
		}
	}
	if (!result) {
		alert("Please enter a valid email address in the form: yourname@yourdomain.com");
		myField.focus();
	}
	return result;
}

function validURL(field) {
	var regex = /(http|https):\/\/(\w+:{0,1}\w*)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%!\-\/]))?/;
	if(!regex .test(url.value)) {
		alert("Please enter valid URL.");
		url.value="";
		return false;
	} 
	return true;
}

function validPwd(field) {
    var stringa = field.value;
    var numeri = 0;
    var maiuscola = 0;
    var speciale = 0;
    for (var i = 0; i < stringa.length; i++) {
        var c = stringa[i];
        if (c >= '0' && c <= '9')
            numeri++;
        else if (c >= 'A' && c <= 'Z')
            maiuscola++;
        else if ("!?$".indexOf(c) != -1)
            speciale++;
    }
    if(numeri<2||maiuscola<1||speciale<1){
        alert("Pwd deve contenere 2 numeri, 1 lettera maiuscola e un carattere speciale tra !, $ e ?")
				return false;
		} else
			return true;
}

function setListeners(table) {
    for (var i = 0; i < table.children.length; i++) {
        var tr = table.children[i];
        if (tr.tagName == "TR") {
            for (var j = 0; j < tr.children.length; j++) {
                var td = tr.children[j];
                if (td.tagName == "TD") {
                    td.addEventListener("click", new Function("show('" + td.innerText + "');"));
                }
            }
        }
    }
}

function show(name) {
    for (var i = 1; i <= 4; i++) {
        var img = myGetElementById("img" + i);
        img.setAttribute("src", name + "-" + i + ".jpg");
        img.setAttribute("alt", name + "-" + i + ".jpg");
    }
}

// crea tante caselle di testo per filename e tante caselle di risultato quante il valore del field n
function spawnTextBoxes(n) {
	var div = myGetElementById('div');
	div.innerHTML="";
	if (checkConstraintNumber(n)) {
		for (var i =0; i<parseInt(n.value); i++) {
			var input = document.createElement('input');
			input.setAttribute('type', 'text');
			input.setAttribute('name', 'filename');
			input.setAttribute('onkeyup', 'esegui()');
			div.appendChild(input);

			var resUl = document.createElement('p');
			resUl.setAttribute('id', 'result' + i);
			div.appendChild(resUl);

			div.appendChild(document.createElement('br'));
		}
	}
}
