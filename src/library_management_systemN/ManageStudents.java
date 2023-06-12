package library_management_systemN;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ManageStudents extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Studenid;
	private JTextField textField_Studentname;

	private JComboBox comboBox_course;
	private JComboBox comboBox_branch;
	private JTable StudentDetails_table;

	// Chart variables
	public String Student_name, course, branch;
	public int Student_id;
	public DefaultTableModel model;

	/**
	 * Launch the application from here XD
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageStudents frame = new ManageStudents();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setStudentDetailsToTable() {

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			ResultSet resultset = statement.executeQuery("SELECT * FROM student_details");

			while (resultset.next()) {

				// result set in icini yazma asamasındasın buradan devam et

				// Exporting data's from *
				String Student_id = resultset.getString("student_id");
				String BookName = resultset.getString("name");
				String Course = resultset.getString("course");
				String Branch = resultset.getString("branch");

				// Table display
				Object object[] = { Student_id, BookName, Course, Branch };
				model = (DefaultTableModel) StudentDetails_table.getModel();
				model.addRow(object);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cleartable() {

		DefaultTableModel model = (DefaultTableModel) StudentDetails_table.getModel();

		model.setRowCount(0);

	}

	public boolean addstudent() {

		boolean Isadded = false;

		Student_id = Integer.parseInt(textField_Studenid.getText());
		Student_name = textField_Studentname.getText();
		course = comboBox_course.getSelectedItem().toString();
		branch = comboBox_branch.getSelectedItem().toString();

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			String sql = "insert into student_details(student_id,name,course,branch) values(?,?,?,?)";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setInt(1, Student_id);
			preparedstatement.setString(2, Student_name);
			preparedstatement.setString(3, course);
			preparedstatement.setString(4, branch);

			int rowCount = preparedstatement.executeUpdate();

			if (rowCount > 0) {
				Isadded = true;
			} else {
				Isadded = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Isadded;

	}

	public boolean updatestudent() {

		boolean Isupdate = false;

		Student_id = Integer.parseInt(textField_Studenid.getText());
		Student_name = textField_Studentname.getText();
		course = comboBox_course.getSelectedItem().toString();
		branch = comboBox_branch.getSelectedItem().toString();

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			String sql = "update student_details set name = ?,course = ?,branch = ? where student_id = ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setString(1, Student_name);
			preparedstatement.setString(2, course);
			preparedstatement.setString(3, branch);
			preparedstatement.setInt(4, Student_id);

			int rowCount = preparedstatement.executeUpdate();

			if (rowCount > 0) {
				Isupdate = true;
			} else {
				Isupdate = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Isupdate;
	}

	public boolean deletestudent() {

		boolean Isdeleted = false;

		Student_id = Integer.parseInt(textField_Studenid.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);) {

			String sql = "delete from student_details where student_id = ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, Student_id);

			int rowCount = preparedstatement.executeUpdate();

			if (rowCount > 0) {
				Isdeleted = true;
			} else {
				Isdeleted = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Isdeleted;
	}

	public ManageStudents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 814);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(159, 175, 144));
		panel.setBounds(10, 10, 491, 757);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel SignupPage_label_username = new JLabel("Enter Student ID");
		SignupPage_label_username.setFont(new Font("Dialog", Font.BOLD, 20));
		SignupPage_label_username.setBounds(121, 118, 161, 28);
		panel.add(SignupPage_label_username);

		textField_Studenid = new JTextField();
		textField_Studenid.setOpaque(false);
		textField_Studenid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_Studenid.setFont(new Font("Dialog", Font.PLAIN, 13));
		textField_Studenid.setDisabledTextColor(new Color(192, 192, 192));
		textField_Studenid.setColumns(10);
		textField_Studenid.setCaretColor(Color.BLACK);
		textField_Studenid.setBackground(new Color(0, 128, 128));
		textField_Studenid.setBounds(120, 145, 297, 33);
		panel.add(textField_Studenid);

		JLabel SignupPage_Icon_username = new JLabel("");
		SignupPage_Icon_username
				.setIcon(new ImageIcon(ManageStudents.class.getResource("/MyNewIcoıns/icons8-records-25.png")));
		SignupPage_Icon_username.setBounds(59, 134, 50, 44);
		panel.add(SignupPage_Icon_username);

		JLabel SignupPage_label_username_1 = new JLabel("Enter Student Name");
		SignupPage_label_username_1.setFont(new Font("Dialog", Font.BOLD, 20));
		SignupPage_label_username_1.setBounds(121, 240, 217, 28);
		panel.add(SignupPage_label_username_1);

		textField_Studentname = new JTextField();
		textField_Studentname.setOpaque(false);
		textField_Studentname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_Studentname.setFont(new Font("Dialog", Font.PLAIN, 13));
		textField_Studentname.setDisabledTextColor(Color.BLACK);
		textField_Studentname.setColumns(10);
		textField_Studentname.setCaretColor(Color.BLACK);
		textField_Studentname.setBackground(new Color(0, 128, 128));
		textField_Studentname.setBounds(120, 267, 297, 33);
		panel.add(textField_Studentname);

		JLabel SignupPage_Icon_username_1 = new JLabel("");
		SignupPage_Icon_username_1
				.setIcon(new ImageIcon(ManageStudents.class.getResource("/MyNewIcoıns/icons8-book-25.png")));
		SignupPage_Icon_username_1.setBounds(59, 256, 50, 44);
		panel.add(SignupPage_Icon_username_1);

		JLabel SignupPage_label_username_2 = new JLabel("Select Course");
		SignupPage_label_username_2.setFont(new Font("Dialog", Font.BOLD, 20));
		SignupPage_label_username_2.setBounds(121, 365, 161, 28);
		panel.add(SignupPage_label_username_2);

		JLabel SignupPage_Icon_username_2 = new JLabel("");
		SignupPage_Icon_username_2.setIcon(
				new ImageIcon(ManageStudents.class.getResource("/MyNewIcoıns/icons8-registration-25.png")));
		SignupPage_Icon_username_2.setBounds(59, 381, 50, 44);
		panel.add(SignupPage_Icon_username_2);

		JLabel SignupPage_label_username_3 = new JLabel("Select Branch");
		SignupPage_label_username_3.setFont(new Font("Dialog", Font.BOLD, 20));
		SignupPage_label_username_3.setBounds(121, 489, 161, 28);
		panel.add(SignupPage_label_username_3);

		JLabel SignupPage_Icon_username_3 = new JLabel("");
		SignupPage_Icon_username_3
				.setIcon(new ImageIcon(ManageStudents.class.getResource("/MyNewIcoıns/icons8-branch-25.png")));
		SignupPage_Icon_username_3.setBounds(59, 505, 50, 44);
		panel.add(SignupPage_Icon_username_3);

		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton.setOpaque(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (addstudent() == true) {
					JOptionPane.showMessageDialog(btnNewButton, "Student is added.");
					cleartable();
					setStudentDetailsToTable();

				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Student addition is failed...");
				}
			}
		});
		btnNewButton.setBounds(49, 619, 94, 33);
		panel.add(btnNewButton);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBorder(null);
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDelete.setOpaque(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (deletestudent() == true) {
					JOptionPane.showMessageDialog(btnNewButton, "Student is deleted.");
					cleartable();
					setStudentDetailsToTable();
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Student deleting is failed...");
				}

			}
		});
		btnDelete.setBounds(188, 619, 106, 33);
		panel.add(btnDelete);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBorder(null);
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 15));
		btnUpdate.setOpaque(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (updatestudent() == true) {
					JOptionPane.showMessageDialog(btnNewButton, "Student is updated.");
					cleartable();
					setStudentDetailsToTable();
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Student updating is failed...");
				}
			}
		});
		btnUpdate.setBounds(326, 619, 106, 33);
		panel.add(btnUpdate);

		comboBox_course = new JComboBox();
		comboBox_course.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		comboBox_course.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox_course.setModel(new DefaultComboBoxModel(new String[] { "BSC", "MSC", "PHD" }));
		comboBox_course.setBounds(120, 415, 297, 33);
		panel.add(comboBox_course);

		comboBox_branch = new JComboBox();
		comboBox_branch.setModel(new DefaultComboBoxModel(new String[] { "IT", "CS", "ECON", "EEE" }));
		comboBox_branch.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox_branch.setBounds(121, 536, 297, 33);
		panel.add(comboBox_branch);
		
				JLabel lblNewLabel = new JLabel("Back");
				lblNewLabel.setBounds(10, 10, 137, 44);
				panel.add(lblNewLabel);
				lblNewLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						HomePage homepage = new HomePage();
						homepage.setVisible(true);
						dispose();
					}
				});
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblNewLabel.setIcon(new ImageIcon(ManageStudents.class.getResource("/MyNewIcoıns/icons8-back-64.png")));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(238, 240, 242));
		panel_2.setBounds(498, 10, 864, 757);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(431, 5, 1, 1);
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(128, 0, 0));
		panel_2.add(panel_1_1);

		JLabel lblNewLabel_1 = new JLabel("Back");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 10, 113, 26);
		panel_1_1.add(lblNewLabel_1);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setOpaque(false);
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(128, 0, 0));
		panel_1_2.setBounds(774, 5, 69, 46);
		panel_2.add(panel_1_2);

		JLabel lblX = new JLabel("  X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblX.setBounds(10, 10, 60, 26);
		panel_1_2.add(lblX);

		StudentDetails_table = new JTable();
		StudentDetails_table.setSelectionForeground(new Color(0, 0, 0));
		StudentDetails_table.setOpaque(false);
		StudentDetails_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int rowNo = StudentDetails_table.getSelectedRow();
				TableModel model = StudentDetails_table.getModel();

				textField_Studenid.setText(model.getValueAt(rowNo, 0).toString());
				textField_Studentname.setText(model.getValueAt(rowNo, 1).toString());
				comboBox_course.setSelectedItem(model.getValueAt(rowNo, 2).toString());
				comboBox_branch.setSelectedItem(model.getValueAt(rowNo, 3).toString());

			}
		});
		StudentDetails_table.setRowHeight(30);
		StudentDetails_table.setGridColor(new Color(0, 0, 0));
		StudentDetails_table.setForeground(new Color(0, 0, 0));
		StudentDetails_table.setFont(new Font("Tahoma", Font.BOLD, 20));
		StudentDetails_table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		StudentDetails_table.setBackground(new Color(238, 240, 242));
		StudentDetails_table.setSelectionBackground(new Color(218, 221, 216));
		StudentDetails_table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Student ID", "Name", "Branch", "Branch" }));
		StudentDetails_table.setToolTipText("");
		StudentDetails_table.setName("Student Details");
		StudentDetails_table.setBounds(121, 254, 705, 277);
		panel_2.add(StudentDetails_table);

		JLabel lblNewLabel_3 = new JLabel("Manage Students");
		lblNewLabel_3.setIcon(
				new ImageIcon(ManageStudents.class.getResource("/MyNewIcoıns/icons8-hire-me-100.png")));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(314, 31, 317, 94);
		panel_2.add(lblNewLabel_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(159, 175, 144));
		panel_3.setBounds(244, 135, 445, 10);
		panel_2.add(panel_3);

		JLabel lblNewLabel_2 = new JLabel(
				"Student ID                  Name              Course               Branch       ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBackground(new Color(102, 205, 170));
		lblNewLabel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_2.setBounds(121, 231, 705, 23);
		panel_2.add(lblNewLabel_2);

		setStudentDetailsToTable();
	}
}
