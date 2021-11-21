function getList(page) {
	const data = {
		page: page
	}
	const option = {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
		}
	};
	requestAjax(`/api/list/${page}`, option, (object) => {
		let table = document.querySelector(".list")
		object.data.book.forEach((book)=>{
			let tr = document.createElement("tr")
			let title = document.createElement("td")
			let category = document.createElement("td")
			let price = document.createElement("td")
			let a = document.createElement("a")
			a.setAttribute("href",`./detail/${book.bookId}`)
			a.innerText = book.title
			title.append(a);
			category.innerText = book.category
			price.innerText = book.price
			tr.append(title)
			tr.append(category)
			tr.append(price)
			table.append(tr)
		})
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
	getList(1);
})