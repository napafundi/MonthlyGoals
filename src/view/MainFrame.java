package view;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.MonthlyController;
import model.Monthly;
import model.MonthlyTableModel;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

/**
 * The main GUI view of the application. Includes a table, a series of buttons and a search field.
 * @author Nick Pafundi
 *
 */
public class MainFrame extends JFrame {
	private JPanel panel;
	private JLabel searchLabel;
	private JButton btnAddGoal;
	private JButton btnDeleteGoal;
	private JLabel lblDescription;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable monthlyTable;
	private JTextField searchField;
	private JTextArea descriptionArea;
	JButton updateDescBtn;
	MonthlyTableModel model = new MonthlyTableModel();
	TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(model);
	
	public MainFrame(Dimension screenDim) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Monthly Goals");
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 200, 361);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		searchLabel = new JLabel("Search:");
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setBounds(10, 11, 46, 14);
		panel.add(searchLabel);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			/**
			 * Filter the table values when the searchField text changes
			 */
			@Override
			public void keyReleased(KeyEvent arg0) {
				String searchTxt = searchField.getText();
				MonthlyController controller = new MonthlyController(model);
				controller.setFilter(searchTxt, rowSorter);
			}
		});
		searchLabel.setLabelFor(searchField);
		searchField.setBounds(59, 8, 131, 23);
		panel.add(searchField);
		searchField.setColumns(10);
		
		btnAddGoal = new JButton("Add Goal");
		btnAddGoal.addMouseListener(new MouseAdapter() {
			/**
			 * Show addView frame when clicked
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddView addView = new AddView(model);
				addView.setSize(new Dimension(314, 455));
				// Place frame in center of screen
				addView.setLocation(screenDim.width/2 - addView.getSize().width/2, screenDim.height/2 - addView.getSize().height/2);
				addView.setVisible(true);
			}
		});
		btnAddGoal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddGoal.setBounds(10, 36, 180, 23);
		panel.add(btnAddGoal);
		
		btnDeleteGoal = new JButton("Delete Goal");
		btnDeleteGoal.addMouseListener(new MouseAdapter() {
			/**
			 * Attempt to delete selected goal upon clicking
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (monthlyTable.getSelectedRow() > -1) {
					Monthly goal = model.getGoal(monthlyTable.getSelectedRow());
					int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this goal? It cannot be undone.", null, JOptionPane.YES_NO_OPTION);
					MonthlyController controller = new MonthlyController(model);
					controller.deleteGoal(option, goal);
					if (monthlyTable.getSelectedRow() == -1) { // When goal is deleted and model is updated, the row disappears and getSelectedRow should be -1, so descriptionArea text should become empty
						descriptionArea.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a goal to be deleted.");
				}
			}
		});
		btnDeleteGoal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteGoal.setBounds(10, 70, 180, 23);
		panel.add(btnDeleteGoal);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 135, 169, 14);
		panel.add(lblDescription);
		
		panel_1 = new JPanel();
		panel_1.setBounds(199, 0, 385, 361);
		getContentPane().add(panel_1);
		
		descriptionArea = new JTextArea();
		descriptionArea.setBorder(UIManager.getBorder("TextField.border"));
		descriptionArea.setBounds(10, 151, 180, 168);
		panel.add(descriptionArea);
		lblDescription.setLabelFor(descriptionArea);
		descriptionArea.setLineWrap(true);
		
		updateDescBtn = new JButton("Update Description");
		updateDescBtn.addMouseListener(new MouseAdapter() {
			/**
			 * Attempt to update the selected goal's description upon click
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (monthlyTable.getSelectedRow() > -1) {
					Monthly goal = model.getGoal(monthlyTable.getSelectedRow());
					String newDesc = descriptionArea.getText();
					MonthlyController controller = new MonthlyController(model);
					String result = controller.updateDescription(newDesc, goal);
					JOptionPane.showMessageDialog(null, result);
				} else {
					JOptionPane.showMessageDialog(null, "Please select a goal first.");
				}
			}
		});
		updateDescBtn.setBounds(10, 323, 180, 26);
		panel.add(updateDescBtn);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 385, 361);
		panel_1.add(scrollPane);
		
		monthlyTable = new JTable();
		monthlyTable.setFillsViewportHeight(true);
		monthlyTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		monthlyTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(monthlyTable);
		monthlyTable.setModel(model);
		monthlyTable.setRowSorter(rowSorter);
		JTableHeader monthlyTableHeader = monthlyTable.getTableHeader();
		monthlyTableHeader.setBackground(new Color(17, 16, 47));
		monthlyTableHeader.setForeground(Color.WHITE);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 
		centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Set default cell alignment to center text
		monthlyTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		monthlyTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		monthlyTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			/**
			 * Fill description text box when selection is made
			 */
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
