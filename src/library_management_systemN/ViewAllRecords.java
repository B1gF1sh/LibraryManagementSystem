package library_management_systemN;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane; // Allah belanı versin senin
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import rojeru_san.componentes.RSDateChooser;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAllRecords extends JFrame {

	private JPanel contentPane;
	private JTable table_viewallrecords;

	private DefaultTableModel model;

	private RSDateChooser date_issuedate, date_duedate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllRecords frame = new ViewAllRecords();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setIssueBookDetailstoTable() {

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			ResultSet resultset = statement.executeQuery("SELECT * FROM issue_book_details");

			while (resultset.next()) {

				String id = resultset.getString("id");
				String BookName = resultset.getString("book_name");
				String studentname = resultset.getString("student_name");
				String Issuedate = resultset.getString("issue_date");
				String Duedate = resultset.getString("due_date");
				String status = resultset.getString("status");

				// Table display
				Object object[] = { id, BookName, studentname, Issuedate, Duedate, status };
				model = (DefaultTableModel) table_viewallrecords.getModel();
				model.addRow(object);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cleartable() {

		DefaultTableModel model = (DefaultTableModel) table_viewallrecords.getModel();

		model.setRowCount(0);

	}

	public void search() {

		Date uFromdate = date_issuedate.getDatoFecha();
		Date utoDate = date_duedate.getDatoFecha();

		long x = uFromdate.getTime();
		long y = utoDate.getTime();

		java.sql.Date fromDate = new java.sql.Date(y);
		java.sql.Date toDate = new java.sql.Date(x);

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);) {

			String sql = "select *from issue_book_details where issue_date BETWEEN ? to ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setDate(1, fromDate);
			preparedstatement.setDate(2, toDate);

			ResultSet resultset = preparedstatement.executeQuery();

			if (resultset.next() == false) {
				JOptionPane.showMessageDialog(contentPane, "No Record Found!!!");
			} else {
				while (resultset.next()) {

					String id = resultset.getString("id");
					String BookName = resultset.getString("book_name");
					String studentname = resultset.getString("student_name");
					String Issuedate = resultset.getString("issue_date");
					String Duedate = resultset.getString("due_date");
					String status = resultset.getString("status");

					// Table display
					Object object[] = { id, BookName, studentname, Issuedate, Duedate, status };
					model = (DefaultTableModel) table_viewallrecords.getModel();
					model.addRow(object);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public ViewAllRecords() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1380, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(159, 175, 144));
		panel.setBounds(0, 0, 1366, 252);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("     View All Record");
		lblNewLabel_1.setIcon(
				new ImageIcon(ViewAllRecords.class.getResource("/MyNewIcoıns/icons8-library-100.png")));
		lblNewLabel_1.setBounds(553, 23, 392, 100);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 30));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.BLACK);
		lblNewLabel_2.setBounds(553, 134, 392, 6);
		panel.add(lblNewLabel_2);

		date_issuedate = new RSDateChooser();
		date_issuedate.setBounds(153, 178, 296, 45);
		panel.add(date_issuedate);
		date_issuedate.setPlaceholder("Select Issue Date");

		JLabel SignupPage_Icon_username_1_1 = new JLabel("Issue Date");
		SignupPage_Icon_username_1_1.setBounds(10, 178, 132, 36);
		panel.add(SignupPage_Icon_username_1_1);
		SignupPage_Icon_username_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		date_duedate = new RSDateChooser();
		date_duedate.setBounds(721, 178, 297, 45);
		panel.add(date_duedate);
		date_duedate.setPlaceholder("Select Due Date");

		JLabel SignupPage_Icon_username_1_1_1 = new JLabel("Due Date");
		SignupPage_Icon_username_1_1_1.setBounds(579, 178, 132, 36);
		panel.add(SignupPage_Icon_username_1_1_1);
		SignupPage_Icon_username_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (date_issuedate.getDatoFecha() != null && date_duedate.getDatoFecha() != null) {
					cleartable();
					search();
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Please Select a Date!!!");
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(1053, 178, 141, 45);
		panel.add(btnNewButton);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setOpaque(false);
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(128, 0, 0));
		panel_1_2.setBounds(1287, 10, 69, 46);
		panel.add(panel_1_2);

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
		
		JButton button_all = new JButton("Show All");
		button_all.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleartable();
				setIssueBookDetailstoTable();
				
			}
		});
		button_all.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_all.setBounds(1215, 178, 141, 45);
		panel.add(button_all);
		
				JLabel lblNewLabel = new JLabel("Back");
				lblNewLabel.setBounds(10, 10, 141, 46);
				panel.add(lblNewLabel);
				lblNewLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						HomePage frame = new HomePage();

						frame.setVisible(true);
						dispose();
					}
				});
				lblNewLabel.setIcon(new ImageIcon(ViewAllRecords.class.getResource("/MyNewIcoıns/icons8-back-64.png")));
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblNewLabel.setBackground(SystemColor.menu);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 254, 1366, 440);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(682, 5, 1, 1);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(173, 216, 230));
		panel_1.add(panel_2);

		table_viewallrecords = new JTable();
		table_viewallrecords.setOpaque(false);
		table_viewallrecords.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Book Name", "Student Name", "Issue Date", "Due Date", "Status"
			}
		));

		table_viewallrecords.setBounds(106, 106, 1116, 277);
		panel_1.add(table_viewallrecords);
		table_viewallrecords.setToolTipText("");
		table_viewallrecords.setSelectionBackground(new Color(218, 221, 216));
		table_viewallrecords.setRowHeight(30);
		table_viewallrecords.setName("Student Details");
		table_viewallrecords.setGridColor(Color.BLACK);
		table_viewallrecords.setForeground(Color.BLACK);
		table_viewallrecords.setFont(new Font("Tahoma", Font.BOLD, 20));
		table_viewallrecords.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_viewallrecords.setBackground(new Color(238, 240, 242));

		JLabel lblNewLabel_2_1 = new JLabel(
				"ID                             Book Name              Student Name               Issue Date\t                 Due Date\t\t\t\t\t                    Status       ");
		lblNewLabel_2_1.setBounds(106, 83, 1116, 23);
		panel_1.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_1.setBackground(new Color(102, 205, 170));

		setIssueBookDetailstoTable();

	}
}
