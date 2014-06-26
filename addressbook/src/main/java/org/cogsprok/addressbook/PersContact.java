/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cogsprok.addressbook;

import java.util.HashMap;

import javax.swing.JLabel;

/**
 *
 * @author John Wilson
 */
public class PersContact extends Contact {
	public PersContact() {
		this.setType("Personal");
	}
	
    private String dob;
        
    public void setDob(String bday) {
        dob = bday;
    }
    public String getDob() {
        return dob;
    }
    
    //Overrides superclass method to get DOB and set variable
    @Override
    public void dataCollector() {
        super.dataCollector();
        //this.setDob(Contact.fieldMap.get("birth"));
        
    }
    
    public void addContact()  {
    	try {
    		DBAccess dba = new DBAccess();
    		dba.dbConnect();
    		dba.addPersContact(this.getFieldMap());
    	} catch (Exception e) {
    		System.out.println("PaddCont: " + e.getMessage());
    	}
    }
    
    //Overrides superclass method to display DOB with contact details
    @Override
    public void displayContact(HashMap<String, String> conDetail) {
        super.displayContact(conDetail);
        ContactList.panel2.add(new JLabel("Birthday: " + conDetail.get("birth")));
        ContactList.panel2.revalidate();
        ContactList.panel2.repaint();   
    }
    
}
