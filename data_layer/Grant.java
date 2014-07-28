package data_layer;
import data_layer.Record;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author Kim Desorcie
 * @version 8
 * Data layer Grants record
*/
public class Grant extends Record {
	
	protected static String TABLE_NAME = "grants";
	protected static String[] attrNames = {"UserID", "GrantID", "Year", "Citation", "Tease", "Amount", "Status"};
	
	/**
	 * Default constructor
	*/
	protected Grant() {
		super();
	}
	
	/**
	 * Constructor with userID only
	 * Primary key constructor for User recores
	 * @param user String the record's UserId (primary key for User, foreign key and part of primary key for all others)
	*/
	protected Grant(String user) {
		super(user);
	}
	
	/**
	 * Primary key constructor
	 * @param user String the record's UserId
	 * @param grant String the record's GrantId
	*/
	protected Grant(String user, String grant) {
		super(user, grant);
	}
	
	/**
	 * ArrayList constructor
	 * @param values ArrayList list of the values of the attributes, in order (doesn't need to incldue all attributes)
	*/
	protected Grant(ArrayList<String> values) {
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