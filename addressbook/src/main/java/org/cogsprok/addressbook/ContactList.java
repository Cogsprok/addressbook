
package org.cogsprok.addressbook;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;


/**
 *
 * @author John Wilson
 */
public class ContactList {
    // Collection of client ID key and Contact object value pairs
    HashMap<Integer, Contact> contacts = new HashMap<Integer, Contact>();
    
    JFrame main;
    //Declared static to allow access from Contact class methods
    //There is probably a better/safer way to pass between classes
    static JPanel panel2;
    static JTextField fName;
    static JTextField lName;
    static JTextField address;
    static JTextField phone;
    static JTextField email;
    static JTextField title;
    static JTextField org;
    static JTextField birth;
    static JTextField cid;
    
    
    //public method to access getText of local fields
    public String getInfo(JTextField field) {
    	return field.getText();
    }
    //Iterates through HashMap, getting Contacts and displaying first + last name
    private void displayContacts() {
        for(int i = 1; i <= contacts.size(); i++) {
            Contact c = (Contact) contacts.get(i);
            panel2.add(new JLabel(c.getContactId() + "    " + c.getFirstName() 
            + " " + c.getLastName() + "    (" + c.getType().substring(0, 1) + ")"));  
        }
    }
    
    //Main Window and Menu Layout
    public void menuScreen() {
    main = new JFrame();
    JPanel panel1 = new JPanel();
    panel1.setBackground(Color.lightGray);
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
    panel2 = new JPanel(); 
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
    JButton addBiz = new JButton("Add Business Contact");
    addBiz.addActionListener((ActionListener) new BizListener());
    JButton addPers = new JButton("Add Personal Contact");
    addPers.addActionListener(new PersListener());
    JButton display = new JButton("Display Contact List");
    display.addActionListener(new DisplayListener());
    JButton quit = new JButton("Quit");
    quit.addActionListener(new QuitListener());
    panel1.add(addBiz);
    panel1.add(Box.createVerticalStrut(10));
    panel1.add(addPers);
    panel1.add(Box.createVerticalStrut(10));
    panel1.add(display);
    panel1.add(Box.createVerticalStrut(10));
    panel1.add(quit);
    panel2.add(new JLabel("Welcome to your Contact List."));
    panel2.add(new JLabel("Please choose an option to continue."));
    main.getContentPane().add(BorderLayout.WEST, panel1);
    main.getContentPane().add(BorderLayout.EAST, panel2);
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setSize(600, 400);
    main.setVisible(true); 
    }

    
    //Listens for button click to display a contacts info
    class DisListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String c = cid.getText();
            int cnum = Integer.parseInt(c);
            Contact disCont = contacts.get(cnum);
            disCont.displayContact();
            cid.setText("");
        }
    }
    //Listens for return/enter key to display a contact's info
    class CidListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String c = cid.getText();
            int cnum = Integer.parseInt(c);
            Contact disCont = contacts.get(cnum);
            disCont.displayContact();
            cid.setText("");
        }
    }
    
    /* Save button on Add New Business Contact form
    *  Creates new BizContact object, creates ContactId/Key
    *  calls dataCollector() to set variables
    *  Adds completed BizContact object to HashMap
    *  Clears fields for next entry
    */
    class BizSaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BizContact contact = new BizContact();
            contact.setContactId(contacts.size() + 1);
            contact.setType("Business");
            contact.dataCollector();
            contacts.put((contacts.size() +1), contact);
            fName.setText("");
            lName.setText("");
            address.setText("");
            phone.setText("");
            email.setText("");
            title.setText("");
            org.setText("");
        }
    }
    
    /* Save Button on Add New Personal Contact form
    *  Creates new PersContact object, sets ContactId/Key 
    *  calls dataCollector() to set variables
    *  Adds completed PersContact object to HashMap 
    *  Clears fields for next entry
    */
    class PersSaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PersContact contact = new PersContact();
            contact.setContactId(contacts.size() + 1);
            contact.setType("Personal");
            contact.dataCollector();
            contacts.put((contacts.size() +1), contact);
            fName.setText("");
            lName.setText("");
            address.setText("");
            phone.setText("");
            email.setText("");
            birth.setText("");
            
           
        }
    }
    
    //Add New Business Contact Button
    class BizListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panel2.removeAll();
            panel2.add(new JLabel("New Business Contact"));
            panel2.add(Box.createVerticalStrut(5));
            panel2.add(new JLabel("First Name:"));
            fName = new JTextField(35);
            panel2.add(fName);
            panel2.add(new JLabel("Last Name:"));
            lName = new JTextField(35);
            panel2.add(lName);
            panel2.add(new JLabel("Address:"));
            address = new JTextField(35);
            panel2.add(address);
            panel2.add(new JLabel("Phone:"));
            phone = new JTextField(35);
            panel2.add(phone);
            panel2.add(new JLabel("E-mail:"));
            email = new JTextField(35);
            panel2.add(email);
            panel2.add(new JLabel("Job Title:"));
            title = new JTextField(35);
            panel2.add(title);
            panel2.add(new JLabel("Organization:"));
            org = new JTextField(35);
            panel2.add(org);
            panel2.add(Box.createVerticalStrut(5));
            JButton save = new JButton("Save");
            save.addActionListener(new BizSaveListener());
            panel2.add(save);
            panel2.revalidate();
            panel2.repaint();
            
            
            
            
        }
    }
    
    //Add New Personal Contact Button
    class PersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panel2.removeAll();
            panel2.add(new JLabel("New Personal Contact"));
            panel2.add(Box.createVerticalStrut(5));
            panel2.add(new JLabel("First Name:"));
            fName = new JTextField(35);
            panel2.add(fName);
            panel2.add(new JLabel("Last Name:"));
            lName = new JTextField(35);
            panel2.add(lName);
            panel2.add(new JLabel("Address:"));
            address = new JTextField(35);
            panel2.add(address);
            panel2.add(new JLabel("Phone:"));
            phone = new JTextField(35);
            panel2.add(phone);
            panel2.add(new JLabel("E-mail:"));
            email = new JTextField(35);
            panel2.add(email);
            panel2.add(new JLabel("Date of Birth:"));
            birth = new JTextField(35);
            panel2.add(birth);
            panel2.add(Box.createVerticalStrut(5));
            JButton save = new JButton("Save");
            save.addActionListener(new PersSaveListener());
            panel2.add(save);
            panel2.revalidate();
            panel2.repaint();
        }
    }

    // Display Contacts Button Listener
    class DisplayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            panel2.removeAll();
            panel2.add(new JLabel("ID    Name"));
            displayContacts();
            panel2.add(Box.createVerticalStrut(15));
            JPanel panel3 = new JPanel();
            panel3.setLayout(new FlowLayout());
            panel2.add(panel3);
            cid = new JTextField(5);
            cid.addActionListener(new CidListener());
            JButton display = new JButton("Display");
            display.addActionListener(new DisListener());
            panel3.add(new JLabel("Enter ID for full Contact Info"));
            panel3.add(cid);
            panel2.add(display);
            panel2.add(Box.createVerticalStrut(15));
            panel2.revalidate();
            panel2.repaint(); 
        }
    }

    //Quit Button Listener
    class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
         main.dispose();
        }
    }
}
    

