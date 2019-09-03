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

package controller;

import java.sql.*;

import model.DatabaseManager;
import model.Monthly;

public class MonthlyController {
	private Monthly goal;
	private Connection conn;
	
	public MonthlyController(Monthly goal) {
		this.goal = goal;
	}

	public void deleteGoal() {
		try {
			Connection conn = DatabaseManager.getConnection();
			String query = "DELETE FROM monthly WHERE monthly_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, goal.getID());
			stmt.execute();
		} catch(SQLException e) {
			
		} finally {
			DatabaseManager.closeConnection(conn);
		}
	}
	
	public void addGoal() {
		try {
			Connection conn = DatabaseManager.getConnection();
			String query = "INSERT INTO `monthly` (date, title, description, completed) VALUES (?, ?, ? ,?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDate(1, (Date) goal.getDate().getTime());
			stmt.setString(2, goal.getTitle());
			stmt.setString(3, goal.getDescription());
			stmt.setBoolean(4, false);
			stmt.executeUpdate();
			DatabaseManager.closeStatement(stmt);
			DatabaseManager.closeConnection(conn);
		} catch(SQLException e) {
			
		}
	}
	
	public void setGoal(Monthly goal) {
		this.goal = goal;
	}
}
