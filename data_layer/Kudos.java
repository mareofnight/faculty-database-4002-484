package data_layer;
import data_layer.*;
import java.util.ArrayList;

/**
 * @author David Desorcie
 * @version 8
 * Manages a list of business layer Kudos records
*/
public class Kudos extends Records {
	
	protected static String TABLE_NAME = Kudo.TABLE_NAME;
	protected static String[] attrNames = Kudo.attrNames;
	
	/**
	 * Basic constructor
	*/
	public Kudos() {
		super();
	}
	
	/**
	 * Constructor for listing all of a user's records of type
	 * @param id String the ID of the user for which the courses are listed
	*/
	public Kudos(String id) {
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
				newList.add(new Kudo(resultList.get(i)));
			}
			
			// replace old array list with new array list
			setList(newList);
		}
		
		catch (Exception x) {
		}
	}
	
}