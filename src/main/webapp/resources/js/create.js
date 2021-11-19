function getCategories(data) {
	const option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	};
	requestAjax("category/select",option, (object)=>{
		const categories = object.data;
		categories.forEach((cate)=>{
			const select = document.querySelector(".category");
			const option = document.createElement("option");
			option.setAttribute("name",cate.categoryId)
			option.innerText=`${cate.category}`;
			select.appendChild(option);
		})
	});
};

function create(target){
	let data = new FormData(target)
	data.append("title",target.title.value);
	data.append("categoryId",target.category.name);
	data.append("price",target.price.value);
	const option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(new FormData(target))
	};
	console.log(data);
	requestAjax("./create", option,(object)=>{
		alert("생성되었습니다.")
		location.replace("./list")
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

document.addEventListener("DOMContentLoaded",function(){
	getCategories(null);
	document.addEventListener("submit",(e)=>{
		e.preventDefault();
		create(e.target);
	})
})
