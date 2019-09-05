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

package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;

import controller.MonthlyController;
import model.Monthly;
import model.MonthlyTableModel;

import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * A pop-up frame that allows users to add a goal to the db and model
 * @author Nick Pafundi
 *
 */
public class AddView extends JFrame {
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel descLabel;
	private JTextField titleField;
	private JCalendar calendarField;
	private JTextArea descriptionField;
	private JSplitPane splitPane;
	private JButton addButton;
	JButton cancelButton;
	public AddView(MonthlyTableModel model) {
		setTitle("Add Goal");
		getContentPane().setLayout(null);
		
		titleLabel = new JLabel("Title:");
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titleLabel.setBounds(10, 11, 68, 14);
		getContentPane().add(titleLabel);
		
		dateLabel = new JLabel("Date:");
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setBounds(10, 36, 68, 14);
		getContentPane().add(dateLabel);
		
		descLabel = new JLabel("Description:");
		descLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descLabel.setBounds(10, 198, 68, 14);
		getContentPane().add(descLabel);
		
		titleField = new JTextField();
		titleField.setBounds(88, 8, 198, 25);
		getContentPane().add(titleField);
		titleField.setColumns(10);
		
		calendarField = new JCalendar();
		calendarField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendarField.getDayChooser().getDayPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendarField.setWeekOfYearVisible(false);
		calendarField.setTodayButtonVisible(true);
		calendarField.setBounds(88, 36, 198, 153);
		getContentPane().add(calendarField);
		
		descriptionField = new JTextArea();
		descriptionField.setWrapStyleWord(true);
		descriptionField.setLineWrap(true);
		descriptionField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		descriptionField.setBounds(88, 193, 198, 176);
		getContentPane().add(descriptionField);
		
		splitPane = new JSplitPane();
		splitPane.setBounds(10, 376, 280, 25);
		getContentPane().add(splitPane);
		
		addButton = new JButton("Add Goal");
		addButton.addMouseListener(new MouseAdapter() {
			/**
			 * Add a goal to the db and model
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				String title = titleField.getText();
				Date date = calendarField.getDate();
				String desc = descriptionField.getText().trim(); // Must use trim to remove whitespace and newline characters, aids in checking if desc is empty
				MonthlyController controller = new MonthlyController(model);
				String result = controller.addGoal(title, date, desc);
				JOptionPane.showMessageDialog(null, result);
				if ("Goal successfully added".equals(result)) {
					dispose(); // Close the addView on success, else let the user enter the correct information
				}
			}
		});
		addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		splitPane.setLeftComponent(addButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		splitPane.setRightComponent(cancelButton);
		splitPane.setDividerLocation(0.5);
	}
}
