function showRegistratiForm() {
	myGetElementById("immagini").hidden = true;
	myGetElementById("registrati").hidden = false;
}

function validateLoginForm(form) {
    for (var i = 0; i < form.elements.length; i++) {
        if (isBlank(form.elements[i]))
            return false;
    }
    alert("Login OK");
    return true;
}

function checkNoNumber(field) {
    var stringa = field.value;
    if (new RegExp(".*[0-9].*").test(stringa) )
    	alert("Errore: " + stringa);
}

var N = 4;
function show(name) {
	var target = myGetElementById("immagini");
	target.innerHTML="";
    for (var i = 1; i <= N; i++) {
        var img = document.createElement("img");
        img.setAttribute("src", name + i + ".jpeg");
        img.setAttribute("alt", name + i + ".jpeg");
        target.appendChild(img);
    }
}

function checkPwd(field) {
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
    if(string.length<9||numeri<3||maiuscola<1||speciale<1)
        alert("Pwd deve contenere 3 numeri, 1 lettera maiuscola e un carattere speciale tra !, $ e ? ed avere un tot di almeno 4 lettere (min/maiu)")
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

/**
 * from http://javascript.html.it/guide/leggi/95/guida-ajax/
 */
function myGetElementById(idElemento) {

	// elemento da restituire
	var elemento;

	// se esiste il metodo getElementById questo if sara'�
	// diverso da false, null o undefined
	// e sara'� quindi considerato valido, come un true
	if ( document.getElementById )
		elemento = document.getElementById(idElemento);

	// altrimenti e' necessario usare un vecchio sistema
	else
		elemento = document.all[idElemento];

	// restituzione elemento
	return elemento;

} // myGetElementById()

