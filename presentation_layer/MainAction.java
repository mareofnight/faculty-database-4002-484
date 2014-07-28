package presentation_layer;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import business_layer.*;
import java.util.ArrayList;

/**
 * @author Jessica Dopkant, Pawel Baltaziuk
 **/

public class MainAction implements ActionListener{
	// objects to play with
	JButton button;
	JMenuItem exit;
	JMenuItem about;
	JMenuItem howTo;
	User signedin;
	JComboBox recordTypeBox;
	JComboBox userBox;
	JTable databasedata;
	// constructor
	public MainAction(JMenuItem x, JMenuItem a, JMenuItem ht, User use, JComboBox rt, JComboBox u, JTable jt){
		exit = x;
		about = a;
		howTo = ht;
		signedin = use;
		recordTypeBox = rt;
		userBox = u;
		databasedata = jt;
	}
	public void actionPerformed(ActionEvent ae){
		// get the source object that called the AL
		Object source = ae.getSource();
		if(source instanceof JButton){
			// cast source back to a button
			button = (JButton)source;
			// get location/name of button (it's a string)
			String s = ae.getActionCommand();
			
			//*************************************************
			//******************   FETCH   ********************
			//*************************************************
			if(s.equalsIgnoreCase("fetch")){
				
				Records recordList;
				try {
					recordList = (Records)recordTypeBox.getSelectedItem();
					recordList.setUser((User)userBox.getSelectedItem());
					recordList.update(signedin);
					ArrayList<Record> recordArrayList = (ArrayList<Record>) recordList.getRecords();// this might cause bugs later...
					
					// if no records - put a no records message in the table
					if (recordArrayList.size() == 0) {
						
						FacultyTableModel newModel;
						
						String[] noRecordsHeader = {""};
						//String[][] noRecordsMessage;
						String userString = userBox.getSelectedItem().toString();
						String[][] noRecordsMessage = {{"There are no " + recordTypeBox.getSelectedItem().toString()
																	+ " records for " + userBox.getSelectedItem().toString() + "."}};
						newModel = new FacultyTableModel(noRecordsMessage, noRecordsHeader);
						
						databasedata.setModel(newModel);
					}
					
					// if there are records
					else {				
						
						// populate column headers
						String[] attrNames = recordArrayList.get(recordArrayList.size() - 1).getAttrNames();
						String[] columnNames;
						if (recordTypeBox.getSelectedItem() instanceof Users) {
							columnNames = new String[(attrNames.length)];// populate column headers for users - no UserID
							columnNames[0] = "Record Object";
							for (int i=1; i<attrNames.length; i++) {
								columnNames[i] = attrNames[i];
							}
						}
						else {
							columnNames = new String[(attrNames.length - 1)];// popularte column headers for non-users - no UserID or recordID
							columnNames[0] = "Record Object";
							for (int i=2; i<attrNames.length; i++) {
								columnNames[i - 1] = attrNames[i];
							}
						}
						
						// populate table content
						Object[][] dataArray = new Object[recordArrayList.size()][columnNames.length];
						for (int i=0; i<recordArrayList.size(); i++) {// for each record
							dataArray[i][0] = recordArrayList.get(i);
							ArrayList<String> valueList = recordArrayList.get(i).getValues();
							int offset = 1;
							if (recordTypeBox.getSelectedItem() instanceof Users) { offset = 0; }
							for (int j=1; j<(valueList.size() - offset); j++) {
								// for Scholarships, check if it's a pub, and change the positions of the array itmes if it is
								if (recordTypeBox.getSelectedItem() instanceof Scholarships && j == 4 && valueList.size() == 6) {
									dataArray[i][j] = "";// amount is blank
									dataArray[i][j+1] = valueList.get(j + offset);// put status in index 6 instead of index 5, to match grants
									j = valueList.size() + 500;
								}
								else {
									dataArray[i][j] = valueList.get(j + offset);
								}
							}
						}
						
						// make JTableModel
						FacultyTableModel newModel = new FacultyTableModel(dataArray, (Object[])columnNames);
						
						// put the model in the table
						databasedata.setModel(newModel);
						
						// hide object column
						TableColumn objectColumn = databasedata.getColumnModel().getColumn(0);
						objectColumn.setMinWidth(0);
						objectColumn.setMaxWidth(0);
						objectColumn.setPreferredWidth(0);
						
						// setup column widths
						TableColumn yearColumn = databasedata.getColumnModel().getColumn(1);
						yearColumn.setPreferredWidth(1);
					}
				}
				catch (Exception x){}
				
				
				// update the jtable from the result set (check b_layer methods and array-casting options)
				
				// after you update the table, use 'databasedata.doLayout()' so it'll fit in the table right
				databasedata.doLayout();
				// then tell maingui to pack() again so it'll resize the window
				
			}
			
			//*************************************************
			//*******************   NEW   *********************
			//*************************************************
			else if(s.equalsIgnoreCase("new")){ // if you're making a new entry...
				String recordtype = recordTypeBox.getSelectedItem().toString();
				if(recordtype.equalsIgnoreCase("teaching")){ // and you're looking at courses
					new DetailPage(DetailPage.COURSE, signedin);
				}
				else if(recordtype.equalsIgnoreCase("scholarship")){
					// show an option pane asking whether grants, pubs, or back
					String[] paneOptions={"Grant", "Publication", "Back"};
					int choice = JOptionPane.showOptionDialog(null, "Do you want to create a new Grant or a new Publication?", "New Option", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, paneOptions, null);
					// this returns a value depending on what is chosen.
					if(choice == JOptionPane.YES_OPTION){ // grants
						new DetailPage(DetailPage.GRANT, signedin);
					}
					else if(choice == JOptionPane.NO_OPTION){ // pubs
						new DetailPage(DetailPage.PUB, signedin);
					}
				}
				else if(recordtype.equalsIgnoreCase("service")){ // looking at services
					new DetailPage(DetailPage.SERVICE, signedin);
				}
				else if(recordtype.equalsIgnoreCase("kudos")){ // looking at kudos
					new DetailPage(DetailPage.KUDO, signedin);
				}
				else if(recordtype.equalsIgnoreCase("users")){ // looking at users
					new DetailPage(DetailPage.USER, signedin);
				}
			}
			
			//************************************************
			//******************   EDIT   ********************
			//************************************************
			else if(s.equalsIgnoreCase("edit")){ // if you're editing
				// get the currently selected record
				int row = databasedata.getSelectedRow();
				try {
					Record currentRecord = (Record)databasedata.getValueAt(row, 0);
					
					//open detail view, editing allowed
					new DetailPage(currentRecord, (true), signedin);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Please select a record to view.", "", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			//************************************************
			//******************   VIEW   ********************
			//************************************************
			else if(s.equalsIgnoreCase("view")){ // if viewing records without making changes
				// get the currently selected record
				int row = databasedata.getSelectedRow();
				try {
					Record currentRecord = (Record)databasedata.getValueAt(row, 0);
					
					//open detail view, no editing
					new DetailPage(currentRecord, (false), signedin);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Please select a record to view.", "", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else if(source == exit){
			System.exit(0);
		}
		else if(source == about){
			// an option pane giving details about the program
			JOptionPane.showMessageDialog(null, "Coded by Kim, Jessica Dopkant & Pawel \nFor Database Client Server Implementation", "About the Program", JOptionPane.INFORMATION_MESSAGE, null);
		}
		else if(source == howTo){
			// an option pane explaining how to do specific things in the program
			String help = "How to view a specific user's records:\nSelect the type of records you wish to view in the first drop-down box and\nthe particular user's records you wish to see in the second (if you are a \nfaculty, you will only see your own records), then press the 'View Records' button.\nIf there are no records of that type to view for that user, \nthen you will see a message saying so.\nYou may select a record and click the 'View Details' button in the bottom right\nto view more details about a specific record.\n\nHow to create a new record (Administration and Department Chair only):\nFirst view records of the specific type of record you wish to create, then click\nthe new button in the bottom right.\nSelect the user you wish to create a new record for from the drop-down box,\nand fill in the info into the specific fields. \nNote: There is a character limit for some info fields.\n\nHow to edit an existing record (Administration and Department Chair only):\nFirst view the records of the specific type and user you wish to edit, then click\nthe specific record you wish to change to select it,\nthen click the 'edit' button in the bottom right.";
			JOptionPane.showMessageDialog(null, help, "How to...", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
}
