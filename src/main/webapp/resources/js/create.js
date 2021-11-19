function getCategories() {
	const option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		}
	};
	requestAjax("category/select", option, (object) => {
		const categories = object.data;
		categories.forEach((cate) => {
			const select = document.querySelector(".category");
			const option = document.createElement("option");
			option.setAttribute("value", cate.categoryId)
			option.innerText = `${cate.category}`;
			select.appendChild(option);
		})
	});
};

function create(target) {
	let data = new FormData(target)
	let object = {};
	data.forEach((value, key) => object[key] = value);
	const option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(object)
	};
	requestAjax("./create", option, (object) => {
		if (object.status == "OK") {
			alert("저장되었습니다.")
			location.replace("./list")
		}
		else{
			alert("오류가 발생했습니다.")
		}
	})
}

function requestAjax(url, option, func) {
	fetch(url, option)
		.then((response) => {
			return response.json();
		})
		.then((myJson) => {
			func(myJson);
		})
		.catch((error) => {
			console.log(error);
		});
};

document.addEventListener("DOMContentLoaded", function() {
	getCategories();
	document.querySelector(".form").addEventListener("submit", (e) => {
		e.preventDefault();
		if (confirm("저장하시겠습니까?"))
			create(e.target);
	})
})
