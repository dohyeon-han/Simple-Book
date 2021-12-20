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
		object.data.book.forEach((book) => {
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
			a1.setAttribute("href", `./update/${book.bookId}`)
			a1.innerText = "수정";
			update.append(a1);

			let a2 = document.createElement("button");
			a2.setAttribute("id", book.bookId);
			a2.innerText = "삭제";
			del.append(a2);

			tr.append(title)
			tr.append(category)
			tr.append(price)
			tr.append(update)
			tr.append(del)
			table.append(tr)
		})
	}, console.log)
}



function requestAjax(url, option, func, errfun) {
	fetch(url, option)
		.then((response) => {
			return response.json();
		})
		.then((myJson) => {
			if(myJson.status=="OK")
				func(myJson);
			else
				errfun(myJson);
		})
		.catch((error) => {
			errfun(error);
		});
};

function addDeleteBook(){
	const list = document.querySelector(".list");
	list.addEventListener("click", function(e) {
		if (e.target.innerText == "삭제") {
			if (confirm("삭제하시겠습니까?")) {
				const option = {
					method: "DELETE",
					headers: {
						"Content-Type": "application/json",
					}
				};
				requestAjax(`/api/delete/book/${e.target.id}`, option, () => {
					alert("삭제되었습니다.")
					location.replace(location.href);
				}, () => {
					alert("오류가 발생했습니다.")
					location.replace(location.href);
				})
			}
		}
	})
}

document.addEventListener("DOMContentLoaded", function() {
	getList(1);
	addDeleteBook();
})