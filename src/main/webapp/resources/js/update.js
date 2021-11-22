let url = window.location.href;
let id = url.split('/').pop();

function init() {
	requestAjax("/api/get/category", null, (object) => {
		const categories = object.data;
		categories.forEach((cate) => {
			const select = document.querySelector(".category");
			const option = document.createElement("option");
			option.setAttribute("value", cate.categoryId)
			option.innerText = `${cate.category}`;
			select.appendChild(option);
			getData();
		})
	});
};

function getData() {
	requestAjax(`/api/get/book/${id}`, null, (object) => {
		const data = object.data;
		let title = document.querySelector("input[name=title]")
		let category = document.querySelector("select[name=categoryId]")
		let price = document.querySelector("input[name=price]")
		title.value = data.title;
		category.value = data.categoryId
		price.value = data.price;
	})
}

function insert(target) {
	let data = new FormData(target)
	data.append("bookId", id)
	let object = {};
	data.forEach((value, key) => object[key] = value);
	const option = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(object)
	};
	requestAjax(`/api/put/book`, option, (object) => {
		if (object.status == "OK") {
			alert("저장되었습니다.")
			location.replace("/list")
		}
		else {
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
	init();
	document.querySelector(".form").addEventListener("submit", (e) => {
		e.preventDefault();
		if (confirm("수정하시겠습니까?"))
			insert(e.target);
	})
})
