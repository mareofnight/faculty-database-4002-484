package presentation_layer;
import javax.swing.table.*;
import java.util.Vector;

/*
	@author David Desorcie
	@version 11
	Customized version of DefaultTableModel, used in the JTable
*/
public class FacultyTableModel extends DefaultTableModel {
	
	/*
		constructors mimic DefaultTableModel
	*/
	public FacultyTableModel() {
		super();
	}
	public FacultyTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}
	public FacultyTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}
	public FacultyTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}
	public FacultyTableModel(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}
	public FacultyTableModel(Vector data, Vector columnNames) {
		super(data, columnNames);
	}
	
	
	/*
		Whether cells are editable - cells are always not editable
		@return boolean false (cells never editable
	*/
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}