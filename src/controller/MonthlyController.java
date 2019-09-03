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

import model.Monthly;
import model.MonthlyTableModel;

public class MonthlyController {
	private MonthlyTableModel model;
	
	public MonthlyController(MonthlyTableModel model) {
		this.model = model;
	}
	
	public void addGoal(String title, Date date, String desc) {
		if (title.equals("") || title.length() > 30) { // Make sure title isn't empty
			JOptionPane.showMessageDialog(null, "Please enter a goal title. (MAX 30 CHARACTERS)");
			return;
		} else if (desc.contentEquals("") || desc.length() > 60) { // Make sure description isn't empty
			JOptionPane.showMessageDialog(null, "Please enter a goal description (MAX 60 CHARACTERS)");
			return;
		} else {
			Monthly newGoal = new Monthly(title, date, desc);
			model.addGoal(newGoal);
		}
	}
}
