package data_layer;
import data_layer.Record;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author Kim Desorcie
 * @version 8
 * Data layer Pubs record
*/
public class Pub extends Record {
	
	protected static String TABLE_NAME = "pubs";
	protected static String[] attrNames = {"UserId", "PubID", "Year", "Citation", "Tease", "Status"};
	
	/**
	 * Default constructor
	*/
	protected Pub() {
		super();
	}
	
	/**
	 * Constructor with userID only
	 * Primary key constructor for User recores
	 * @param user String the record's UserId (primary key for User, foreign key and part of primary key for all others)
	*/
	protected Pub(String user) {
		super(user);
	}
	
	/**
	 * Primary key constructor
	 * @param user String the record's UserId
	 * @param pub String the record's PubId
	*/
	protected Pub(String user, String pub) {
		super(user, pub);
	}
	
	/**
	 * ArrayList constructor
	 * @param values ArrayList list of the values of the attributes, in order (doesn't need to incldue all attributes)
	*/
	protected Pub(ArrayList<String> values) {
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
	
}