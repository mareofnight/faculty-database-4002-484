package business_layer;
import java.util.ArrayList;

/**
 * @author Kim Desorcie
 * @version 11
 * Manage a list of business layer Kudos records
*/
public class Kudos extends data_layer.Kudos implements Records {
	
	/**
	 * Default constructor
	*/
	public Kudos() {
		super();
	}
	
	/**
	 * Constructor for listing all of a user's records of type
	 * @param id String the ID of the user for which the records are listed
	*/
	public Kudos(String id) {
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
		if ( user.getRole().equals(User.CHAIR) ) {
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
		if ( ticket[1].equals(User.CHAIR) ) {
			try {
				fetch();
			}
			catch (Exception x) {
				
			}
		}
	}
	
	/**
	 * Accessor - returns an array list of business layer Kudos records
	 * @return ArrayList<business_layer.Kudo> list of Kudos records
	*/
	public ArrayList<business_layer.Record> getRecords() {
		
		ArrayList<business_layer.Record> businessList = new ArrayList<business_layer.Record>();
		ArrayList<data_layer.Record> dataList = getList();
		for (int i=0; i<dataList.size(); i++) {
			businessList.add(new Kudo((data_layer.Kudo)dataList.get(i)));
		}
		return businessList;
		
	}
	
	/**
	 * toString - returns the presentation layer name for the class
	 * @return String presentation layer name of this class
	*/
	public String toString() {
		return "Kudos";
	}
	
}