package kr.or.simplebook.dao;

public class Sql {
	public static final String SELECT_USER = "SELECT username, password, enable "
			+ "FROM users WHERE username = :username";
	public static final String INSERT_USER = "INSERT INTO users VALUES(:username, :password, :enable)";
	public static final String INSERT_AUTH = "INSERT INTO authorities VALUES(:username, :auth)";
	public static final String SELECT_BOOKS_PAGING = "SELECT title, price, category, create_date, update_date "
			+ "FROM book JOIN categories USING(category_id) LIMIT :start, :end";
	public static final String SELECT_CATEGORIES = "SELECT category_id, category FROM categories";
}