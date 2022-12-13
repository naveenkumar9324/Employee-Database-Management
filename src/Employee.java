import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;

public class Employee {

	private JFrame frame;
	private JTextField jtxtEMployeeID;
	private JTable table_3;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	private JTextField jtxtNInumber;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField jtxtFirstName;
	private JTextField jtxtSurname;
	private JTextField jtxtGender;
	private JTextField jtxtDOB;
	private JTextField jtxtAge;
	private JTextField jtxtSalary;
	
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 * 
	 */
	
	
	public void updateTable()
	{
		conn = EmployeeData.ConnectDB();
		
		if (conn != null)
		{
			String sql = " Select EmpID, NINumber, Firstname, Surname, Gender, DOB, Age, Salary";
			
		
		try 
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnData= new Object[8];
			
			while(rs.next()) {
				columnData [0] = rs.getString("EmpID");
				columnData [1] = rs.getString("NINumber");
				columnData [2] = rs.getString("Firstname");
				columnData [3] = rs.getString("Surname");
				columnData [4] = rs.getString("Gender");
				columnData [5] = rs.getString("DOB");
				columnData [6] = rs.getString("Age");
				columnData [7] = rs.getString("Salary");
				
				model.addRow(columnData);
			}
		}
		  catch(Exception e)
		{
			  JOptionPane.showMessageDialog(null, e);
		}
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
		
		conn = EmployeeData.ConnectDB();
		Object col[] = {"EmpID", "NINumber", "Firstname", "Surname", "Gender", "DOB", "Age", "Salary"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 0));
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(46, 162, 209, 46);
		frame.getContentPane().add(lblNewLabel);
		
		jtxtEMployeeID = new JTextField();
		jtxtEMployeeID.setBounds(292, 166, 234, 46);
		frame.getContentPane().add(jtxtEMployeeID);
		jtxtEMployeeID.setColumns(10);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.setBackground(new Color(0, 64, 128));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "INSERT INTO employee(EmpID, NINumber, Firstname, Surname, Gender, DOB, Age, Salary)VALUES(?,?,?,?,?,?,?,?)";
				
				try
				{
					pst = conn.prepareStatement(sql);
					pst.setString(1, jtxtEMployeeID.getText());
					pst.setString(2, jtxtNInumber.getText());
					pst.setString(3, jtxtFirstName.getText());
					pst.setString(4, jtxtSurname.getText());
					pst.setString(5, jtxtGender.getText());
					pst.setString(6, jtxtDOB.getText());
					pst.setString(7, jtxtAge.getText());
					pst.setString(8, jtxtSalary.getText());
					
					pst.execute();
					
					rs.close();
					pst.close();
				}
				catch(Exception ev)
				{
					JOptionPane.showConfirmDialog(null, "System Update Completed");
					
				}
				DefaultTableModel model = (DefaultTableModel) table_3.getModel();
				model.addRow(new Object[] {
						
						jtxtEMployeeID.getText(),
						jtxtNInumber.getText(),
					    jtxtFirstName.getText(),
			            jtxtSurname.getText(),
						jtxtGender.getText(),
						jtxtDOB.getText(),
						jtxtAge.getText(),
						jtxtSalary.getText(),
				});		
				
				if(table_3.getSelectedRow()==-1) {
					if(table_3.getRowCount()==0) {
						JOptionPane.showMessageDialog(null, "Membership Update confirmed", "Employee Database System",
								JOptionPane.OK_OPTION);
					}
				}
				
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAddNew.setBounds(46, 690, 191, 46);
		frame.getContentPane().add(btnAddNew);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(553, 162, 873, 438);
		frame.getContentPane().add(scrollPane);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EmpID", "NINumber", "Firstname", "Surname", "Gender", "DOB", "Age", "Salary"
			}
		));
		scrollPane.setViewportView(table_3);
		
		lblNewLabel_1 = new JLabel("NI Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(46, 225, 209, 50);
		frame.getContentPane().add(lblNewLabel_1);
		
		jtxtNInumber = new JTextField();
		jtxtNInumber.setColumns(10);
		jtxtNInumber.setBounds(292, 231, 234, 46);
		frame.getContentPane().add(jtxtNInumber);
		
		lblNewLabel_2 = new JLabel("Firstname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(46, 297, 209, 50);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Surname");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(46, 357, 209, 50);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setBounds(46, 417, 209, 50);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("DOB");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_5.setBounds(46, 477, 209, 50);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Age");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_6.setBounds(46, 537, 209, 50);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Salary");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_7.setBounds(46, 597, 209, 50);
		frame.getContentPane().add(lblNewLabel_7);
		
		jtxtFirstName = new JTextField();
		jtxtFirstName.setColumns(10);
		jtxtFirstName.setBounds(292, 303, 234, 46);
		frame.getContentPane().add(jtxtFirstName);
		
		jtxtSurname = new JTextField();
		jtxtSurname.setColumns(10);
		jtxtSurname.setBounds(292, 363, 234, 46);
		frame.getContentPane().add(jtxtSurname);
		
		jtxtGender = new JTextField();
		jtxtGender.setColumns(10);
		jtxtGender.setBounds(292, 421, 234, 49);
		frame.getContentPane().add(jtxtGender);
		
		jtxtDOB = new JTextField();
		jtxtDOB.setColumns(10);
		jtxtDOB.setBounds(292, 483, 234, 46);
		frame.getContentPane().add(jtxtDOB);
		
		jtxtAge = new JTextField();
		jtxtAge.setColumns(10);
		jtxtAge.setBounds(292, 543, 234, 46);
		frame.getContentPane().add(jtxtAge);
		
		jtxtSalary = new JTextField();
		jtxtSalary.setColumns(10);
		jtxtSalary.setBounds(292, 603, 234, 46);
		frame.getContentPane().add(jtxtSalary);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBackground(new Color(128, 0, 128));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header= new MessageFormat("Printing in Progress");

				MessageFormat footer= new MessageFormat("Printing in Progress");
				
				try
				{
					table_3.print();
				}
				catch(java.awt.print.PrinterException ev) {
					System.err.format("No Printer found", ev.getMessage());
				}


			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnPrint.setBounds(360, 690, 191, 46);
		frame.getContentPane().add(btnPrint);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(128, 128, 0));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jtxtEMployeeID.setText(null);
				jtxtNInumber.setText(null);
				jtxtFirstName.setText(null);
				jtxtSurname.setText(null);
				jtxtGender.setText(null);
				jtxtDOB.setText(null);
				jtxtAge.setText(null);
				jtxtSalary.setText(null);
				
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnReset.setBounds(642, 690, 191, 46);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame =new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame,"Confirm if you want to exit", "Employee Database Sytstem",
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnExit.setBounds(947, 690, 191, 46);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel_8 = new JLabel("Employee Database Management System");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_8.setBounds(435, 29, 632, 46);
		frame.getContentPane().add(lblNewLabel_8);
	}
}
