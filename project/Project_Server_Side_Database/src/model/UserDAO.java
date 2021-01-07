package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;

public enum UserDAO {
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
	
	public User selectOne(String email) throws Exception {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM USER where email ='" + email +"'");
		while(rs.next()) {

		if(rs.getString("email").equals(email)) {
			
			User u = new User(rs.getString("id"),rs.getString("email"),rs.getString("username"), rs.getString("password"));
			return u;
		}
		
		}
		return null;
	}
	
	public User selectId(String id) throws Exception {
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER where id ='" + id +"'");
			while(rs.next()) {
	
			if(rs.getString("id").equals(id)) {
				
				User u = new User(id,rs.getString("email"),rs.getString("username"), rs.getString("password"));
				return u;
			}
			
			}
			return null;
		}
	
	public boolean login(String email, String password) throws Exception {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM USER where email ='" + email +"'");
		while(rs.next()) {
			if(rs.getString("email").equals(email) && rs.getString("password").equals(password)) {
				return true;
			}
		}
		return false;
	}

	
	public void save(User u) throws Exception{
		
		Connection conn = getConnection();
		
		PreparedStatement psmt = conn.prepareStatement("INSERT INTO USER(username, email, password) VALUES (?,?,?)");
		
		
		psmt.setString(1, u.getEmail());
		psmt.setString(2,  u.getUsername());
		psmt.setString(3,  u.getPassword());
		
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}
	
	public ArrayList<User> list() throws Exception{
		ArrayList<User> listOfUsers = new ArrayList();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM USER");
		while (rs.next()) {
			User u = new User("null", rs.getString("username"), rs.getString("email"), rs.getString("password"));
			listOfUsers.add(u);

		}
		return listOfUsers;
	}
	
public void delete(String id) throws Exception{
		
		Connection conn = getConnection();
		
		PreparedStatement psmt = conn.prepareStatement("DELETE from USER where id = " + id);
		
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}
	
	public void update(User u, String id) throws Exception{
		
		Connection conn = getConnection();
		PreparedStatement psmt = conn.prepareStatement("UPDATE USER SET username = ?, email = ?, password = ? WHERE id = " +
		id + "");
		
		
		psmt.setString(1, u.getUsername());
		psmt.setString(2,  u.getEmail());
		psmt.setString(3,  u.getPassword());
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	}

}
