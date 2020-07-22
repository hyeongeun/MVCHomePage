/**
 * 
 */
function createFormCheck(obj){
	
	if(obj.id.value==""){
		alert("아이디를 반드시 입력하세요.");
		obj.id.focus();
		return false;
	}
	
	if(obj.password.value.length <= 7){
		alert("비밀번호 7글자 이상");
		obj.password.focus();
		return false;
	}
	
	if(obj.password.value!=obj.passwordCheck.value){
		alert("비밀번호가 일치하지 않습니다.")
		obj.password.focus();
		return false;
	}
	
	if(obj.name.value.length ==""){
		alert("이름을 반드시 입력하세요.");
		obj.name.focus();
		return false;
	}
	
	if(obj.jumin1.value.length ==""){
		alert("주민번호를 반드시 입력하세요.");
		obj.jumin1.focus();
		return false;
	}
	
	if(obj.jumin2.value.length ==""){
		alert("주민번호를 반드시 입력하세요.");
		obj.jumin2.focus();
		return false;
	}
	
	if(obj.jumin1.value.length !== 6){
		alert("주민번호 앞 6자리 입력하세요");
		obj.jumin1.focus();
		return false;
	}
	
	if(obj.jumin2.value.length !== 7){
		alert("주민번호 뒤 7자리 입력하세요");
		obj.jumin2.focus();
		return false;
	}
	
	if(obj.email.value.length ==""){
		alert("이메일을 반드시 입력하세요.");
		obj.email.focus();
		return false;
	}
	
	if(obj.zipcode.value.length ==""){
		alert("우편번호를 반드시 입력하세요.");
		obj.zipcode.focus();
		return false;
	}
	
	if(obj.address.value.length ==""){
		alert("주소를 반드시 입력하세요.");
		obj.address.focus();
		return false;
	}
	
	if(obj.job.value =="none"){
		alert("직업을 반드시 선택하세요.");
		obj.job.focus();
		return false;
	}
	
var check = false;
	for(var i=0; i<obj.mailing.length; i++){
		if(obj.mailing[i].checked == true) check=true;
			
	}
	if(check == false){
		alert("메일 수신을 반드시 선택하세요.");
		return false;
	}
		
	

	check = false;
	var str = ""; 
	for(var i=0; i<obj.interest.length; i++){
		if(obj.interest[i].checked ==true){
		str += obj.interest[i].value + ",";
		check = true;
		}
	}
	
	//alert(str);
	obj.resultInterest.value = str;
	
	if(check==false){
		alert("관심분야를 하나 이상 선택하세요.");
		return false;
	}

	

}


function idCheck(obj, root){
	//alert(obj.id.value);
	
	if(obj.id.value == ""){
		alert("아이디를 반드시 입력하고 검색하세요");
		obj.id.value.focus();
		return false;
	}
	
	var url = root + "/member/idCheck.do?id="+obj.id.value;
	//alert(url);
	//location.href = url;
	window.open(url,"","width=400, height=400")

}

function zipcodeButton(root){
		var url = root +"/member/zipcode.do";
		//alert(url);
		
		mywindow = window.open(url,"","width=800, height=400, scrollvars=yes")
	}
	
function sendAddress(zipcode, sido, gugun, dong, ri, bunji){
	var address = sido+ gugun+ri+bunji;
	//alert(zipcode +"\t"+address);
	
	opener.createForm.zipcode.value = zipcode;
	opener.createForm.address.value = address;
	mywindow.closed;
	
}



