package view;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.Monthly;
import model.MonthlyTableModel;

import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
	private JTable monthlyTable;
	private JTextField searchField;
	public MainFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Monthly Goals");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 361);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setBounds(10, 11, 46, 14);
		panel.add(searchLabel);
		
		searchField = new JTextField();
		searchLabel.setLabelFor(searchField);
		searchField.setBounds(59, 8, 131, 23);
		panel.add(searchField);
		searchField.setColumns(10);
		
		JButton btnAddGoal = new JButton("Add Goal");
		btnAddGoal.setBounds(10, 36, 180, 23);
		panel.add(btnAddGoal);
		
		JButton btnDeleteGoal = new JButton("Delete Goal");
		btnDeleteGoal.setBounds(10, 70, 180, 23);
		panel.add(btnDeleteGoal);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 157, 169, 14);
		panel.add(lblDescription);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(199, 0, 385, 361);
		getContentPane().add(panel_1);
		
		JTextArea descriptionArea = new JTextArea();
		descriptionArea.setBounds(10, 182, 180, 168);
		panel.add(descriptionArea);
		lblDescription.setLabelFor(descriptionArea);
		descriptionArea.setLineWrap(true);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 385, 361);
		panel_1.add(scrollPane);
		
		monthlyTable = new JTable();
		monthlyTable.setFillsViewportHeight(true);
		monthlyTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		monthlyTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(monthlyTable);
		MonthlyTableModel model = new MonthlyTableModel();
		monthlyTable.setModel(model);
		JTableHeader monthlyTableHeader = monthlyTable.getTableHeader();
		monthlyTableHeader.setBackground(new Color(17, 16, 47));
		monthlyTableHeader.setForeground(Color.WHITE);
		// Fill description textbox when selection is made
		monthlyTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (monthlyTable.getSelectedRow() > -1) {
					Monthly goal = model.getGoal(monthlyTable.getSelectedRow());
					String descText = goal.getDescription();
					descriptionArea.setText(descText);
				}
			}
		});
	}
}
