package data_layer;
import data_layer.Database;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.*;

/**
 * @author Kim
 * @version 10
 * Superclass for data layer record objects
*/
public abstract class Record {
	
	// variables - table values and metadata
	protected ArrayList<String> attrValues;
	protected String[] attrNames;
	protected String TABLE_NAME;
	
	// variables - query strings
	protected String fetchQuery;
	protected String putQuery;
	protected String postQuery;
	protected String deleteQuery;
	protected String nextPKQuery;
	
	// methods to be implemented by each subclass
	protected abstract String[] getAttrNames();
	protected abstract void constructorSetup();
	
	
	
	//***************************************************
	//****************   CONSTRUCTORS   *****************
	//***************************************************
	
	/**
	 * Default constructor
	*/
	protected Record() {
		super();
		
		// setup attributes
		attrValues = new ArrayList<String>();
		//attrNames = getAttrNames();
		
		constructorSetup();// specified in subclass, sets tablename and query strings (fetchQuery, putQuery, etc.)
	}
	
	/**
	 * Constructor with userID only
	 * Primary key constructor for User recores
	 * @param userID String the record's UserId (primary key for User, foreign key and part of primary key for all others)
	*/
	protected Record(String userID) {
		super();
		
		// setup attributes
		attrValues = new ArrayList<String>();
		attrValues.add(userID);
		//attrNames = getAttrNames();
		
		constructorSetup();// specified in subclass, sets tablename and query strings (fetchQuery, putQuery, etc.)
	}
	
	/**
	 * Primary key String constructor
	 * (should not be used for User records)
	 * @param userID String the record's UserId (primary key for User, foreign key and part of primary key for all others)
	 * @param recordID String the other part of the record's primary key for all except user
	*/
	protected Record(String userID, String recordID) {
		super();
		
		// setup attributes
		attrValues = new ArrayList<String>();
		attrValues.add(userID);
		attrValues.add(recordID);
		//attrNames = getAttrNames();
		
		constructorSetup();// specified in subclass, sets tablename and query strings (fetchQuery, putQuery, etc.)
	}
	
	/**
	 * ArrayList constructor
	 * @param values ArrayList list of the values of the attributes, in order (doesn't need to incldue all attributes)
	*/
	protected Record(ArrayList<String> values) {
		super();
		
		attrValues = values;
		
		constructorSetup();// specified in subclass, sets tablename and query strings (fetchQuery, putQuery, etc.)
	}
	
	
	
	
	
	//****************************************************
	//*************   ATTRIBUTE ACCESSORS   **************
	//****************************************************
	
	/**
	 * Accessor for all attributes
	 * @param int index of attribute (starts at 0)
	 * @return attr String value of the attribute
	*/
	public String getAttr(int attr) {
		try {
			return attrValues.get(attr);
		}
		catch (IndexOutOfBoundsException x) {
			return null;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * Accessor for all attributes
	 * @param String attribute name
	 * @return attr String value of the attribute
	*/
	public String getAttr(String attr) {
		for (int i=0; i<attrNames.length; i++) {// find the attribute index
			if ( attrNames[i].toLowerCase().equals(attr.toLowerCase()) ) {
				return getAttr(i);// call the int version using the attribute index
			}
		}
		return null;
	}
	
	/**
	 * Returns the primary key attribute values as an arraylist
	 * @return ArrayList<String> values of the primary key attributes, with UserId first
	*/
	public ArrayList<String> getPrimaryKey() {
		ArrayList<String> primKey = new ArrayList<String>();
		primKey.add(attrValues.get(0));
		if (!TABLE_NAME.equals(User.TABLE_NAME)) {
			primKey.add(attrValues.get(1));
		}
		return primKey;
	}
	
	/**
	 * Returns all attribute values as an ArrayList
	 * @return ArrayList<String> all values, in order
	*/
	public ArrayList<String> getValues() {
		return attrValues;
	}
	
	/**
	 * Returns table name
	 * @return String name of the table for this record type
	*/
	public String getTableName() {
		return TABLE_NAME;
	}
	
	
	//****************************************************
	//**************   ATTRIBUTE MUTATORS   **************
	//****************************************************
	
	/**
	 * Accessor for all attributes
	 * @param attr int index of attribute (starts at 0)
	 * @param value String new value for the attribute
	*/
	protected void setAttr(int attr, String value) {
		try {
			attrValues.set(attr, value);
		}
		catch (IndexOutOfBoundsException x) {
			for (int i=0; i<(attr - attrValues.size() + 1); i++) {
				attrValues.add("");
			}
			setAttr(attr, value);
		}
	}
	
	/**
	 * Accessor for all attributes
	 * @param attr String attribute name
	 * @param value String new value for the attribute
	*/
	protected void setAttr(String attr, String value) {
		for (int i=0; i<attrNames.length; i++) {// find the attribute index
			if ( attrNames[i].toLowerCase().equals(attr.toLowerCase()) ) {
				setAttr(i, value);// call the int version using the attribute index
				break;// stop searching for the attribute index
			}
		}
	}
	
	
	
	
	//****************************************************
	//**************     DATABASE ACCESS     *************
	//**************  and query string setup *************
	//****************************************************
	
	/**
	 * Get the query string for finding the integer one higher than the greatest primary key of the record type (exculding foreign keys)
	*/
	protected String getNextPKQuery() {
		
		// full query should be SELECT MAX(< primary key attribute >)+1 FROM <table>;
		
		String query = "SELECT MAX(";
		
		if (TABLE_NAME.equals(User.TABLE_NAME)) {
			query += attrNames[0];
		}
		else {
			query += attrNames[1];
		}
		
		query+= ")+1 FROM " + TABLE_NAME + ";";
		
		return query;
	}
	
	/**
	 * Get the integer one higher than the highest ID (output as a string), to be used as the ID for a new record of the type
	 * @return String an ID that can be used for a new record - the next ID after the highest ID for this record type
	*/
	protected String nextPK() {
		try {
			ArrayList<ArrayList<String>> resultList = getData(nextPKQuery, new ArrayList<String>());
			String result = resultList.get(0).get(0);
			return result;
		}
		catch (Exception x) {
			return null;
			
			
		}
	}
	
	/**
	 * Get the query string for SELECT, based on the table and attribute names specific to this record type
	*/
	protected String getFetch() {
		
		String query = "SELECT ";
		
		// list attributes in order
		for (int i=0; i<(attrNames.length - 1); i++) {
			query += attrNames[i] + ", ";
		}
		query += attrNames[(attrNames.length - 1)];
		
		// add WHERE
		query += " FROM " + TABLE_NAME + " WHERE UserID = ? ";
		if (!TABLE_NAME.equals(User.TABLE_NAME)) {
			query+= "AND " + attrNames[1] + " = ? ";
		}
		
		query += ";";
		return query;
	}
	
	/**
	 * Update this object to match its corresponding database record
	*/
	protected void fetch() {
		try {
			ArrayList<ArrayList<String>> resultList = getData(fetchQuery, getPrimaryKey());
			attrValues = resultList.get(0);
		}
		
		catch (Exception x) {
		}
	}
	
	/**
	 * Get the query string for INSERT, based on the table and attribute names specific to this record type
	*/
	protected String getPut() {
		
		String query = "INSERT INTO " + TABLE_NAME + " (";
		String valueBlanks = "";
		
		// list attributes in order
		for (int i=0; i<(attrNames.length - 1); i++) {
			query += attrNames[i] + ", ";
			valueBlanks += "?, ";
		}
		query += attrNames[(attrNames.length - 1)];
		valueBlanks += "?";
		
		query += ") VALUES ( " + valueBlanks + " );";
		return query;
		
	}
	
	/**
	 * Save this to the database as a new record
	 * If non-foreign-key primary keys (courseID, grantID, etc.) are invalid, they will automatically be replaced with a valid one
	*/
	protected void put() {
		try {
			
			// if a User's UserID is not an integer, make an ID for it
			if (TABLE_NAME.equals(User.TABLE_NAME)) {
				try {		Integer.parseInt(attrValues.get(0));	}
				catch (NumberFormatException e) {
					attrValues.set(0, nextPK());
				}
			}
			else {
				// If a non-user record's ID (courseID of courses, kudoID of kudos, etc.) is not an integer, make an ID for it
				try {		Integer.parseInt(attrValues.get(1));	}
				catch (NumberFormatException e) {
					attrValues.set(1, nextPK());
				}
			}
			
			setData(putQuery, attrValues);
		}
		catch (Exception x) {
		}
	}
	
	/**
	 * Get the query string for UPDATE, based on the table and attribute names specific to this record type
	*/
	protected String getPost() {
		
		String query = "UPDATE " + TABLE_NAME + " SET ";
		
		// list attributes in order
		for (int i=0; i<(attrNames.length - 1); i++) {
			query += attrNames[i] + " = ? , ";
		}
		query += attrNames[(attrNames.length - 1)] + " = ?";
		
		// add WHERE
		query += " WHERE " + attrNames[0] + " = ? ";
		if (!TABLE_NAME.equals(User.TABLE_NAME)) {
			query+= "AND " + attrNames[1] + " = ? ";
		}
		
		query += ";";
		return query;
	}
	
	/**
	 * Save this to the database by saving over the record with a corresponding primary key
	*/
	protected void post() {
		ArrayList<String> vars = attrValues;
		ArrayList<String> primKey = getPrimaryKey();
		for (int i=0; i<primKey.size(); i++) {
			vars.add(primKey.get(i));
		}
		try {
			setData(postQuery, vars);
		}
		catch (Exception x) {
			
		}
	}
	
	/**
	 * Get the query string for DELETE, based on the table and attribute names specific to this record type
	*/
	protected String getDelete() {
		String query = "DELETE FROM " + TABLE_NAME + " WHERE " + attrNames[0] + " = ? ";
		if (!TABLE_NAME.equals(User.TABLE_NAME)) {
			query+= "AND " + attrNames[1] + " = ? ";
		}
		query += ";";
		return query;
	}
	
	/**
	 * Delete this record from the database
	*/
	protected void delete() {
		try {
			setData(deleteQuery, getPrimaryKey());
		}
		catch (Exception x) {
		}
	}
	
	
	
	//*****************************************************
	//*************   BASIC DATABASE ACCESS   *************
	//*****************************************************
	
	/**
	 * Connect to the database and execute a prepared SELECT statement
	 * @param query String prepared statement
	 * @param values ArrayList<String> parameters for prepared statement
	 * @return ArrayList<ArrayList<String>> result of the query
	*/
	protected static ArrayList<ArrayList<String>> getData(String query, ArrayList<String> values) {
		
		Database db = new Database();
		ArrayList<ArrayList<String>> resultList = null;// return null if query fails
		
		try {
			db.connect();
			resultList = db.getData(query, values);
		}
		
		catch (Exception x) {
			
		}
		
		finally {
			try {
				db.close();
			}
			catch (Exception x) {
				
			}
			return resultList;
		}
		
	}
	
	/**
	 * Connect to the database and execute a prepared INSERT, UPDATE or DELETE staement
	 * @param query String prepared statement
	 * @param values ArrayList<String> parameters for prepared statement
	 * @return int number of records changed, or -1 if update failed
	*/
	protected static int setData(String query, ArrayList<String> values) {
		
		Database db = new Database();
		int numChanged = -1; // return -1 if the query fails
		
		try {
			db.connect();
			numChanged = db.setData(query, values);
		}
		
		catch (Exception x) {
			
		}
		
		finally {
			try {
				db.close();
			}
			catch (Exception x) {
				
			}
			return numChanged;
		}
		
	}
	
	
	
	//***************************************************
	//**************   TESTING UTILITIES   **************
	//***************************************************
	
	/**
	 * Outputs the contents of all attributes as a string. To be used for testing.
	 * @return String contents of all attributes as a string
	*/
	public String toString() {
		String output = "";
		try {
		for (int i=0; i<attrNames.length; i++) {
			output += attrNames[i] + ": " + attrValues.get(i) + "\n";
		}
		}
		catch (NullPointerException e) {
			
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		return output;
	}
	
	
}