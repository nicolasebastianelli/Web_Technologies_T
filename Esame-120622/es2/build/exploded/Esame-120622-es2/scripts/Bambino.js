

function main(){
	var Junior={
		numero: function(){return this.eta.length;},
		massimo: function(){var max=0;for(i=0;i<this.numero();i++){if(max<this.eta[i]) max = this.eta[i]} return max; },
		minimo: function(){var min=3;for(i=0;i<this.numero();i++){if(min>this.eta[i]) min = this.eta[i]} return min; },
		medio: function(){var sum=0;for(i=0;i<this.numero();i++){sum+=this.eta[i];} return  parseFloat(sum)/parseFloat(this.numero());},
		eta: []
	} ;
	var Senior={
		numero: function(){return this.eta.length;},
		massimo: function(){var max=0;for(i=0;i<this.numero();i++){if(max<this.eta[i]) max = this.eta[i]} return max; },
		minimo: function(){var min=5;for(i=0;i<this.numero();i++){if(min>this.eta[i]) min = this.eta[i]} return min; },
		medio: function(){var sum=0;for(i=0;i<this.numero();i++){sum+=this.eta[i];} return  parseFloat(sum)/parseFloat(this.numero());},
		eta: []
	} ;
	var ok=1;
	var eta = prompt("Inserire eta bambini [2-5] separata da spazi")
	for(i=0;i<eta.length;i++){
		if(eta.charAt(i)==" ")
			continue;
		else if(eta.charAt(i)==2 ||eta.charAt(i)==3){
			Junior.eta.push(parseInt(eta.charAt(i)));
		}
		else if(eta.charAt(i)==4 ||eta.charAt(i)==5){
			Senior.eta.push(parseInt(eta.charAt(i)));
		}
		else {
			alert("Formattazione non corretta")
			return;
		}
	}
		var inner="<table><tr>"+
		"<th>Junior:</th>"+
		"<td>Numero: "+Junior.numero()+"</td>"+
		"<td>Massimo: "+Junior.massimo()+"</td>"+
		"<td>Minimo: "+Junior.minimo()+"</td>"+
		"<td>Medio: "+Junior.medio()+"</td>"+
		"</tr> <tr>"+
		"<th>Senior:</th>"+
		"<td>Numero: "+Senior.numero()+"</td>"+
		"<td>Massimo: "+Senior.massimo()+"</td>"+
		"<td>Minimo: "+Senior.minimo()+"</td>"+
		"<td>Medio: "+Senior.medio()+"</td>"+
		"</tr></table>"
		myGetElementById("tabella").innerHTML = inner;
}