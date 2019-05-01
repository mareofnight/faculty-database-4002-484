package data_layer;
import data_layer.Record;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author David Desorcie
 * @version 10
 * Data layer Users record
*/
public class User extends Record {
	
	// static roles
	public static String FACULTY = "3";
	public static String ADMIN = "2";
	public static String CHAIR = "1";
	
	protected static String TABLE_NAME = "users";
	protected static String[] attrNames = {"UserId", "FName", "LName", "Email", "Pswd", "Role"};
	
	/**
	 * Default constructor
	*/
	protected User() {
		super();
	}
	
	/**
	 * Constructor with userID only
	 * Primary key constructor for User recores
	 * @param user String the record's UserId (primary key for User, foreign key and part of primary key for all others)
	*/
	protected User(String user) {
		super(user);
	}
	
	/**
	 * ArrayList constructor
	 * @param values ArrayList list of the values of the attributes, in order (doesn't need to incldue all attributes)
	*/
	protected User(ArrayList<String> values) {
		super(values);
	}
	
	
	
	/**
	 * Set superclass's variables for name of the table and query strings
	*/
	protected void constructorSetup () {
		super.TABLE_NAME = TABLE_NAME;
		super.attrNames = attrNames;
		super.fetchQuery = getFetch();
		super.putQuery = getPut();
		super.postQuery = getPost();
		super.deleteQuery = getDelete();
		super.nextPKQuery = getNextPKQuery();
	}
	
	/**
	 * Accessor for attribute names (used by superclass to construct query strings)
	 * @return String[] array of names of all attributes
	*/
	protected String[] getAttrNames() {
		return attrNames;
	}
	
	
	
	
	//**************************************************
	//************   USER-ONLY UTILITIES   *************
	//**************************************************
	
	/**
	 * check password against input
	 * @param testPass String password to check
	*/
	protected boolean checkPass (String testPass) {
	
		if (testPass.equals(super.getAttr("pswd"))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * get the user's role
	 * @return String user's role
	*/
	public String getRole() {
		return super.getAttr(5);
	}
	
	/**
	 * get the user's ID
	 * @return String user's role
	*/
	public String getID() {
		return super.getAttr(0);
	}
	
}