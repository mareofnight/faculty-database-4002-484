package business_layer;
import java.util.ArrayList;

/**
 * @author David Desorcie
 * @version 11
 * Interface for record listing classes
*/
public interface Records {
	
	/**
	 * Update the list of records to match the database
	 * @param user User user accessing the records (for authentication)
	*/
	public void update(User user);
	
	/**
	 * Update the list of records to match the database
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void update(String[] ticket);
	
	/**
	 * toString - returns the presentation layer name for the class
	 * @return String presentation layer name of this class
	*/
	public String toString();
	
	/**
	 * Change the user whose records will be listed
	 * @param id String id of the user whose records will be listed
	*/
	public void setUser(String id);
	
	/**
	 * Change the user whose records will be listed
	 * @param user User the user whose records will be listed
	*/
	public void setUser(User user);
	
	/**
	 * Accessor - returns an array list of business layer Courses records
	 * @return ArrayList<business_layer.Course> list of Courses records
	*/
	public ArrayList<business_layer.Record> getRecords();
	
}