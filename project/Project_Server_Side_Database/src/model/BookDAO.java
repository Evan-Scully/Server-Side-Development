package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;

public enum BookDAO {
	instance;

	//CRUD
	//Create - Insert - save
	//Read - Select - list
	//Update - Update - update
	//Delete - Delete - remove
	
	public Connection getConnection() throws Exception {

		
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/one", "sa", "");
			
			return con;
		}
	
	public Book selectOne(String id) throws Exception {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK where id ='" + id +"'");
		while(rs.next()) {

		if(rs.getString("id").equals(id)) {
			
			Book b = new Book(id,rs.getString("name"),rs.getString("author"),rs.getString("series"), rs.getString("ISBN"));
			
			return b;
		}
		
		}
		return null;
	}

	// Saves Works
	public void save(Book b) throws Exception{
		
		Connection conn = getConnection();
		PreparedStatement psmt = conn.prepareStatement("INSERT INTO BOOK(name, author, series, ISBN) VALUES (?,?,?,?)");
		
		psmt.setString(1, b.getName());
		psmt.setString(2,  b.getAuthor());
		psmt.setString(3,  b.getSeries());
		psmt.setString(4,  b.getISBN());
		
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}
	
	public ArrayList<Book> list() throws Exception{
		ArrayList<Book> listOfBooks = new ArrayList();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK");
		while (rs.next()) {
			Book b = new Book(rs.getString("id"), rs.getString("name"),rs.getString("author"),rs.getString("series"), rs.getString("ISBN"));
			listOfBooks.add(b);
		}
		return listOfBooks;
	}
	
	public void delete(String id) throws Exception{
		
		Connection conn = getConnection();
		
		PreparedStatement psmt = conn.prepareStatement("DELETE from BOOK where id = " + id);
		
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}
	
	public void update(Book b, String id) throws Exception{
		
		Connection conn = getConnection();
		PreparedStatement psmt = conn.prepareStatement("UPDATE BOOK SET name = ?, author = ?, series = ?, ISBN = ? WHERE id = " +
		id + "");
		
		psmt.setString(1, b.getName());
		psmt.setString(2, b.getAuthor());
		psmt.setString(3, b.getSeries());
		psmt.setString(4, b.getISBN());
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}
	

}
