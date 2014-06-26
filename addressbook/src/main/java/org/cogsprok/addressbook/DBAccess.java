package org.cogsprok.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;

public class DBAccess {
	
	
	
	
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private TreeMap<String, String> conNames = new TreeMap<String, String>();
	
	

	public void dbConnect() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/addressbook?"
							+ "user=adbook&password=adbook");
		} catch (ClassNotFoundException e) {
			throw e;
		} finally {
			close(connect);
		}
		
	}

	public void addBizContact(HashMap<String,String> contact) throws Exception {
		
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/addressbook?"
							+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("insert into addressbook.CONTACTS values (default, ?, ?, ?, ?, ?, NULL, ?, ?, ?)");
		preparedStatement.setString(1, contact.get("fName"));
		preparedStatement.setString(2, contact.get("lName"));
		preparedStatement.setString(3, contact.get("address"));
		preparedStatement.setString(4, contact.get("phone"));
		preparedStatement.setString(5, contact.get("email"));
		preparedStatement.setString(6, contact.get("title"));
		preparedStatement.setString(7, contact.get("org"));
		preparedStatement.setString(8, "B");
		preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			throw e;
		} finally {
			close(connect);
		}
		
	}

	
	
	public void addPersContact(HashMap<String, String> contact) throws Exception {
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/addressbook?"
							+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("insert into addressbook.CONTACTS values (default, ?, ?, ?, ?, ?, ?, NULL, NULL, ?)");
		preparedStatement.setString(1, contact.get("fName"));
		preparedStatement.setString(2, contact.get("lName"));
		preparedStatement.setString(3, contact.get("address"));
		preparedStatement.setString(4, contact.get("phone"));
		preparedStatement.setString(5, contact.get("email"));
		preparedStatement.setString(6, contact.get("birth"));
		preparedStatement.setString(7, "P");
		preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			throw e;
		} finally {
			close(connect);
		}
		
	}
	
	public HashMap<String, String> readBizContact(int id) throws Exception {
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost/addressbook?"
						+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("SELECT FNAME, LNAME, ADDRESS, PHONE, EMAIL, TITLE, ORG FROM addressbook.CONTACTS WHERE ID = ?");
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		HashMap<String, String> contact = writeResults(resultSet);
		close(connect);
		return contact;
		
	}

	public HashMap<String, String> readPersContact(int id) throws Exception {
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost/addressbook?"
						+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("SELECT FNAME, LNAME, ADDRESS, PHONE, EMAIL, DOB FROM addressbook.CONTACTS WHERE ID = ?");
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		HashMap<String, String> contact = writeResults(resultSet);
		close(connect);
		return contact;
	}
	
	private HashMap<String, String> writeResults (ResultSet results) throws SQLException {
		HashMap<String, String> contact = new HashMap<String, String>();
		while (results.next()) {
			contact.put("fname", results.getString("FNAME"));
			contact.put("lname", results.getString("LNAME"));
			contact.put("address", results.getString("ADDRESS"));
			contact.put("phone", results.getString("PHONE"));
			contact.put("email", results.getString("EMAIL"));
			
			try {
				contact.put("title", results.getString("TITLE"));
				contact.put("org", results.getString("ORG"));
			} catch (SQLException notbiz) {
				contact.put("birth", results.getDate("DOB").toString());
			}
		}
		return contact;
	}

	public ResultSet listContacts() throws Exception {
		conNames.clear();
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost/addressbook?"
						+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("SELECT LNAME, FNAME, ID, TYPE FROM addressbook.CONTACTS ORDER BY LNAME"); 
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}
	
	public void remoteClose() {
		close(connect);
	}
	
	private void close(AutoCloseable c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			System.out.println("Close EX: " + e.getMessage());
		}
		
	}
	
	

}
