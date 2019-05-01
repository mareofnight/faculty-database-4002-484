package business_layer;
import java.util.ArrayList;

/**
 * @author David Desorcie
 * @version .04
 * Business layer Users recor
 * Handles viewing, editing and inserting records, and enforces user permissions.
*/

public class User extends data_layer.User implements Record {
	
	// static roles
	public static String FACULTY = "3";
	public static String ADMIN = "2";
	public static String CHAIR = "1";
	
	/**
	 * Default constructor
	*/
	public User() {
		super();
	}
	
	/**
	 * Constructor
	 * @param baseRecord data_layer.User data layer object to base this object on
	*/
	public User(data_layer.User baseRecord) {
		super(baseRecord.getValues());
	}
	
	/**
	 * Primary key String constructor
	 * (should not be used for User records)
	 * @param userID String the record's UserId (primary key for User)
	*/
	public User(String userID) {
		super(userID);
	}
	
	/**
	 * Constructor
	 * @param values ArrayList list of the values of the attributes, in order (doesn't need to incldue all attributes)
	*/
	public User(ArrayList<String> values) {
		super(values);
	} 
	
	/**
	 * check password against input
	 * @param String password to check
	*/
	public boolean login(String testPass) {
		return checkPass(testPass);
	}
	
	
	/**
	 * For use in GUIs - returns the user's full name as a string
	 * @return String user's first name and last name, separated by a space
	*/
	public String toString() {
		return getAttr(1) + " " + getAttr(2);
	}
	
	
	
	//****************************************************
	//*************   ATTRIBUTE ACCESSORS   **************
	//****************************************************
	
	/**
	 * Accessor for table name
	 * @return String name of the table for this record type
	*/
	public String getTableName() {
		return super.getTableName();
	}
	
	/**
	 * Accessor for attribute names (used by superclass to construct query strings)
	 * @return String[] array of names of all attributes
	*/
	public String[] getAttrNames() {
		return super.getAttrNames();
	}
	
	/**
	 * Accessor for all attributes
	 * @param int index of attribute (starts at 0)
	 * @return String value of the attribute
	*/
	public String getAttr(int attr) {
		return super.getAttr(attr);
	}
	
	/**
	 * Accessor for all attributes
	 * @param String attribute name
	 * @return String value of the attribute
	*/
	public String getAttr(String attr) {
		return super.getAttr(attr);
	}
	
	/**
	 * Returns all attribute values as an ArrayList
	 * @return ArrayList<String> all values, in order
	*/
	public ArrayList<String> getValues() {
		return super.attrValues;
	}
	
	
	//****************************************************
	//**************   ATTRIBUTE MUTATORS   **************
	//****************************************************
	
	/**
	 * Accessor for all attributes
	 * @param attr int index of attribute (starts at 0)
	 * @param value String new value for the attribute
	*/
	public void setAttr(int attr, String value) {
		super.setAttr(attr, value);
	}
	
	/**
	 * Accessor for all attributes
	 * @param attr String attribute name
	 * @param value String new value for the attribute
	*/
	public void setAttr(String attr, String value) {
		super.setAttr(attr, value);
	}
	
	
	//****************************************************
	//**************     DATABASE ACCESS     *************
	//****************************************************
	
	///////////////////////////// EDIT /////////////////////////////
	
	/**
	 * Edit a record (save)
	 * @param user User accessing the record (for authentication)
	*/
	public void edit(User user) {
		if ( user.getRole().equals(User.ADMIN) || user.getRole().equals(User.CHAIR) ) {
			edit();
		}
	}
	
	/**
	 * Edit a record (save)
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void edit(String[] ticket) {
		if ( ticket[1].equals(User.ADMIN) || ticket[1].equals(User.CHAIR) ) {
			edit();
		}
	}
	
	/**
	 * Edit a record (save) - bypasses authentication, can only be called within this class
	*/
	private void edit() {
		try {			post();		}
			catch (Exception x) {
		
		}
	}
	
	///////////////////////////// INSERT /////////////////////////////
	
	/**
	 * Insert a new record (save as)
	 * @param user User accessing the record (for authentication)
	*/
	public void insert(User user) {
		if ( user.getRole().equals(User.ADMIN) || user.getRole().equals(User.CHAIR) ) {
			insert();
		}
	}
	
	/**
	 * Insert a new record (save as)
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void insert(String[] ticket) {
		if ( ticket[1].equals(User.ADMIN) || ticket[1].equals(User.CHAIR) ) {
			insert();
		}
	}
	
	/**
	 * Insert a new record (save as) - bypasses authentication, can only be called within this class
	*/
	private void insert() {
		try {			put();		}
		catch (Exception x) {
			
		}
	}
	
	///////////////////////////// UPDATE /////////////////////////////
	
	/**
	 * Update to match the database (refresh)
	 * @param user User accessing the record (for authentication)
	*/
	public void update(User user) {
		if ( user.getID().equals(this.getAttr(0)) || user.getRole().equals(User.ADMIN) || user.getRole().equals(User.CHAIR) ) {
			fetch();
		}
	}
	
	/**
	 * Update to match the database (refresh)
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void update(String[] ticket) {
		if ( ticket[0].equals(this.getAttr(0)) || ticket[1].equals(User.ADMIN) || ticket[1].equals(User.CHAIR) ) {
			fetch();
		}
	}
	
	/**
	 * Update to match the database (refresh)  - bypasses authentication, can only be called within this class
	*/
	private void update() {
		try {			fetch();		}
		catch (Exception x) {
			
		}
	}
	
}