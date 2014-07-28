package data_layer;
import data_layer.*;
import java.util.ArrayList;

/**
 * @author Kim Deorcie
 * @version 8
 * Manages a list of business layer Pubs records
*/
public class Pubs extends Records {
	
	protected static String TABLE_NAME = Pub.TABLE_NAME;
	protected static String[] attrNames = Pub.attrNames;
	
	/**
	 * Basic constructor
	*/
	public Pubs() {
		super();
	}
	
	/**
	 * Constructor for listing all of a user's records of type
	 * @param id String the ID of the user for which the courses are listed
	*/
	public Pubs(String id) {
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
	 * Update this object to match its corresponding database record
	*/
	protected void fetch() {
		
		ArrayList<Record> newList = new ArrayList<Record>();
		
		try {
			// fetch data from database
			ArrayList<ArrayList<String>> resultList = data_layer.Record.getData(fetchQuery, new ArrayList<String>());
			
			// populate new array list
			for (int i=0; i<resultList.size(); i++) {
				newList.add(new Pub(resultList.get(i)));
			}
			
			// replace old array list with new array list
			setList(newList);
		}
		
		catch (Exception x) {
		}
	}
	
}