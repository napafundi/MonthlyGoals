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

import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Monthly;
import model.MonthlyTableModel;


/**
 * A controller for MonthlyTableModel that prevents invalid data from being passed to the model
 * @author Nick Pafundi
 *
 */
public class MonthlyController {
	private MonthlyTableModel model;
	
	public MonthlyController(MonthlyTableModel model) {
		this.model = model;
	}
	
	/**
	 * Check to make sure title and description inputs are valid before sending to the MonthlyTableModel
	 * @param title The title of the goal to be added
	 * @param date The date of the goal to be added
	 * @param desc The description of the goal to be added
	 * @return Returns a string indicating the success/failure of adding the goal, which is displayed by a JOptionPane
	 */
	public String addGoal(String title, Date date, String desc) {
		if (title.equals("") || title.length() > 30) { // Make sure title isn't empty or longer than 30 char
			return "Please enter a goal title. (MAX 30 CHARACTERS)";
		} else if (desc.contentEquals("") || desc.length() > 60) { // Make sure description isn't empty or longer than 60 char
			return "Please enter a goal description (MAX 60 CHARACTERS)";
		} else {
			Monthly newGoal = new Monthly(title, date, desc);
			return model.addGoal(newGoal);
		}
	}
	
	/**
	 * Check to make sure the user selected yes to deleting the goal before sending to MonthlyTableModel for attempted deletion
	 * @param option Integer representation of the selection made. (YES or NO)
	 * @param goal The Monthly goal object to be deleted
	 */
	public void deleteGoal(int option, Monthly goal) {
		if (option == JOptionPane.YES_OPTION) {
			model.deleteGoal(goal);
		}
	}
	
	/**
	 * Check to make sure the new description to be updated for a goal is non-empty and less than 60 characters long
	 * @param newDesc The new description to be checked
	 * @param goal The goal whose description will be updated
	 * @return Returns a string indicating the success/failure of updating the description, which is displayed by a JOptionPane
	 */
	public String updateDescription(String newDesc, Monthly goal) {
		if (newDesc.length() <= 60 && !"".equals(newDesc)) {
			return model.updateDescription(newDesc, goal);
		} else {
			return "New description must be non-empty and less than 60 characters";
		}
	}
	
	/**
	 * Filter the table based on text within the searchField
	 * @param searchTxt The filter text to be applied to the table
	 * @param rowSorter The TableRowSorter object that filters the results based on the new filter
	 */
	public void setFilter(String searchTxt, TableRowSorter<TableModel> rowSorter) {
		if (searchTxt.trim().length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxt));
		}
	}
}
