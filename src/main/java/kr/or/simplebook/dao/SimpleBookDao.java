package kr.or.simplebook.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.simplebook.entity.Book;
import kr.or.simplebook.entity.Category;

import static kr.or.simplebook.dao.Sql.*;


@Repository
public class SimpleBookDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert categoryInsert;
	private SimpleJdbcInsert bookInsert;
	private RowMapper<Category> categoryMapper = BeanPropertyRowMapper.newInstance(Category.class);
	private RowMapper<Book> bookMapper = BeanPropertyRowMapper.newInstance(Book.class);

	public SimpleBookDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		categoryInsert = new SimpleJdbcInsert(dataSource).withTableName("categories").usingGeneratedKeyColumns("id");
		bookInsert = new SimpleJdbcInsert(dataSource).withTableName("book").usingGeneratedKeyColumns("id");
	}

	public int insertCategory(String category) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		return categoryInsert.execute(param);
	}
	
	public List<Category> selectCategories(){
		return jdbc.query(SELECT_CATEGORIES, categoryMapper);
	}
	
	public int insertBook(Book book) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(book);
		return bookInsert.execute(param);
	}
	
	public List<Book> selectBooksPaging(int start, int end){
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", start);
		param.put("end", end);
		return jdbc.query(SELECT_BOOKS_PAGING, param, bookMapper);
	}
	
	public int countBook() {
		return jdbc.queryForObject(COUNT_BOOKS, Collections.emptyMap() ,int.class);
	}
	
	public Book selectBookById(int id) {
		try {
			return jdbc.queryForObject(SELECT_BOOK_BY_ID, Collections.singletonMap("id", id), bookMapper);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int updateBook(Book book) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(book);
		return jdbc.update(UPDATE_BOOK, param);
	}
	
	public int deleteBookById(int id) {
		 SqlParameterSource param = new MapSqlParameterSource("bookId", id);
		return jdbc.update(DELETE_BOOK, param);
	}
}
