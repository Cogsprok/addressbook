package org.cogsprok.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
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

	public void addBizContact(String[] contact) throws Exception {
		
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/addressbook?"
							+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("insert into addressbook.BIZCONTACTS values (default, ?, ?, ?, ?, ?, ?, ?)");
		preparedStatement.setString(1, contact[0]);
		preparedStatement.setString(2, contact[1]);
		preparedStatement.setString(3, contact[2]);
		preparedStatement.setString(4, contact[3]);
		preparedStatement.setString(5, contact[4]);
		preparedStatement.setString(6, contact[5]);
		preparedStatement.setString(7, contact[6]);
		preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			throw e;
		} finally {
			close(connect);
		}
		
	}

	public HashMap<String, String> readBizContact() throws Exception {
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost/addressbook?"
						+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("SELECT FNAME, LNAME, ADDRESS, PHONE, EMAIL, TITLE, ORG FROM addressbook.BIZCONTACTS");
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
				contact.put("dob", results.getDate("DOB").toString());
			}
			
			
		}
		return contact;
	}
	
	private void close(AutoCloseable c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			// don't throw now as it might leave following closables in undefined state
		}
		
	}

	public void addPersContact(String[] contact) throws Exception {
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/addressbook?"
							+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("insert into addressbook.PERSCONTACTS values (default, ?, ?, ?, ?, ?, ?)");
		preparedStatement.setString(1, contact[0]);
		preparedStatement.setString(2, contact[1]);
		preparedStatement.setString(3, contact[2]);
		preparedStatement.setString(4, contact[3]);
		preparedStatement.setString(5, contact[4]);
		preparedStatement.setString(6, contact[5]);
		preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			throw e;
		} finally {
			close(connect);
		}
		
	}

	public HashMap<String, String> readPersContact() throws Exception {
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost/addressbook?"
						+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("SELECT FNAME, LNAME, ADDRESS, PHONE, EMAIL, DOB FROM addressbook.PERSCONTACTS");
		resultSet = preparedStatement.executeQuery();
		HashMap<String, String> contact = writeResults(resultSet);
		close(connect);
		return contact;
	}

	public TreeMap<String, String> listContacts() throws Exception {
		conNames.clear();
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost/addressbook?"
						+ "user=adbook&password=adbook");
		preparedStatement = connect
				.prepareStatement("SELECT LNAME, FNAME FROM addressbook.BIZCONTACTS"); 
		resultSet = preparedStatement.executeQuery();
		mapNames(resultSet);
		preparedStatement = connect
				.prepareStatement("SELECT LNAME, FNAME FROM addressbook.PERSCONTACTS;");
		resultSet = preparedStatement.executeQuery();
		mapNames(resultSet);
	
		close(connect);
		return conNames;
	}
	
	private void mapNames(ResultSet results) throws SQLException {
		while (results.next()) {
			conNames.put(results.getString("LNAME"), results.getString("FNAME"));
			}
	}
	
	

}
