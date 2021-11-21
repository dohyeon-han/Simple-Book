package kr.or.simplebook.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {
	String title;
	int price;
	int bookId;
	String category;
	int categoryId;
	@JsonFormat(pattern = "yyyy-MM=dd kk:mm:ss")
	LocalDateTime createDate;
	@JsonFormat(pattern = "yyyy-MM=dd kk:mm:ss")
	LocalDateTime updateDate;
	
	public Book() {
		this.title = null;
		this.price = 0;
		this.bookId = 0;
		this.category = "";
		this.categoryId = 0;
		this.createDate = LocalDateTime.now();
		this.updateDate = LocalDateTime.now();
	}
	
	public Book(String title, int price, int bookId, String category, int categoryId, LocalDateTime createDate, LocalDateTime updateDate) {
		this.title = title;
		this.price = price;
		this.bookId = bookId;
		this.category = category;
		this.categoryId = categoryId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}
