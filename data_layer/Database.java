package data_layer;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

/**
 * @author Kim, Pawel
 * @version 7
 * Database access class
 * Methods are all private or default, so that they can only be used by other data layer classes.
*/
public class Database {
	
	private Connection connection;
	private String username = "root";
	private String password = "";
	private String location = "jdbc:mysql://localhost/facultyactivity";
	private boolean autoCommit; // autocommit setting before changes
	Logger logger;
	
	/**
	 * default constructor
	*/
	Database() {
		logger = Logger.getLogger("Log");
		try {
		    FileHandler handler = new FileHandler("logs/LogFile.log", true);
		    handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (SecurityException e) {
        } catch (IOException e) {
        }
		
	}
	
	/**
	 * constructor with custom connection values
	 * @param user String username
	 * @param pass String password
	 * @param uri String connection string with database location
	*/
	Database(String user, String pass, String uri) {
		username = user;
		password = pass;
		location = uri;
	}
	
	/**
		Connect to the database
	*/
	void connect() throws Exception {
		try {
            connection = DriverManager.getConnection(location, username, password);
            autoCommit = connection.getAutoCommit();
            //logger.info("Connection made"); 
        } catch (SQLException x) {
				logger.warning("Failed to connect: " + x.getMessage() + "\n" + x.getStackTrace());
				throw new Exception();
        }
	}
	
	/**
	 * Close the database connection
	*/
	void close() throws Exception {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException x) {
            logger.warning("Failed to close connection: " + x.getMessage() + "\n" + x.getStackTrace()); 
				throw new Exception();
        }
	}
	
	/**
	 * Begin a transaction
	*/
	void startTrans() throws Exception {
		try {
			connection.setAutoCommit(false);
		}
		catch (Exception x) {
			logger.warning("Failed to start transaction: " + x.getMessage() + "\n" + x.getStackTrace());
			throw new Exception();
		}
	}
	
	/**
	 * End a transaction
	*/
	void endTrans() throws Exception {
		try {
			connection.commit();
		}
		catch (Exception x) {
			
		}
		finally {
			try {
				connection.setAutoCommit(autoCommit);
			}
			catch (Exception x) {
				logger.warning("Failed to end transaction: " + x.getMessage() + "\n" + x.getStackTrace());
				throw new Exception();
			}
		}
	}
	
	/**
	 * Rollback a transaction
	*/
	void rollbackTrans() throws Exception {
		try {
			connection.rollback();
		}
		catch (Exception x) {
			logger.warning("Failed to rollback transaction: " + x.getMessage() + "\n" + x.getStackTrace());
		}
		finally {
			try {
				connection.setAutoCommit(autoCommit);
			}
			catch (Exception x) {
				logger.warning("Failed to reset autocommit after failing to rollback a transaction: " + x.getMessage() + "\n" + x.getStackTrace());
				throw new Exception();
			}
		}
	}
	
	/**
	 * Retrieve data using a prepared SELECT statement
	 * @param query String prepared statement
	 * @param values ArrayList<String> parameters for the prepared statement
	 * @return ArrayList<ArrayList<String>> results of the perpared statement
	*/
	ArrayList<ArrayList<String>> getData(String query, ArrayList<String> values) throws Exception {
		try {
			// run query and get results
			PreparedStatement stmt = prepareStatement(query, values);
			ResultSet results = stmt.executeQuery();
			
			// convert result set to array list
			ResultSetMetaData meta = results.getMetaData();
			int numCols = meta.getColumnCount();
			
			// instantiate master ArrayList
			ArrayList resultList = new ArrayList();
			
			// put results into arraylist
			while (results.next()) {
			
				// instantiate sub-array-list
				ArrayList subList = new ArrayList();
				
				//add to arraylist
				for (int i=1; i<(numCols + 1); i++) {
					subList.add(results.getString(i));
				}
				
				resultList.add(subList);
			}
			
			return resultList;
			
		}
		catch (Exception x) {
			logger.warning("Failed to get data via prepared staement: " + x.getMessage() + "\n" + x.getStackTrace());
			throw new Exception();
		}
		
	}
	
	
	/**
	 * Change data using a prepared UPDATE, INSERT or DELETE statement
	 * @param query String prepared statement
	 * @param values ArrayList<String> parameters for the prepared statement
	 * @return int number of records changed by the update, or -1 if update failed
	*/
	int setData(String query, ArrayList<String> values) throws Exception {
		try {
			PreparedStatement stmt = prepareStatement(query, values);
			return stmt.executeUpdate();
		}
		catch (SQLException x) {
      	logger.warning("Failed to change data via prepared statement: " + x.getMessage() + "\n" + x.getStackTrace());
			throw new Exception();
      }
	}
	
	/**
	 * Sets up and returns a prepared statement
	 * @param query String the prepared statement string
	 * @param values ArrayList<String> parameters for the prepared statement
	 * @return PreparedStatement the prepared statement with the parameters added
	*/
	private PreparedStatement prepareStatement(String query, ArrayList<String> values) throws Exception {
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			for (int i=0; i < values.size(); i++) {
				statement.setString((i+1), values.get(i));
			}
			return statement;
		}
		catch (Exception x) {
			logger.warning("Failed to creat prepared statement: " + x.getMessage() + "\n" + x.getStackTrace());
			throw new Exception();
		}
	}
	
}