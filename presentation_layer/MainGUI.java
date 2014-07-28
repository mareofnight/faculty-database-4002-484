package presentation_layer;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import business_layer.*;

/**
 * @author Jessica Dopkant, Pawel Baltaziuk
 **/

public class MainGUI extends JFrame{
	/*list my component objects*/
	JButton fetch;
	JButton newb;
	JButton edit;
	JButton viewDetails;
	JComboBox record;
	JComboBox user;
	JTable data;
	JScrollPane scroll;
	JPanel north;
	JPanel center;
	JPanel south;
	BorderLayout bl;
	JMenu file;
	JMenuBar menu;
	JMenuItem exit;
	JMenu help;
	JMenuItem about;
	JMenuItem howTo;
	JLabel logo;
	// business layer object
	User current;
	// Actionlistener
	MainAction ma;
	
	// record lidst classes
	Courses teaching = new Courses();
	Services service = new Services();
	Scholarships scholarship = new Scholarships();
	Kudos kudos = new Kudos();
	Users users = new Users();
	
	/* gui constructor */
	public MainGUI(User cur){
		// gui menu bar
		menu = new JMenuBar();
		file = new JMenu("File");
		exit = new JMenuItem("Exit", KeyEvent.VK_X);
		file.add(exit);
		menu.add(file);
		help = new JMenu("Help");
		about = new JMenuItem("About");
		howTo = new JMenuItem("How to...");
		help.add(about);
		help.add(howTo);
		menu.add(help);
		setJMenuBar(menu);
		
		// layout stuff
		bl = new BorderLayout();
		setLayout(bl);
		// panels
		north = new JPanel(new BorderLayout());
		center = new JPanel();
		south = new JPanel();		
		
		// set current user
		current = cur;
		
		// gui components
		logo = new JLabel("Faculty Activity");
			logo.setFont(new Font( logo.getFont().getName(), logo.getFont().getStyle(), 20 ));
		record = new JComboBox();
		user = new JComboBox(); 
		/* NOTE: THESE COMBO BOXES ARE EMPTY. MANUALLY ADDED CHILDREN TO THE DROPDOWNS BELOW   */
		fetch = new JButton("View Records");
		
		/* table stuff */
		data = new JTable();
		scroll = new JScrollPane(data);
		data.setFillsViewportHeight(true);
		data.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // set table to single-row selection
		
		
		newb = new JButton("new");
		edit = new JButton("edit");
		viewDetails = new JButton("view details");
		
		// setting action listeners/commands
		newb.setActionCommand("new");
		edit.setActionCommand("edit");
		viewDetails.setActionCommand("view");
		fetch.setActionCommand("fetch");
		ma = new MainAction(exit, about, howTo, current, record, user, data);
		exit.addActionListener(ma);
		about.addActionListener(ma);
		howTo.addActionListener(ma);
		newb.addActionListener(ma);
		edit.addActionListener(ma);
		viewDetails.addActionListener(ma);
		fetch.addActionListener(ma);
		// getting the users array
		// Users usersList = new Users();
// 		usersList.update();
// 		ArrayList<User> userArray = usersList.getRecords();
// 		
		// combobox and other user specific stuff User.FACULTY, User.ADMIN, User.CHAIR
		// Record combo box: add Teaching, Service and Scholarship for all users
		record.addItem(teaching);
		record.addItem(scholarship);
		record.addItem(service);
		
		if(current.getRole().equalsIgnoreCase(User.FACULTY)){
			// show/hide
			user.addItem(current);
			user.setVisible(false);
			newb.setVisible(false);
			viewDetails.setVisible(true);
			edit.setVisible(false);
			
		}
		if(current.getRole().equalsIgnoreCase(User.ADMIN) || current.getRole().equalsIgnoreCase(User.CHAIR)){
			// show/hide
			user.setVisible(true);
			newb.setVisible(true);
			viewDetails.setVisible(true);
			edit.setVisible(true);
			
			// getting the list of users
			Users usersList = new Users();
			usersList.update(current);
			ArrayList<Record> userArray = usersList.getRecords();
			for(int i=0; i< userArray.size(); i++){
 				user.addItem(userArray.get(i));
 			}
			user.setVisible(true);
		}
		if(current.getRole().equalsIgnoreCase(User.CHAIR)){
			record.addItem(kudos);
			record.addItem(users);
			
			//user.addItem(current);
			//user.setVisible(true);
		}
		
		// adding things to north
		north.add(logo, BorderLayout.NORTH);
		north.add(record, BorderLayout.WEST);
		north.add(user, BorderLayout.CENTER);
		north.add(fetch, BorderLayout.EAST);
		
		// adding things to center
		center.add(scroll);
		
		// adding things to south
		south.add(newb);
		south.add(edit);
		south.add(viewDetails);
		
		// adding the panels
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		// finishing up
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
