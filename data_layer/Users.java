package data_layer;
import data_layer.*;
import java.util.ArrayList;

/**
 * @author David Desorcie
 * @version 8
 * Manages a list of business layer Users records
*/
public class Users extends Records {
	
	protected static String TABLE_NAME = User.TABLE_NAME;
	protected static String[] attrNames = User.attrNames;
	
	/**
	 * Basic constructor
	*/
	public Users() {
		super();
	}
	
	/**
	 * Constructor for listing all of a user's records of type
	 * @param id String the ID of the user for which the courses are listed
	*/
	public Users(String id) {
		super(id);
	}
	
	/**
	 * returns the names of the table's attributes
	 * @return String[] the names of the table's attributes
	*/
	protected String[] getAttrNames() {
		return attrNames;
	}
	
	/**
	 * returns the name of the table
	 * @return String name of the table
	*/
	protected String getTableName() {
		return TABLE_NAME;
	}
	
	/**
	 * Update this object to match its corresponding database table
	*/
	protected void fetch() {
		
		ArrayList<Record> newList = new ArrayList<Record>();
		
		try {
			// fetch data from database
			ArrayList<ArrayList<String>> resultList = data_layer.Record.getData(fetchQuery, new ArrayList<String>());
			
			// populate new array list
			for (int i=0; i<resultList.size(); i++) {
				newList.add(new User(resultList.get(i)));
			}
			
			// replace old array list with new array list
			setList(newList);
		}
		
		catch (Exception x) {
		}
	}
	
	/**
	 * Update this object to match its corresponding database table, but retrieve only userIDs and names
	*/
	protected void fetchNames() {
		
		ArrayList<Record> newList = new ArrayList<Record>();
		
		try {
			// fetch data from database
			ArrayList<ArrayList<String>> resultList = data_layer.Record.getData(
						"SELECT UserId, FName, LName FROM users ;", new ArrayList<String>());
			
			// populate new array list
			for (int i=0; i<resultList.size(); i++) {
				newList.add(new User(resultList.get(i)));
			}
			
			// replace old array list with new array list
			setList(newList);
		}
		
		catch (Exception x) {
		}
	}
	
	/**
	 * Get a list containing only the user with a given UserID
	 * @param id String the userID of the user that will
	*/
	protected void fetchNames(String id) {
		
		ArrayList<Record> newList = new ArrayList<Record>();
		
		try {
			// fetch data from database
			ArrayList<String> values = new ArrayList<String>();
				values.add(id);
			ArrayList<ArrayList<String>> resultList = data_layer.Record.getData(
						"SELECT UserId, FName, LName FROM users WHERE userID = ? ;", values);
			
			// populate new array list
			for (int i=0; i<resultList.size(); i++) {
				newList.add(new User(resultList.get(i)));
			}
			
			// replace old array list with new array list
			setList(newList);
		}
		
		catch (Exception x) {
		}
	}
	
}