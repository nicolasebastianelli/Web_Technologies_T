



function checkVal(input){
	input.value=input.value.replace(/\D/g,'');
	if(parseInt(input.value)>10)
		input.value="10";
	if(parseInt(input.value)<1 )
		input.value="1";
}
