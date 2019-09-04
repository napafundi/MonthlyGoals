/*
 * The MIT License
 *
 * Copyright 2019 Nick Pafundi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package model;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

public class MonthlyTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Monthly> goals = new ArrayList<Monthly>();
	
	private String[] columnNames = new String[] {
			"Month", "Title", "Completed"
	};
	
	public MonthlyTableModel() {
		try {
			Connection conn = DatabaseManager.getConnection();
			String query = "SELECT * FROM monthly";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Monthly m = new Monthly(rs);
				goals.add(m);
			}
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		} catch (SQLException e) {
			
		}
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Object temp = null;
		if (col == 0) {
			temp = goals.get(row).getMonth();
		} else if ( col == 1) {
			temp = goals.get(row).getTitle();
		} else {
			temp = new Boolean(goals.get(row).isCompleted());
		}
		return temp;
	}

	@Override
	public int getColumnCount() {
		return 3; // Constant for this model
	}
	
	/*
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column
     * would contain text ("true"/"false"), rather than a check box.
     */
	@Override
    public Class<?> getColumnClass(int c) {
      return getValueAt(0, c).getClass();
    }
    
    public String getColumnName(int col) {
    	return columnNames[col];
    }

	@Override
	public int getRowCount() {
		return goals.size();
	}
	
	public Monthly getGoal(int ind) {
		return goals.get(ind);
	}
	
	public void deleteGoal(Monthly goal) {
		try {
			Connection conn = DatabaseManager.getConnection();
			String query = "DELETE FROM monthly WHERE monthly_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, goal.getID());
			stmt.execute();
			DatabaseManager.closeConnection(conn);
			fireTableDataChanged();
		} catch(SQLException e) {
			
		}
	}
	
	public String addGoal(Monthly goal) {
		try {
			Connection conn = DatabaseManager.getConnection();
			String query = "INSERT INTO `monthly` (date, title, description, completed) VALUES (?, ?, ? ,?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			Date utilDate = (java.util.Date) goal.getDate().getTime();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			stmt.setDate(1, sqlDate);
			stmt.setString(2, goal.getTitle());
			stmt.setString(3, goal.getDescription());
			stmt.setBoolean(4, false);
			stmt.executeUpdate();
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
			goals.add(goal);
			fireTableDataChanged();
			return "Goal successfully added";
		} catch(SQLException e) {
			
		}
		return "There was an error connecting to the database.";
	}

}
