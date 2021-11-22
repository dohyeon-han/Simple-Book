package kr.or.simplebook.dao;

public class Sql {
	public static final String SELECT_USER = "SELECT username, password, enable "
			+ "FROM users WHERE username = :username";
	public static final String INSERT_USER = "INSERT INTO users VALUES(:username, :password, :enable)";
	public static final String INSERT_AUTH = "INSERT INTO authorities VALUES(:username, :auth)";
	public static final String SELECT_BOOKS_PAGING = "SELECT book_id, title, price, category, category_id, create_date, update_date "
			+ "FROM book JOIN categories USING(category_id) LIMIT :start, :end";
	public static final String SELECT_CATEGORIES = "SELECT category_id, category FROM categories";
	public static final String COUNT_BOOKS = "SELECT COUNT(*) FROM BOOK";
	public static final String SELECT_BOOK_BY_ID = "SELECT * FROM categories JOIN book USING(category_id)"
			+ " WHERE book_id = :id";
	public static final String UPDATE_BOOK = "UPDATE book SET title = :title, category_id = :categoryId, "
			+ "price = :price, update_date = :updateDate WHERE book_id = :bookId";
}