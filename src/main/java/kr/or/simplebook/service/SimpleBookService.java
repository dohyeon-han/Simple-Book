package kr.or.simplebook.service;

import java.util.List;

import kr.or.simplebook.entity.Book;
import kr.or.simplebook.entity.Category;

public interface SimpleBookService {
	public int insertCategory(String category);
	public List<Category> selectCategories();
	public int insertBook(Book book);
	public List<Book> selectBookPaging(int start, int end);
	public int countBooks();
}
