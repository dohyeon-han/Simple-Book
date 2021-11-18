package kr.or.simplebook.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {
	String title;
	int price;
	int categoryId;
	LocalDateTime createDate;
	LocalDateTime updateDate;
	
	public Book() {
		this.title = null;
		this.price = 0;
		this.categoryId = 0;
		this.createDate = LocalDateTime.now();
		this.updateDate = LocalDateTime.now();
	}
	
	public Book(String title, int price, int categoryId, LocalDateTime createDate, LocalDateTime updateDate) {
		this.title = title;
		this.price = price;
		this.categoryId = categoryId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}
