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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Stores the data for each monthly goal
 * @author Nick Pafundi
 *
 */
public class Monthly {
    private int monthly_id;
    public Calendar date = Calendar.getInstance();
    public String title;
    public String description;
    public boolean completed;

    /**
     * @param rs The MySQL ResultSet containing the data to instantiate a Monthly object
     */
    public Monthly(ResultSet rs) {
    	try {
            monthly_id = rs.getInt("monthly_id");
            date.setTime(rs.getDate("date"));
            title = rs.getString("title");
            description = rs.getString("description");
            completed = rs.getBoolean("completed");
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }
    
    /**
     * Instantiates a Monthly object based on a given title, date and description. Completed is set to false by default. (You wouldn't create a goal you've already completed)
     * @param title The title for the goal
     * @param date The date for the goal
     * @param desc The description of the goal
     */
    public Monthly(String title, Date date, String desc) {
    	this.title = title;
    	this.date.setTime(date);
    	this.description = desc;
    	completed = false;
    }

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	/**
	 * Used by the MonthlyTableModel to return the goal's month.
	 * @return Returns a string representation of the goal's month. If the month's int value is outside the bounds, will display "error"
	 */
	public String getMonth() {
		int monthNum = Calendar.MONTH;
		String month = "error";
		if (monthNum >= 0 && monthNum <= 11) {
			month = date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		}
		return month;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public int getID() {
		return monthly_id;
	}
}
