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
	requestAjax(`/api/get/list/${page}`, option, (object) => {
		let table = document.querySelector(".list")
		object.data.book.forEach((book)=>{
			let tr = document.createElement("tr")
			let title = document.createElement("td")
			let category = document.createElement("td")
			let price = document.createElement("td")
			let update = document.createElement("td")
			let del = document.createElement("td")
			
			title.innerText = book.title
			category.innerText = book.category
			price.innerText = book.price
			
			let a1 = document.createElement("a")
			a1.setAttribute("href",`./update/${book.bookId}`)
			a1.innerText = "수정";
			update.append(a1);
			
			let a2 = document.createElement("a")
			a2.setAttribute("href",`./delete/${book.bookId}`)
			a2.innerText = "삭제";
			del.append(a2);
			
			tr.append(title)
			tr.append(category)
			tr.append(price)
			tr.append(update)
			tr.append(del)
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