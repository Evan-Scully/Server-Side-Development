package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Loan;

public enum LoanDAO {
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
	
	public Loan selectOne(String id) throws Exception {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM Loan where user_id ='" + id +"'");
		while(rs.next()) {

		if(rs.getString("loan_id").equals(id)) {
			
			Loan l = new Loan(rs.getString("loan_id"),rs.getString("book_id"),id);
			return l;
		}
		
		}
		return null;
	}

	// Saves Works
	public void save(Loan l) throws Exception{
		
		Connection conn = getConnection();
		PreparedStatement psmt = conn.prepareStatement("INSERT INTO Loan(book_id, user_id) VALUES (?,?)");
		
		psmt.setString(1, l.getBook_id());
		psmt.setString(2,  l.getUser_id());
		
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}
	
	public ArrayList<Loan> list(String id) throws Exception{
		ArrayList<Loan> listOfLoans = new ArrayList<Loan>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM loan where user_id = '" + id + "'");
		while (rs.next()) {
			Loan l = new Loan(rs.getString("loan_id"),rs.getString("book_id"),rs.getString("user_id"));
			listOfLoans.add(l);
		}
		return listOfLoans;
	}
	
	public void delete(String id,String book_id) throws Exception{
		
		Connection conn = getConnection();
		
		PreparedStatement psmt = conn.prepareStatement("DELETE from loan where book_id = ? and user_id = ?");
		
		psmt.setString(1, id);
		psmt.setString(2, book_id);
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}
	
	public void update(Loan l, String id) throws Exception{
		
		Connection conn = getConnection();
		PreparedStatement psmt = conn.prepareStatement("UPDATE loan SET book_id = ?, user_id = ? WHERE loan_id = " +
		id + "");
		
		
		psmt.setString(1, l.getBook_id());
		psmt.setString(2,  l.getUser_id());
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}
	
}
