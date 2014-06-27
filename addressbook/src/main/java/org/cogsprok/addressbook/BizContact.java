
package org.cogsprok.addressbook;

import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author John Wilson
 * Contact subclass adds Business specific attributes
 */
public class BizContact extends Contact {
	
	public BizContact() {
		this.setType("Business");
	} 
    
    //Overrides superclass method to display contact details with biz info
    @Override
    public void displayContact(HashMap<String, String> conDetail) {
        super.displayContact(conDetail);
        ContactList.panel2.add(new JLabel(conDetail.get("title") + " at " + conDetail.get("org") + "  "));
        ContactList.panel2.revalidate();
        ContactList.panel2.repaint();
    }
    
}
