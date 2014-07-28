package data_layer;
import data_layer.*;
import java.util.ArrayList;

/**
 * @author Kim
 * @version 8
 * Manages a list of business layer Courses records
*/
public class Courses extends Records {
	
	protected static String TABLE_NAME = Course.TABLE_NAME;
	protected static String[] attrNames = Course.attrNames;
	
	/**
	 * Constructor for listing all records of type
	*/
	public Courses() {
		super();
	}
	
	/**
	 * Constructor for listing all of a user's records of type
	 * @param id String the ID of the user for which the courses are listed
	*/
	public Courses(String id) {
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
				newList.add(new Course(resultList.get(i)));
			}
			
			// replace old array list with new array list
			setList(newList);
		}
		
		catch (Exception x) {
		}
	}
	
}

/** test method
	public static void main ( String[] args ) {
	 * 
	 * Services test = new Services("1");
	 * test.fetch();
	 * ArrayList<Record> testList = test.getList();
	 * for (int i=0; i<testList.size(); i++) {
	 * 	System.out.println(testList.get(i).toString());
	 * }
	 * 
	}
*/