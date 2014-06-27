
package org.cogsprok.addressbook;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.*;

/**
 *
 * @author John Wilson
 */
abstract class Contact {
	private HashMap<String, String> fieldMap = new HashMap<>();
    private String type;
    
    public void setType(String t) {
        type = t;
    }
    public String getType() {
        return type;
    }
    
    public HashMap<String, String> getFieldMap() {
    	return fieldMap;
    }

    
    //Takes text from JText fields, collects in HashMap
    public void dataCollector() {
    	for(Component c : ContactList.panel2.getComponents()) {
    		if(c instanceof JTextField) {
    			fieldMap.put(c.getName(), ((JTextField) c).getText());
    		}
    	}
    }
    
    //Passes dataCollector hashMap to DB addContact
    public void addContact()  {
    	try {
    		DBAccess dba = new DBAccess();
    		dba.dbConnect();
    		dba.addContact(fieldMap, type);
    	} catch (Exception e) {
    		System.out.println("BaddCont: " + e.getMessage());
    	}
    }
    
    //Repaints panel2 with contact details
    public void displayContact(HashMap<String, String> conDetail) {
            ContactList.panel2.removeAll();
            ContactList.panel2.add(new JLabel(type + " Contact:"));
            ContactList.panel2.add(Box.createVerticalStrut(5));
            ContactList.panel2.add(new JLabel(conDetail.get("fname") + " " + conDetail.get("lname") + "  "));
            ContactList.panel2.add(new JLabel(conDetail.get("address") + "  "));
            ContactList.panel2.add(new JLabel("Phone:" + conDetail.get("phone") + "  E-mail: " 
                    + conDetail.get("email") + "  "));
            ContactList.panel2.revalidate();
            ContactList.panel2.repaint();
    }   
}
