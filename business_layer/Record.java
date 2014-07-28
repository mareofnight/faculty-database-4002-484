package business_layer;
import java.util.ArrayList;

public interface Record {
	
	
	//****************************************************
	//*************   ATTRIBUTE ACCESSORS   **************
	//****************************************************
	
	/**
	 * Accessor for all attributes
	 * @param int index of attribute (starts at 0)
	 * @return String value of the attribute
	*/
	public String getAttr(int attr);
	
	/**
	 * Accessor for all attributes
	 * @param String attribute name
	 * @return String value of the attribute
	*/
	public String getAttr(String attr);
	
	/**
	 * Returns all attribute values as an ArrayList
	 * @return ArrayList<String> all values, in order
	*/
	public ArrayList<String> getValues();
	
	/**
	 * Accessor for attribute names (used by superclass to construct query strings)
	 * @return String[] array of names of all attributes
	*/
	public String[] getAttrNames();
	
	/**
	 * Accessor for table name
	 * @return String name of the table for this record type
	*/
	public String getTableName();
	
	
	//****************************************************
	//**************   ATTRIBUTE MUTATORS   **************
	//****************************************************
	
	/**
	 * Accessor for all attributes
	 * @param attr int index of attribute (starts at 0)
	 * @param value String new value for the attribute
	*/
	public void setAttr(int attr, String value);
	
	/**
	 * Accessor for all attributes
	 * @param attr String attribute name
	 * @param value String new value for the attribute
	*/
	public void setAttr(String attr, String value);
	
	
	//****************************************************
	//**************     DATABASE ACCESS     *************
	//****************************************************
	
	///////////////////////////// EDIT /////////////////////////////
	
	/**
	 * Edit a record (save)
	 * @param user User accessing the record (for authentication)
	*/
	public void edit(User user);
	
	/**
	 * Edit a record (save)
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void edit(String[] ticket);
	
	///////////////////////////// INSERT /////////////////////////////
	
	/**
	 * Insert a new record (save as)
	 * @param user User accessing the record (for authentication)
	*/
	public void insert(User user);
	
	/**
	 * Insert a new record (save as)
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void insert(String[] ticket);
	
	///////////////////////////// UPDATE /////////////////////////////
	
	/**
	 * Update to match the database (refresh)
	 * @param user User accessing the record (for authentication)
	*/
	public void update(User user);
	
	/**
	 * Update to match the database (refresh)
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void update(String[] ticket);
	
}