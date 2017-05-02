
function onLoad(){
	var d = new Date();
	var result = "Oggi &egrave ";
	var day =d.getDay();
	if(day == 1){
		day="lunedi"
	}	
	else if(day == 2){
		day="martedi"
	}
	else if(day == 3){
		day="mercoledi"
	}	
	else if(day == 4){
		day="giovedi"
	}	
	else if(day == 5){
		day="venerdi"
	}	
	else if(day == 6){
		day="sabato"
	}	
	else if(day == 0){
		day="domenica"
	}	
	result += day;
	result += " " +d.getDate();
	var mese = d.getMonth();
	if(mese==0){
		mese=" gennaio "
	}
	else if(mese==1){
		mese=" febbraio "
	}
	else if(mese==2){
		mese=" marzo "
	}
	else if(mese==3){
		mese=" aprile "
	}
	else if(mese==4){
		mese=" maggio "
	}
	else if(mese==5){
		mese=" giugnio "
	}
	else if(mese==6){
		mese=" luglio "
	}
	else if(mese==7){
		mese=" agosto "
	}
	else if(mese==8){
		mese=" settembre "
	}
	else if(mese==9){
		mese=" ottobre "
	}
	else if(mese==10){
		mese=" novembre "
	}
	else if(mese==11){
		mese=" dicembre "
	}
	result += mese
	result += d.getFullYear()+", ore ";
	result+= d.getHours() +":"+d.getMinutes() +":"+d.getSeconds();
	return result;
}

function changeColor(){
	document.getElementById("messaggio").style.background="green"
		}
function resetColor(){
	document.getElementById("messaggio").style.background="white"
		}
function refreshChar(){
	var nchar= document.getElementById("messaggio").value.length;
	var text;
	if (nchar>64){
		text= document.getElementById("messaggio").value;
		text= text.substr(0,63)+text.substr(64);
		document.getElementById("messaggio").value=text;
	}
	 nchar= document.getElementById("messaggio").value.length;
	document.getElementById("charleft").value=64-eval(nchar);
}
function Events(id,event){
	document.getElementById("eventi").value=document.getElementById("eventi").value+"["+event+"]: name="+id.name+", value="+id.value+"\n"
}
function svuotaEventi(){
	document.getElementById("eventi").value="";
}
