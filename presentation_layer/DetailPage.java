package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;

import business_layer.*;

/**
 * @author Jessica Dopkant, Pawel Baltaziuk
 **/

public class DetailPage extends JFrame implements ActionListener {

    public static final int COURSE = 1;
    public static final int GRANT = 2;
    public static final int KUDO = 3;
    public static final int PUB = 4;
    public static final int SERVICE = 5;
    public static final int USER = 6;
    
    private Record record;
    private User user;
    private String[] fieldNames;
    private boolean newRecord;
    private boolean editable;
    private JPanel userPanel;
    private JPanel dataPanel;
    private JComboBox usersCombo;
    private HashMap<String, JTextComponent> inputValues;
    
    /**
     * Default constructor for the window
     */
    public DetailPage(User user, boolean newUser) {
        this.user = user;
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(200, 75);
        inputValues = new HashMap<String, JTextComponent>();
        userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Dropdown for user selection
        // only if not adding new user
        if(!newUser) {
            Users users = new Users();
            users.update(new String[]{"1", User.CHAIR});
            ArrayList<Record> userList = users.getRecords();
            usersCombo = new JComboBox(userList.toArray(new User[userList.size()]));
            userPanel.add(createLabel("User"));
            userPanel.add(usersCombo);
            add(userPanel, BorderLayout.NORTH);
        }
        
        // main content pain
        dataPanel = new JPanel(new GridLayout(3,1));
        add(dataPanel, BorderLayout.CENTER);
    }
    
    /**
     * Constructor for detail page when adding new record
     * @param type - new records type (static class values should be used)
     */
    public DetailPage(int type, User user) {
        this(user, type == USER);
        newRecord = true;
        editable = true;
        setTitle("Add Record");
        switch(type) {
            case COURSE : record = new Course(); break;
            case GRANT : record = new Grant(); break;
            case KUDO : record = new Kudo(); break;
            case PUB : record = new Pub(); break;
            case SERVICE : record = new Service(); break;
            case USER : record = new User(); break;
        }
        recordView();
        addSaveButton();
        pack();
    }
    
    /**
     * Constructor for creating detail page view to edit/view a record
     * @param record
     * @param editable
     */
    public DetailPage(Record record, boolean editable, User user) {
        this(user, false);
        this.record = record;
        this.editable = editable;
        newRecord = false;
        String uid = record.getAttr(0);
        
        // set user
        for(int i = 0; i < usersCombo.getItemCount(); i++) {
            User u = (User)usersCombo.getItemAt(i);
            if(uid.equals(u.getAttr("UserId"))) {
                usersCombo.setSelectedIndex(i);
                break;
            }
        }
        usersCombo.setEnabled(false);
        
        setTitle("View Record");
        recordView();
        // make save button
        if(editable) { 
            addSaveButton();
            setTitle("Edit title");
        }
        pack();
    }

    /**
     * Adds save button if needed (only for edit/add record)
     */
    private void addSaveButton() {
        JPanel panel = new JPanel();
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(this);
        panel.add(saveBtn);
        dataPanel.add(panel);
    }
    
    private void recordView() {
        fieldNames = record.getAttrNames();

        JPanel view = new JPanel(new GridLayout(fieldNames.length-1, 1));
        
        for(String field : fieldNames) {
            if(newRecord) {
                addTextPanel(view, field, "");
            } else {
                addTextPanel(view, field, record.getAttr(field));
            }
        }
        
        dataPanel.add(view);
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text+":");
        label.setMinimumSize(new Dimension(100,20));
        label.setMaximumSize(new Dimension(100,20));
        label.setPreferredSize(new Dimension(100,20));
        return label;
    }
    
    private JTextField createTextfield(String name, String text) {
        JTextField textfield = new JTextField();
        textfield .setDocument(new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if(getLength() + str.length() <= 45)
                    super.insertString(offs, str, a);
            }
        });
        textfield.setName(name);
        textfield.setText(text);
        textfield.setColumns(20);
        textfield.setEditable(editable && !name.endsWith("ID"));
        inputValues.put(name, textfield);
        return textfield;
    }
    
    private JScrollPane createTextArea(String name, String text) {
        JTextArea textarea = new JTextArea(text);
        textarea.setName(name);
        textarea.setColumns(20);
        textarea.setRows(4);
        textarea.setEditable(editable && !name.endsWith("ID"));
        inputValues.put(name, textarea);
        return new JScrollPane(textarea);
    }
    
    private void addTextPanel(JPanel parent, String name, String value) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(createLabel(name));
        if(name.equalsIgnoreCase("tease") || name.equalsIgnoreCase("kudo")) {
            panel.add(createTextArea(name, value));
        } else panel.add(createTextfield(name, value));
        if(!name.equalsIgnoreCase("userid")) {
            parent.add(panel);
        }
    }
    
    private void setRecordValues() {
        for(String name : fieldNames) {
            record.setAttr(name, inputValues.get(name).getText());
        }
        if(!(record instanceof User) || !newRecord) {
            User user = (User)usersCombo.getSelectedItem();
            record.setAttr("UserId", user.getAttr("UserId"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        setRecordValues();
        if(newRecord) {
            record.insert(user);
        } else {
            record.edit(user);
        }
        dispose();
    }
    
}
