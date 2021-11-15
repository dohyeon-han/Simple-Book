
function join() {
	let id = document.querySelector(".id").value
	let pw = document.querySelector(".pw").value
	const data = {
		id : id,
		pw : pw
	};
	requestAjax(`./join`, data, (object) => {
		if(object.message==="same id exist"){
			alert("중복된 ID입니다.")
		}
		else{
			alert("회원 가입이 왼료되었습니다.\n가입된 ID로 로그인 해주세요")
			location.href="./login";
		}
	});

}

function requestAjax(url, data, func) {
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(JSON.parse(xhttp.responseText));
		}
	};
	xhttp.open('POST', url);
	xhttp.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
	xhttp.send(JSON.stringify(data)); // 데이터를 문자열로 동봉해서 전송한다
}