/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cogsprok.addressbook;

import javax.swing.*;

/**
 *
 * @author John Wilson
 * Contact subclass adds Business specific attributes
 */
public class BizContact extends Contact {
    private String jobTitle;
    private String org;
    
    
    public void setJobTitle(String title) {
        jobTitle = title;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setOrg(String co) {
        org = co;
    }
    public String getOrg() {
        return org;
    }
    
    //Overrides superclass method to collect business contact info from JTextFields
    @Override
    public void dataCollector() {
        super.dataCollector();
        this.setJobTitle(ContactList.title.getText());
        this.setOrg(ContactList.org.getText());
    }
    
    //Overrides superclass method to display contact details with biz info
    @Override
    public void displayContact() {
        super.displayContact();
        ContactList.panel2.add(new JLabel(jobTitle + " at " + org + "  "));
        ContactList.panel2.revalidate();
        ContactList.panel2.repaint();
    }
    
}
