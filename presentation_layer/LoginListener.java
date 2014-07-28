package presentation_layer;
import business_layer.User;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
/*
	@author Kim Desorcie
	@version 8
	Loggin window action listener - logs in the user and starts the main GUI
*/
public class LoginListener implements ActionListener {
	
	// components
	private JFrame loginWindow;
	private JTextField idField;
	private JPasswordField passField;
	private JLabel errorLabel = new JLabel("");
		
	/*
		constructor
		@param JFrame the login window
		@param JTextField the user ID field
		@param JTextField the password field
	*/
	public LoginListener (JFrame window, JTextField iField, JPasswordField pField) {
		loginWindow = window;
		idField = iField;
		passField = pField;
		loginWindow.add(errorLabel);
	}
	
	/*
		action handler
		Call a method to handle the action, depending on the class of the source component
		@param ActionEvent the triggering event
	*/
	public void actionPerformed(ActionEvent e) {
		
		User user = new User(idField.getText());
		boolean login = false;
		try {
			String[] tempTicket = { idField.getText(), "0" };
			user.update(tempTicket);
			login = user.login(passField.getText());
		}
		catch (Exception x) {
			errorLabel.setText("Incorrect username or password. Try again.");
			loginWindow.repaint();
			login = false;
		}
		
		// open the main GUI and close the login window
		if (login) {
			new MainGUI(user);
			loginWindow.dispose();
		}
		else
		{
			errorLabel.setText("Incorrect username or password. Try again.");
			loginWindow.repaint();
		}
		
	}
	
}