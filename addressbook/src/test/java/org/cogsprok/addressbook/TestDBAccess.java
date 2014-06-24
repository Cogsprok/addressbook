package org.cogsprok.addressbook;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;

public class TestDBAccess {
	
	private DBAccess dba = new DBAccess();

	@Test
	public void connectToDB() {
	
		boolean noException = false;
		try {
			dba.dbConnect();
			noException = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			noException = false;
		}
		assertTrue(noException);
		
	}
	
	@Test
	public void testWriteBizContact() {
		boolean noException = false;
		String[] contact = {"jTestFN", "jTestLN", "jTestAdd", "jTestPhone", "jTestEm", "jTestTitle", "jTestOrg" };
		try {
			dba.addBizContact(contact);
			noException = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			noException = false;
		}
		assertTrue(noException);
		
		
	}
	
	@Test
	public void testReadBizContact() {
		try {
		HashMap<String, String> biz = dba.readBizContact();
		assertEquals("jTestFN", biz.get("fname"));
		assertEquals("jTestLN", biz.get("lname"));
		assertEquals("jTestAdd", biz.get("address"));
		for(Entry<String, String> entry : biz.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("Exception was thrown");
			
		}
		
	}
	
	@Test
	public void testWritePersContact() {
		boolean noException = false;
		String[] contact = {"pTestFN", "pTestLN", "pTestAdd", "pTestPhone", "pTestEm", "1976-06-03" };
		try {
			dba.addPersContact(contact);
			noException = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			noException = false;
		}
		assertTrue(noException);
		
	}
	
	@Test
	public void testReadPersContact() {
		try {
		HashMap<String, String> pers = dba.readPersContact();
		assertEquals("pTestFN", pers.get("fname"));
		assertEquals("pTestLN", pers.get("lname"));
		assertEquals("pTestAdd", pers.get("address"));
		for(Entry<String, String> entry : pers.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail("Exception was thrown");
			
		}
		
	}
	
	@Test
	public void testDisplayContacts() {
		try {
			TreeMap<String, String> cons = dba.listContacts();
			for(Entry<String, String> entry : cons.entrySet()) {
				System.out.println(entry.getKey() + ", " + entry.getValue());
			} 
			assertEquals("jTestFN", cons.get("jTestLN"));
			assertEquals("pTestFN", cons.get("pTestLN"));
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
			fail("Exception was thrown in DisplayContacts");
		}
		
	
		
	}

}
