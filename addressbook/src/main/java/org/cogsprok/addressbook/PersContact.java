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

	//Override superclass method to display personal contact specific info
    @Override
    public void displayContact(HashMap<String, String> conDetail) {
        super.displayContact(conDetail);
        ContactList.panel2.add(new JLabel("Birthday: " + conDetail.get("birth")));
        ContactList.panel2.revalidate();
        ContactList.panel2.repaint();   
    }
    
}
