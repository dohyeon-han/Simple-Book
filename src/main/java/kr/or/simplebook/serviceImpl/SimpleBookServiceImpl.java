package kr.or.simplebook.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.simplebook.dao.SimpleBookDao;
import kr.or.simplebook.entity.Book;
import kr.or.simplebook.entity.Category;
import kr.or.simplebook.service.SimpleBookService;

@Service
public class SimpleBookServiceImpl implements SimpleBookService{
	@Autowired
	SimpleBookDao simpleBookDao;
	
	@Transactional
	public int insertCategory(String category) {
		return simpleBookDao.insertCategory(category);
	}
	
	public List<Category> selectCategories() {
		return simpleBookDao.selectCategories();
	}
	
	@Transactional
	public int insertBook(Book book) {
		return simpleBookDao.insertBook(book);
	}
	
	public List<Book> selectBookPaging(int start, int end) {
		return simpleBookDao.selectBooksPaging(start, end);
	}
	
	public int countBooks() {
		return simpleBookDao.countBook();
	}
}
