package view;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	private JTable monthlyTable;
	private JTextField searchField;
	public MainFrame() {
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
		searchField.setBounds(55, 8, 135, 23);
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 175, 180, 175);
		panel.add(textArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(199, 0, 385, 361);
		getContentPane().add(panel_1);
		
		monthlyTable = new JTable();
		monthlyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		monthlyTable.setFillsViewportHeight(true);
		panel_1.add(monthlyTable);
	}
}
