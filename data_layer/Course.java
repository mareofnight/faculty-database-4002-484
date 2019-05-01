package data_layer;
import data_layer.Record;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author David Desorcie
 * @version 8
 * Data layer Courses record
*/
public class Course extends Record {
	
	protected static String TABLE_NAME = "courses";
	protected static String[] attrNames = {"UserId", "CourseID", "Year", "CourseNumber", "CourseName"};
	
	/**
	 * Default constructor
	*/
	protected Course() {
		super();
	}
	
	/**
	 * Constructor with userID only
	 * Primary key constructor for User recores
	 * @param user String the record's UserId (primary key for User, foreign key and part of primary key for all others)
	*/
	protected Course(String user) {
		super(user);
	}
	
	/**
	 * Primary key constructor
	 * @param user String the record's UserId
	 * @param course String the record's CourseId
	*/
	protected Course(String user, String course) {
		super(user, course);
	}
	
	/**
	 * ArrayList constructor
	 * @param values ArrayList list of the values of the attributes, in order (doesn't need to incldue all attributes)
	*/
	protected Course(ArrayList<String> values) {
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
	 * Test method
	*/
	/**public static void main (String[] args) {
	 * Course test = new Course("2", "23");
	 * test.setAttr("coursename", "Name of Course");
	 * System.out.println(test.getAttr("coursename"));
	 * System.out.println(test.getAttr(4));
	 * test.setAttr(3, "Number of Course");
	 * System.out.println(test.getAttr("coursenumber"));
	 * System.out.println(test.getAttr(3));
	}*/
	
	/**
	 * Test method
	*/
	/**public static void main (String[] args) {
	 * 
	 * Course test = new Course("2", "20");
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
	 * test.fetch();// this should throw an exception because the record no longer exists in the database.
	}*/