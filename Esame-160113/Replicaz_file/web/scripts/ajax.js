
function check(input){
	input.value =input.value.replace(/[^0-9]/g,'')
	if(input.value.length >4)
		input.value = input.value.substring(0,4);
		
}
