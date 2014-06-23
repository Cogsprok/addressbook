/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cogsprok.addressbook;

import javax.swing.JLabel;

/**
 *
 * @author John Wilson
 */
public class PersContact extends Contact {
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
        this.setDob(ContactList.birth.getText());
    }
    
    //Overrides superclass method to display DOB with contact details
    @Override
    public void displayContact() {
        super.displayContact();
        ContactList.panel2.add(new JLabel("Birthday: " + dob));
        ContactList.panel2.revalidate();
        ContactList.panel2.repaint();   
    }
    
}
