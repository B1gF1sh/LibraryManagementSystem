package library_management_systemN;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class DefaulterList extends JFrame {

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
					DefaulterList frame = new DefaulterList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setIssueBookDetailstoTable() {
		
		long x = System.currentTimeMillis();
		Date todaysdate = new Date(x);

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			PreparedStatement preparedstatement = connection.prepareStatement("select * FROM issue_book_details where due_date < ? and status = ?");
			
			preparedstatement.setDate(1, todaysdate);
			preparedstatement.setString(2, "pending");
			
			ResultSet resultset = preparedstatement.executeQuery();

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
	public DefaulterList() {
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

		JLabel lblNewLabel_1_1_1 = new JLabel("     Defaulter List");
		lblNewLabel_1_1_1
				.setIcon(new ImageIcon(DefaulterList.class.getResource("/AddNewBookIcons/icons8_Book_50px_1.png")));
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1_1_1.setBounds(461, 34, 269, 69);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setOpaque(true);
		lblNewLabel_2_1_1.setBackground(Color.BLACK);
		lblNewLabel_2_1_1.setBounds(426, 113, 333, 5);
		panel.add(lblNewLabel_2_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(128, 128, 0));
		panel_1.setBounds(10, 10, 133, 46);
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Back");
		lblNewLabel.setIcon(new ImageIcon(DefaulterList.class.getResource("/AddNewBookIcons/icons8_Rewind_48px.png")));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage homepage = new HomePage();
				homepage.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBackground(SystemColor.menu);
		lblNewLabel.setBounds(10, 10, 113, 26);
		panel_1.add(lblNewLabel);

		table_returnedbooks = new JTable();
		table_returnedbooks.setSelectionForeground(new Color(0, 0, 0));
		table_returnedbooks.setOpaque(false);
		table_returnedbooks.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column" }));
		table_returnedbooks.setToolTipText("");
		table_returnedbooks.setSelectionBackground(new Color(218, 221, 216));
		table_returnedbooks.setRowHeight(30);
		table_returnedbooks.setName("Student Details");
		table_returnedbooks.setGridColor(Color.BLACK);
		table_returnedbooks.setForeground(Color.BLACK);
		table_returnedbooks.setFont(new Font("Tahoma", Font.BOLD, 20));
		table_returnedbooks.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_returnedbooks.setBackground(new Color(238, 240, 242));
		table_returnedbooks.setBounds(48, 262, 1116, 277);
		panel.add(table_returnedbooks);

		JLabel lblNewLabel_2_1 = new JLabel(
				"ID                 Book Name      \t\t\t       Student Name      \t                       \t\t        Issue Date\t          \t\t\t\t\tDue Date\t\t\t\t\t\tStatus       ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_1.setBackground(new Color(102, 205, 170));
		lblNewLabel_2_1.setBounds(48, 199, 1116, 23);
		panel.add(lblNewLabel_2_1);

		setIssueBookDetailstoTable();
	}

}
