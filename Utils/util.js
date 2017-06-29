// max n figli
var res = theXhr.responseText;
if (element.children.length > 5) {
    element.children[0].remove();
}
var child = document.createElement("p");
child.innerHTML = res;
element.appendChild(child);
}
else {
    
}

// max n figli

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

//Parser xml

function leggiContenuto(item, nomeNodo) {
    return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
};
function parsificaXml( xml ) {
    
    var 	items = xml.getElementsByTagName("casa"),itemNodes = new Array(),risultato = "";
    for (    var a = 0, b = items.length;    a < b;   a++   ) {
        itemNodes[a] = new Object();
        itemNodes[a].indirizzo = leggiContenuto(items[a],"indirizzo");
        itemNodes[a].prezzo = leggiContenuto(items[a],"prezzo");
    }
    risultato = "<ul>";
    for( var c = 0; c < itemNodes.length; c++ ) {
            risultato += '<li class="item">' + itemNodes[c].indirizzo +'<br/>';
            risultato += itemNodes[c].prezzo +"<br/>";
        }
    };
    risultato += "</ul>";
    return risultato;
    
}

//Object

var o = {};
o["x"] = 7;
o.x=7;
for(var i in o)
    o[i];

//array
var a = [1,2,3];
for(var i in a)
    a[i];

//hide an element
element.hidden=true;

//alert

alert("I am an alert box!");

//confirm
if (confirm("Press a button!") == true) {
    txt = "You pressed OK!";
} else {
    txt = "You pressed Cancel!";
}

//prompt
var person = prompt("Please enter your name", "Harry Potter");

if (person == null || person == "") {
    txt = "User cancelled the prompt.";
} else {
    txt = "Hello " + person + "! How are you today?";
}

//replace
input.value = input.value.replace(/[^a-z0-9\s]/gi, '') // elimina tutti i caratteri diversi da a-z, numeri e spazi
input.value = input.value.replace(/\D/g, '') //elimina tutto ciò che è diverso da un numero
input.value=input.value.substring(0, maxlength); // elimina i caratteri che superano la lunghezza massima

//stringa vuota
function isEmpty(str) {
    return (!str || 0 === str.length);
}

// check intero
val = parseInt(val);
if(isNaN(val))
{
    
}
// check float
val = parseFloat(val);
if(isNaN(val))
{
    
}


// invio file
function myFunction() {
    var input, file;
    if (!window.FileReader) {
        bodyAppend("p", "The file API isn't supported on this browser yet.");
        return;
    }    
    input = document.getElementById('myFile');
    if (!input) {
        document.getElementById('output').innerHTML="Um, couldn't find the fileinput element.";
    }
    else if (!input.files) {
        document.getElementById('output').innerHTML="This browser doesn't seem to support the `files` property of file inputs.";
    }
    else if (!input.files[0]) {
        document.getElementById('output').innerHTML="Please select a file before clicking 'Load'";
    }
    else {
        file = input.files[0];
        document.getElementById('output').innerHTML="File " + file.name + " is " + file.size + " bytes in size";
    }
}
