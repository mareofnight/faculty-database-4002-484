package data_layer;
import data_layer.Record;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author David Desorcie
 * @version 8
 * Data layer Service record
*/
public class Service extends Record {
	
	//UserID, ServiceID, Year, Description, Role
	//services
	
	protected static String TABLE_NAME = "service";
	protected static String[] attrNames = {"UserId", "ServiceID", "Year", "Description", "Role"};
	
	/**
	 * Default constructor
	*/
	protected Service() {
		super();
	}
	
	/**
	 * Constructor with userID only
	 * Primary key constructor for User recores
	 * @param user String the record's UserId (primary key for User, foreign key and part of primary key for all others)
	*/
	protected Service(String user) {
		super(user);
	}
	
	/**
	 * Primary key constructor
	 * @param user String the record's UserId
	 * @param service String the record's ServiceId
	*/
	protected Service(String user, String service) {
		super(user, service);
	}
	
	/**
	 * ArrayList constructor
	 * @param values ArrayList list of the values of the attributes, in order (doesn't need to incldue all attributes)
	*/
	protected Service(ArrayList<String> values) {
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

/**
	public static void main (String[] args) {
	 * 
	 * Service test = new Service("1", "2");
	 * System.out.println("Before fetch: " + "\n" + test.toString());
	 * test.fetch();
	 * System.out.println("After fetch: " + "\n" + test.toString());
	 * test.attrValues.set(3, (test.attrValues.get(3) + "+"));
	 * test.post();
	 * test.fetch();
	 * System.out.println("After post: " + "\n" + test.toString());
	 * test.attrValues.set(1, "21");
	 * test.put();
	 * //System.out.println(test.getPrimaryKey().toString());
	 * test.fetch();
	 * System.out.println("After put: " + "\n" + test.toString());
	 * test.delete();
	 * System.out.println("Deleted");
	 * test.fetch();
	}
*/