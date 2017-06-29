function validateLoginForm(form) {
	for (var i = 0; i<form.elements.length; i++) {
		if (isBlank(form.elements[i]))
			return false;
	}
	
	return true;
}

function isBlank(myField) {
	// Check for non-blank field
	var result = false;
	if ( myField.value.trim() == "") {
		alert("Please enter a value for the '" + myField.name + "' field.");
		myField.focus();
		result = true;
	}
	return result;
}

function numberError(myField) {
	// Check for non-numeric field shorter than minLen
	var minLen = 5
	var result = false;
	if ( myField.value.trim() == "" || isNaN(myField.value.trim()) || myField.value.trim().length < minLen ) {
		alert("Please enter a numeric value for the '" + myField.name + "' field.");
		myField.focus();
		result = true;
	}
	return result;
}

function validEmail(myField) {
	// Check for "valid" email: not empty, has "@" sign and "."
	var result = false;
	if ( !isBlank(myField) ) {
		var tempstr = new String(myField.value);
		var aindex = tempstr.indexOf("@");
		if (aindex > 0 ) {
			var pindex = tempstr.indexOf(".",aindex);
	        if ( (pindex > aindex+1) && (tempstr.length > pindex+1) ) {
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