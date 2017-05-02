function minimo(voti){
	var min="33";
	for(i=0;i<voti.length;i++){
		if(eval(voti[i])<eval(min))
			min=voti[i];
	}
	return min;
}

function massimo(voti){
	var max="0";
	for(i=0;i<voti.length;i++){
		if(eval(voti[i])>eval(max))
			max=voti[i];
	}
	return max;
}

function media(voti){
	var media =0;
	var giudizio = "none";
	for(i=0;i<voti.length;i++){
		media=media+eval(voti[i]);
	}
	media=media/eval(voti.length);
	if(media<21)
		giudizio=" (sufficente)";
	else if(media<23)
		giudizio=" (discreto)";
	else if(media<25)
		giudizio=" (buono)";
	else if(media<27)
		giudizio=" (distinto)";
	else if(media<29)
		giudizio=" (ottimo)";
	else
		giudizio=" (eccellente)";
	return media.toFixed(2) + giudizio;
}

function variabilita(voti,media){
	var variabilita=0;
	
	var sum =0;
	var tmp;
	var giudizio = "none";
	for(i=0;i<voti.length;i++){
		tmp=eval(voti[i])-eval(media.split(" ")[0])
		sum+= Math.pow(tmp,2);
	}
	sum =sum / voti.length;
	variabilita = Math.sqrt(sum);
	if (variabilita<1.875)
		giudizio=" (quasi nullo)";
	else if (variabilita<3.75)
		giudizio=" (basso)";
	else if (variabilita<5.625)
		giudizio=" (medio)";
	else
		giudizio=" (alto)";
	return variabilita.toFixed(2) + giudizio;
}