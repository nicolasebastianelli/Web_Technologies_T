
function nonBlank(myField) {
	// Check for non-blank field
    	var result = true;
	if ( myField.value == "") {
		alert("Please enter a value for the '" + myField.name + "' field.");
		myField.focus();
		result = false;
	}
	return result;
}

function validEmail(myField) {
	// Check for "valid" email: not empty, has "@" sign and "."
	var result = false;
	if ( nonBlank(myField) ) {
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
