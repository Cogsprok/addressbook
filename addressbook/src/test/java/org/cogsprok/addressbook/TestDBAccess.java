package org.cogsprok.addressbook;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map.Entry;

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

}
