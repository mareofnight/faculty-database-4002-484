package business_layer;
import java.util.ArrayList;

/**
 * @author David Desorcie
 * @version 11
 * Manage a list of business layer Courses records
*/
public class Courses extends data_layer.Courses implements Records {
	
	/**
	 * Default constructor
	*/
	public Courses() {
		super();
	}
	
	/**
	 * Constructor for listing all of a user's records of type
	 * @param id String the ID of the user for which the records are listed
	*/
	public Courses(String id) {
		super(id);
	}
	
	/**
	 * Change the user whose records will be listed
	 * @param id String id of the user whose records will be listed
	*/
	public void setUser(String id) {
		super.setUser(id);
	}
	
	/**
	 * Change the user whose records will be listed
	 * @param user User the user whose records will be listed
	*/
	public void setUser(User user) {
		super.setUser(user.getID());
	}
	
	/**
	 * Update the list of records to match the database
	 * @param user User user accessing the records (for authentication)
	*/
	public void update(User user) {
		if ( user.getID().equals(this.getUserID()) || user.getRole().equals(User.ADMIN) || user.getRole().equals(User.CHAIR) ) {
			try {
				fetch();
			}
			catch (Exception x) {
				
			}
		}
	}
	
	/**
	 * Update the list of records to match the database
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void update(String[] ticket) {
		if ( ticket[0].equals(this.getUserID()) || ticket[1].equals(User.ADMIN) || ticket[1].equals(User.CHAIR) ) {
			try {
				fetch();
			}
			catch (Exception x) {
				
			}
		}
	}
	
	/**
	 * Accessor - returns an array list of business layer Courses records
	 * @return ArrayList<business_layer.Course> list of Courses records
	*/
	public ArrayList<business_layer.Record> getRecords() {
		
		ArrayList<business_layer.Record> businessList = new ArrayList<business_layer.Record>();
		ArrayList<data_layer.Record> dataList = getList();
		for (int i=0; i<dataList.size(); i++) {
			businessList.add(new Course((data_layer.Course)dataList.get(i)));
		}
		return businessList;
		
	}
	
	/**
	 * toString - returns the presentation layer name for the class
	 * @return String presentation layer name of this class
	*/
	public String toString() {
		return "Teaching";
	}
	
}

/** TEST METHOD

	public static void main ( String[] args ) {
	 * 
	 * Users test = new Users("1");
	 * test.update();
	 * ArrayList<User> testList = test.getRecords();
	 * for (int i=0; i<testList.size(); i++) {
	 * 	System.out.println(testList.get(i).toString());
	 * }
	 * 
	}

*/