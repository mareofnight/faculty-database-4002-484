package business_layer;
import java.util.ArrayList;

/**
 * @author Kim Desorcie
 * @version 11
 * Manage a list of business layer Grants records
*/
public class Scholarships implements Records {
	
	Grants grants;
	Pubs pubs;
	
	/**
	 * Default constructor
	*/
	public Scholarships() {
		super();
		grants = new Grants();
		pubs = new Pubs();
	}
	
	/**
	 * Constructor for listing all of a user's records of type
	 * @param id String the ID of the user for which the records are listed
	*/
	public Scholarships(String id) {
		super();
		grants = new Grants(id);
		pubs = new Pubs(id);
	}
	
	/**
	 * Change the user whose records will be listed
	 * @param id String id of the user whose records will be listed
	*/
	public void setUser(String id) {
		grants.setUser(id);
		pubs.setUser(id);
	}
	
	/**
	 * Change the user whose records will be listed
	 * @param user User the user whose records will be listed
	*/
	public void setUser(User user) {
		setUser(user.getID());
	}
	
	/**
	 * Update the list of records to match the database
	 * @param user User user accessing the records (for authentication)
	*/
	public void update(User user) {
		pubs.update(user);
		grants.update(user);
	}
	
	/**
	 * Update the list of records to match the database
	 * @param ticket String[] authentication ticket - userID at index 0, role (integer) at index 1
	*/
	public void update(String[] ticket) {
		pubs.update(ticket);
		grants.update(ticket);
	}
	
	/**
	 * Accessor - returns an array list of business layer Grants records
	 * @return ArrayList<business_layer.Grant> list of Grants records
	*/
	public ArrayList<business_layer.Record> getRecords() {
		
		// setup arraylists
		ArrayList<business_layer.Record> businessList = new ArrayList<business_layer.Record>();
		ArrayList<business_layer.Record> pubList = pubs.getRecords();
		ArrayList<business_layer.Record> grantList = grants.getRecords();
		
		// add pubs and grants to a single arraylist
		for (int i=0; i<pubList.size(); i++) {
			businessList.add(pubList.get(i));
		}
		for (int i=0; i<grantList.size(); i++) {
			businessList.add(grantList.get(i));
		}
		
		return businessList;
		
	}
	
	/**
	 * toString - returns the presentation layer name for the class
	 * @return String presentation layer name of this class
	*/
	public String toString() {
		return "Scholarship";
	}
	
}