package data_layer;
import data_layer.*;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author Kim Desorcie
 * @version 8
 * Superclass for record list classes
*/
public abstract class Records {
	
	// string variables
	protected String[] attrNames;
	protected String TABLE_NAME;
	protected String fetchQuery;
	protected String userID = "";
	
	// variable to store records
	private ArrayList<Record> recordList;
	
	// abstract methods
	protected abstract String[] getAttrNames();
	protected abstract String getTableName();
	protected abstract void fetch();
	
	/**
	 * Constructor for listing all records of type
	*/
	public Records() {
		super();
		recordList = new ArrayList<Record>();
		attrNames = getAttrNames();
		TABLE_NAME = getTableName();
		fetchQuery = getFetch();
	}
	
	/**
	 * Constructor for listing all of a user's records of type
	 * @param String the ID of the user for which the courses are listed
	*/
	public Records(String id) {
		super();
		userID = id;
		recordList = new ArrayList<Record>();
		attrNames = getAttrNames();
		TABLE_NAME = getTableName();
		fetchQuery = getFetch();
	}
	
	
	/////////////// ACCESSORS AND MUTATORS FOR USER ID ///////////////
	/**
	 * Accessor for User ID (the ID of the user whose records can be included in the list - will be blank ("") if listing records for all users
	 * @return String userID of the users whose records can be included in this list
	*/
	protected String getUserID() {
		return userID;
	}
	
	/**
	 * Change the user whose records will be listed
	 * @param String id of the user whose records will be listed
	*/
	protected void setUser(String id) {
		userID = id;
		fetchQuery = getFetch();
	}
	
	/**
	 * Change the user whose records will be listed
	 * @param User the user whose records will be listed
	*/
	protected void setUser(User user) {
		userID = user.getAttr("userID");
		fetchQuery = getFetch();
	}
	
	
	/////////////// ACCESSORS AND MUTATORS FOR RECORD LIST ///////////////
	
	/**
	 * accessor for the list of Records
	 * @return ArrayList<Record> list of Records records
	*/
	protected ArrayList<Record> getList() {
		return recordList;
	}
	
	/**
	 * accessor for the list of Records
	 * @return ArrayList<Record> new list of Records records
	*/
	protected void setList(ArrayList<Record> newList) {
		recordList = newList;
	}
	
	
	/////////////// REFRESH RECORD LIST ///////////////
	
	/**
	 * Get the query string to fetch all records (if userID attribte is null) or all records for a specific user
	*/
	protected String getFetch() {
		
		String query = "SELECT ";
		
		// list attributes in order
		for (int i=0; i<(attrNames.length - 1); i++) {
			query += attrNames[i] + ", ";
		}
		query += attrNames[(attrNames.length - 1)];
		
		query += " FROM " + TABLE_NAME + " ";
		
		if (!userID.equals("")) {
			query += "WHERE userID = " + userID + " ";
		}
		
		query += ";";
		
		return query;
		
	}
	
}