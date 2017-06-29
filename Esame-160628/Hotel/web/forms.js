

function validateForm(form) {
    if (isBlank(form.elements.star) || form.elements.nazione.value=="" || form.elements.citta.value=="" )
        return false;
    alert("Login OK");
    return true;
}

function checkPass(field) {
	var pass = field.value;
	if ( !isBlank(pass) ) 
    if (/[\$\*\!\?]/.test(pass)&&pass.length==8){}
    else
    	alert("pass di 8 caratteri contenente almeno un carattere tra $!?*")
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