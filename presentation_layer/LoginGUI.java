package presentation_layer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
	@author David Desorcie
	@verion 8
	GUI for logging in and starting the program
*/
public class LoginGUI extends JFrame {
	
	/*
		Constructor - make the GUI
	*/
	public LoginGUI () {
		
		// components
		JLabel userLabel = new JLabel("User ID");
		JLabel passLabel = new JLabel("Password");
		JTextField userField = new JTextField(25);
		JPasswordField passField = new JPasswordField(25);
		JButton button = new JButton("Login");
		button.addActionListener(new LoginListener(this, userField, passField));
		
		// setup layout
		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JPanel userPanel = new JPanel();
			userPanel.add(userLabel);
			userPanel.add(userField);
		JPanel passPanel = new JPanel();
			passPanel.add(passLabel);
			passPanel.add(passField);
		this.add(userPanel);
		this.add(passPanel);
		this.add(button);
		
		// setup window
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(380, 175);
		this.setLocation(200, 75);
		
	}
	
	public static void main(String [] args){
		new LoginGUI();
	}
	
}