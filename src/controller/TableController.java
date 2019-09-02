package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JTable;

public class TableController {

	public void fillTable(Connection conn, JTable table) {
		ResultSet rs;
		try {
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			ArrayList<String> columns = new ArrayList<String>(columnCount);
			
			// (store column names)
			for (int i =1; i <= columnCount; i++) {
				columns.add(md.getColumnName(i));
			}
			
			ArrayList<String>[][] data = new ArrayList[0][columnCount];
		}
		
	}
}
