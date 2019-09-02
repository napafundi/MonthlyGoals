package controller;

import java.sql.*;


public class TableController {

	public static ListTableModel fillTable(Connection conn) throws Exception{
		try {
			String query = "SELECT * FROM monthly";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			ListTableModel model = ListTableModel.createModelFromResultSet(rs);
			return model;
		} catch(Exception e) {
		    System.err.println(e.getMessage());
		    throw new Exception();
		}
	}
}
