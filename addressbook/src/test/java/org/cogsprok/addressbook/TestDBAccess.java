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
		HashMap<String, String> contact = new HashMap<>();
		contact.put("fname", "bTestFN");
		contact.put("lname", "bTestLN");
		contact.put("address",  "bTestAdd");
		contact.put("phone", "bTestPhone");
		contact.put("email", "bTestEm");
		contact.put("title", "bTestTitle");
		contact.put("org", "bTestOrg");
		try {
			dba.addBizContact(contact);
			noException = true;
		} catch (Exception e) {
			System.out.println("Write Biz: " + e.getMessage());
			noException = false;
		}
		assertTrue(noException);
		
		
	}
	
	@Test
	public void testReadBizContact() {
		try {
		HashMap<String, String> biz = dba.readBizContact();
		assertEquals("bTestFN", biz.get("fname"));
		assertEquals("bTestLN", biz.get("lname"));
		assertEquals("bTestAdd", biz.get("address"));
		for(Entry<String, String> entry : biz.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		} catch (Exception e) {
			System.out.println("Read Biz: " + e.getMessage());
			fail("Exception was thrown");
			
		}
		
	}
	
	@Test
	public void testWritePersContact() {
		boolean noException = false;
		HashMap<String, String> contact = new HashMap<>();
		contact.put("fname", "pTestFN");
		contact.put("lname", "pTestLN");
		contact.put("address",  "pTestAdd");
		contact.put("phone", "pTestPhone");
		contact.put("email", "pTestEm");
		contact.put("dob", "1976-03-04");
		try {
			dba.addPersContact(contact);
			noException = true;
		} catch (Exception e) {
			System.out.println("Write Pers: " + e.getMessage());
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
			System.out.println("Read Pers: " + e.getMessage());
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
			assertEquals("bTestFN", cons.get("bTestLN"));
			assertEquals("pTestFN", cons.get("pTestLN"));
		
		}catch (Exception e) {
			System.out.println("Display: " + e.getMessage());
			fail("Exception was thrown in DisplayContacts");
		}
		
	
		
	}

}
