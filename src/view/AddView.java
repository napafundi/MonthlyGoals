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
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class AddView extends JFrame {
	private JTextField titleField;
	public AddView() {
		setTitle("Add Goal");
		getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Title:");
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titleLabel.setBounds(10, 11, 68, 14);
		getContentPane().add(titleLabel);
		
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setBounds(10, 36, 68, 14);
		getContentPane().add(dateLabel);
		
		JLabel descLabel = new JLabel("Description:");
		descLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descLabel.setBounds(10, 198, 68, 14);
		getContentPane().add(descLabel);
		
		titleField = new JTextField();
		titleField.setBounds(88, 8, 198, 25);
		getContentPane().add(titleField);
		titleField.setColumns(10);
		
		JCalendar calendarField = new JCalendar();
		calendarField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendarField.getDayChooser().getDayPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendarField.setWeekOfYearVisible(false);
		calendarField.setTodayButtonVisible(true);
		calendarField.setBounds(88, 36, 198, 153);
		getContentPane().add(calendarField);
		
		JTextArea descriptionField = new JTextArea();
		descriptionField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		descriptionField.setWrapStyleWord(true);
		descriptionField.setBounds(88, 193, 198, 176);
		getContentPane().add(descriptionField);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(10, 376, 280, 25);
		getContentPane().add(splitPane);
		
		JButton addButton = new JButton("Add Goal");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String title = titleField.getText();
				Date date = calendarField.getDate();
				String desc = descriptionField.getText().trim(); // Must use trim to remove whitespace and newline characters, aids in checking if desc is empty
				if (title.equals("") || title.length() > 30) { // Make sure title isn't empty
					JOptionPane.showMessageDialog(null, "Please enter a goal title. (MAX 30 CHARACTERS)");
					titleField.requestFocus();
					return;
				} else if (desc.contentEquals("") || desc.length() > 60) { // Make sure description isn't empty
					JOptionPane.showMessageDialog(null, "Please enter a goal description (MAX 60 CHARACTERS)");
					descriptionField.requestFocus();
					return;
				}
			}
		});
		addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		splitPane.setLeftComponent(addButton);
		
		// Closes the addView frame upon clicking
		JButton cancelButton = new JButton("Cancel");
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
