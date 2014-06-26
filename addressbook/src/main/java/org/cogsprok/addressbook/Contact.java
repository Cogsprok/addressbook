
package org.cogsprok.addressbook;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;

/**
 *
 * @author John Wilson
 */
abstract class Contact {
	private HashMap<String, String> fieldMap = new HashMap<>();
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private int contactId;
    private String type;
    
    public void setContactId(int id) {
        contactId = id;
    }
    public int getContactId() {
        return contactId;
    }
    public void setFirstName(String name) {
        firstName = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String name) {
        lastName = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setAddress(String input) {
        address = input;
    }
    public String getAddress() {
        return address;
    }
    public void setPhone(String input) {
        phone = input;
    }
    public String getPhone() {
        return phone;
    }
    public void setEmail(String mail) {
        email = mail;
    }
    public String getEmail() {
        return email;
    }
    public void setType(String t) {
        type = t;
    }
    public String getType() {
        return type;
    }
    
    public HashMap<String, String> getFieldMap() {
    	return fieldMap;
    }
    
    //Takes text from JText fields for Add Contact, sets variables
    public void dataCollector() {
    	for(Component c : ContactList.panel2.getComponents()) {
    		if(c instanceof JTextField) {
    			fieldMap.put(c.getName(), ((JTextField) c).getText());
    		}
    	}
    	
    	
        //this.setFirstName(fields.get("fName"));
        //this.setLastName(fields.get("lName"));
        //this.setAddress(fields.get("address"));
        //this.setPhone(fields.get("phone"));
        //this.setEmail(fields.get("email"));
       
    }
    
    //Repaints panel2 with contact details
    public void displayContact() {
            ContactList.panel2.removeAll();
            ContactList.panel2.add(new JLabel(type + " Contact:"));
            ContactList.panel2.add(Box.createVerticalStrut(5));
            ContactList.panel2.add(new JLabel(firstName + " " + lastName + "  "));
            ContactList.panel2.add(new JLabel(address + "  "));
            ContactList.panel2.add(new JLabel("Phone:" + phone + "  E-mail: " 
                    + email + "  "));
            ContactList.panel2.revalidate();
            ContactList.panel2.repaint();
    }   
}
