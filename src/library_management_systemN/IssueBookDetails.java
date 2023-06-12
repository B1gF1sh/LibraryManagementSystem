package library_management_systemN;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class IssueBookDetails extends JFrame {

	private JPanel contentPane;
	private JTable table_returnedbooks;
	private DefaultTableModel model;

	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBookDetails frame = new IssueBookDetails();
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

			ResultSet resultset = statement.executeQuery("select * FROM issue_book_details where status = '"+"pending"+"'");

			while (resultset.next()) {

				String id = resultset.getString("id");
				String BookName = resultset.getString("book_name");
				String studentname = resultset.getString("student_name");
				String Issuedate = resultset.getString("issue_date");
				String Duedate = resultset.getString("due_date");
				String status = resultset.getString("status");

				// Table display
				Object object[] = { id, BookName, studentname, Issuedate, Duedate, status };
				model = (DefaultTableModel) table_returnedbooks.getModel();
				model.addRow(object);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public IssueBookDetails() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1204, 689);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 240, 242));
		panel.setBounds(0, 0, 1190, 652);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1_1_1 = new JLabel("     Return Book");
		lblNewLabel_1_1_1
				.setIcon(new ImageIcon(IssueBookDetails.class.getResource("/MyNewIcoıns/icons8-book-100.png")));
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(425, 27, 363, 103);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setOpaque(true);
		lblNewLabel_2_1_1.setBackground(Color.BLACK);
		lblNewLabel_2_1_1.setBounds(425, 140, 333, 5);
		panel.add(lblNewLabel_2_1_1);
		
		table_returnedbooks = new JTable();
		table_returnedbooks.setSelectionForeground(new Color(0, 0, 0));
		table_returnedbooks.setOpaque(false);
		table_returnedbooks.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table_returnedbooks.setToolTipText("");
		table_returnedbooks.setSelectionBackground(new Color(218, 221, 216));
		table_returnedbooks.setRowHeight(30);
		table_returnedbooks.setName("Student Details");
		table_returnedbooks.setGridColor(Color.BLACK);
		table_returnedbooks.setForeground(Color.BLACK);
		table_returnedbooks.setFont(new Font("Dialog", Font.BOLD, 20));
		table_returnedbooks.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_returnedbooks.setBackground(new Color(238, 240, 242));
		table_returnedbooks.setBounds(48, 262, 1116, 277);
		panel.add(table_returnedbooks);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID                               Book Name           Student Name      \t         Issue Date\t          \t\t\t\t\tDue Date\t\t\t\t                    Status       ");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_1.setBackground(new Color(102, 205, 170));
		lblNewLabel_2_1.setBounds(48, 240, 1116, 23);
		panel.add(lblNewLabel_2_1);
		
				JLabel lblNewLabel = new JLabel("Back");
				lblNewLabel.setBounds(10, 10, 138, 41);
				panel.add(lblNewLabel);
				lblNewLabel.setIcon(new ImageIcon(IssueBookDetails.class.getResource("/MyNewIcoıns/icons8-back-64.png")));
				lblNewLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						HomePage homepage = new HomePage();

						homepage.setVisible(true);
						dispose();
					}
				});
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblNewLabel.setBackground(SystemColor.menu);
		
		setIssueBookDetailstoTable();
	}

}
