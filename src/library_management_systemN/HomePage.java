package library_management_systemN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class HomePage extends JFrame {

	private JPanel HomePage_Main;
	private JTable table;
	private JTable table_studentdetails;
	private JTable table_bookdetails;
	
	JLabel label_books,label_students,label_issuedbooks,label_defaulterlist;
	

	// Mouse Area Color
	Color MouseEntered = new Color(218, 221, 216);
	Color MouseExited = new Color(238, 240, 242);
	
	
	private JLabel DefaulterListLabel;
	private DefaultTableModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
				model = (DefaultTableModel) table_studentdetails.getModel();
				model.addRow(object);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setBookDetailsToTable() {

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			ResultSet resultset = statement.executeQuery("SELECT * FROM book_details");

			while (resultset.next()) {

				// result set in icini yazma asamasındasın buradan devam et

				// Exporting data's from *
				String bookid = resultset.getString("book_id");
				String bookName = resultset.getString("book_name");
				String author = resultset.getString("author");
				int quantity = resultset.getInt("quantity");

				// Table display
				Object object[] = { bookid, bookName, author, quantity };
				model = (DefaultTableModel) table_bookdetails.getModel();
				model.addRow(object);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setDataToCards() {
		
		Statement statement = null;
		ResultSet resultset = null;
		
		long x = System.currentTimeMillis();
		Date todaysdate = new Date(x);
		
		DBconnection cnn = new DBconnection();
		
		try(Connection connection = DriverManager.getConnection(cnn.DB_URL,cnn.USER,cnn.PASS);
				){
			statement = connection.createStatement();
			
			resultset = statement.executeQuery("select *from book_details");
			resultset.last();
			label_books.setText(Integer.toString(resultset.getRow()));
			
			resultset = statement.executeQuery("select *from student_details");
			resultset.last();
			label_students.setText(Integer.toString(resultset.getRow()));
			

			resultset = statement.executeQuery("select *from issue_book_details");
			resultset.last();
			label_issuedbooks.setText(Integer.toString(resultset.getRow()));
			
			resultset = statement.executeQuery("select *from issue_book_details where due_date < '"+todaysdate+"' and status = '"+"pending"+"'");
			resultset.last();
			label_defaulterlist.setText(Integer.toString(resultset.getRow()));
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public HomePage() {
		
		
		

	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 900);
		HomePage_Main = new JPanel();
		HomePage_Main.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(HomePage_Main);
		HomePage_Main.setLayout(null);

		JPanel Homepage_Panel = new JPanel();
		Homepage_Panel.setBackground(SystemColor.controlHighlight);
		Homepage_Panel.setBounds(6, 6, 1388, 62);
		HomePage_Main.add(Homepage_Panel);
		Homepage_Panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("LatexSoft : Library Management System");
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 20));
		lblNewLabel.setBounds(24, 15, 392, 30);
		Homepage_Panel.add(lblNewLabel);

		JLabel lblWelcomeAdmin = new JLabel("  Welcome, Admin");
		lblWelcomeAdmin.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/trollface 50x50.png")));
		lblWelcomeAdmin.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 20));
		lblWelcomeAdmin.setBounds(1096, 8, 236, 45);
		Homepage_Panel.add(lblWelcomeAdmin);

		JLabel lblX = new JLabel(" X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 25));
		lblX.setBounds(1344, 6, 38, 45);
		Homepage_Panel.add(lblX);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 240, 242));
		panel.setBounds(6, 67, 285, 799);
		HomePage_Main.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(new Color(218, 221, 216));
		panel_1.setBounds(0, 0, 285, 64);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Home Page");
		lblNewLabel_1.setBounds(53, 20, 166, 22);
		lblNewLabel_1.setBackground(SystemColor.activeCaption);
		lblNewLabel_1.setForeground(SystemColor.windowText);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/25x25HomeIcon.png")));
		panel_1.add(lblNewLabel_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(218, 221, 216));
		panel_1_1.setBounds(0, 62, 285, 64);
		panel.add(panel_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("LMS Dashboard");
		lblNewLabel_1_1.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-library-25.png")));
		lblNewLabel_1_1.setForeground(SystemColor.windowText);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(41, 21, 182, 22);
		panel_1_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Features");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setBounds(24, 158, 108, 41);
		panel.add(lblNewLabel_1_1_1);

		JLabel ManageStudentsLabel = new JLabel("  Manage Students");
		ManageStudentsLabel.setOpaque(true);
		ManageStudentsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ManageStudentsLabel.setBackground(MouseEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ManageStudentsLabel.setBackground(MouseExited);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				ManageStudents managestudents = new ManageStudents();

				managestudents.setVisible(true);
				dispose();
			}
		});
		ManageStudentsLabel
				.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-graduate-25.png")));
		ManageStudentsLabel.setForeground(Color.BLACK);
		ManageStudentsLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		ManageStudentsLabel.setBackground(new Color(238, 240, 242));
		ManageStudentsLabel.setBounds(53, 277, 174, 41);
		panel.add(ManageStudentsLabel);

		JLabel IssueBookLabel = new JLabel("  Issue Book");
		IssueBookLabel.setOpaque(true);
		IssueBookLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				IssueBookLabel.setBackground(MouseEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				IssueBookLabel.setBackground(MouseExited);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				IssueBook issuebook = new IssueBook();
				issuebook.setVisible(true);

				dispose();
			}
		});
		IssueBookLabel.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/25x25 book.png")));
		IssueBookLabel.setForeground(Color.BLACK);
		IssueBookLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		IssueBookLabel.setBackground(new Color(238, 240, 242));
		IssueBookLabel.setBounds(53, 323, 174, 41);
		panel.add(IssueBookLabel);

		JLabel ReturnBookLabel = new JLabel("  Return Book");
		ReturnBookLabel.setOpaque(true);
		ReturnBookLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ReturnBookLabel.setBackground(MouseEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ReturnBookLabel.setBackground(MouseExited);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				ReturnBook returnbook = new ReturnBook();

				returnbook.setVisible(true);
				dispose();

			}
		});
		ReturnBookLabel
				.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/25x25 return book.png")));
		ReturnBookLabel.setForeground(Color.BLACK);
		ReturnBookLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		ReturnBookLabel.setBackground(new Color(238, 240, 242));
		ReturnBookLabel.setBounds(53, 374, 174, 43);
		panel.add(ReturnBookLabel);

		JLabel ViewRecordsLabel = new JLabel("  View Records");
		ViewRecordsLabel.setOpaque(true);
		ViewRecordsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ViewRecordsLabel.setBackground(MouseEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ViewRecordsLabel.setBackground(MouseExited);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewAllRecords frame = new ViewAllRecords();
				frame.setVisible(true);
				dispose();
			}
		});
		ViewRecordsLabel.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-records-25.png")));
		ViewRecordsLabel.setForeground(Color.BLACK);
		ViewRecordsLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		ViewRecordsLabel.setBackground(new Color(238, 240, 242));
		ViewRecordsLabel.setBounds(53, 427, 174, 43);
		panel.add(ViewRecordsLabel);

		JLabel VIBooksLabel = new JLabel("  View Issued Books");
		VIBooksLabel.setOpaque(true);
		VIBooksLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				VIBooksLabel.setBackground(MouseEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				VIBooksLabel.setBackground(MouseExited);

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				IssueBookDetails frame = new IssueBookDetails();
				frame.setVisible(true);
				dispose();
			}
		});
		VIBooksLabel.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-books-25.png")));
		VIBooksLabel.setForeground(Color.BLACK);
		VIBooksLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		VIBooksLabel.setBackground(new Color(238, 240, 242));
		VIBooksLabel.setBounds(53, 480, 174, 40);
		panel.add(VIBooksLabel);

		DefaulterListLabel = new JLabel("  Defaulter List");
		DefaulterListLabel.setOpaque(true);
		DefaulterListLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				DefaulterListLabel.setBackground(MouseEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				DefaulterListLabel.setBackground(MouseExited);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaulterList frame = new DefaulterList();
				frame.setVisible(true);
				dispose();
			}
		});
		DefaulterListLabel.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-hire-me-25.png")));
		DefaulterListLabel.setForeground(Color.BLACK);
		DefaulterListLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		DefaulterListLabel.setBackground(new Color(238, 240, 242));
		DefaulterListLabel.setBounds(53, 532, 174, 41);
		panel.add(DefaulterListLabel);

		JLabel ManageBooksPanel = new JLabel("  Manage Books");
		ManageBooksPanel.setOpaque(true);
		ManageBooksPanel.setBounds(53, 226, 174, 41);
		panel.add(ManageBooksPanel);
		ManageBooksPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageBooks managebooks = new ManageBooks();
				managebooks.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ManageBooksPanel.setBackground(MouseEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ManageBooksPanel.setBackground(MouseExited);
			}
		});
		ManageBooksPanel.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-book-25.png")));
		ManageBooksPanel.setForeground(Color.BLACK);
		ManageBooksPanel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		ManageBooksPanel.setBackground(new Color(238, 240, 242));
		
				JLabel LogoutLabel = new JLabel("Logout");
				LogoutLabel.setBounds(0, 614, 285, 64);
				panel.add(LogoutLabel);
				LogoutLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						LoginPage loginpage = new LoginPage();
						loginpage.setVisible(true);
						dispose();

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						LogoutLabel.setBackground(MouseEntered);

					}

					@Override
					public void mouseExited(MouseEvent e) {
						LogoutLabel.setBackground(MouseExited);
					}
				});
				LogoutLabel.setOpaque(true);
				LogoutLabel.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-logout-50.png")));
				LogoutLabel.setForeground(SystemColor.windowText);
				LogoutLabel.setFont(new Font("Dialog", Font.BOLD, 20));
				LogoutLabel.setBackground(new Color(218, 221, 216));

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(0, 255, 255));
		panel_2.setBackground(new Color(250, 250, 255));
		panel_2.setBounds(292, 67, 1102, 799);
		HomePage_Main.add(panel_2);
		panel_2.setLayout(null);

		// No of students panel
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setForeground(new Color(0, 128, 128));
		panel_2_1.setBounds(44, 65, 210, 121);
		panel_2_1.setBorder(new MatteBorder(15, 1, 1, 1, (Color) new Color(128, 0, 0)));
		panel_2.add(panel_2_1);
		panel_2_1.setLayout(null);

		 label_books = new JLabel("10");
		label_books.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-books-50.png")));
		label_books.setBounds(50, 38, 115, 57);
		label_books.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 25));
		panel_2_1.add(label_books);

		JLabel lblNewLabel_2 = new JLabel("Books:");
		lblNewLabel_2.setBounds(42, 37, 133, 16);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_2.add(lblNewLabel_2);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBounds(309, 65, 210, 121);
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBorder(new MatteBorder(15, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.add(panel_2_1_1);

		 label_students = new JLabel("10");
		label_students.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-graduate-50.png")));
		label_students.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 25));
		label_students.setBounds(50, 38, 115, 57);
		panel_2_1_1.add(label_students);

		JLabel lblNewLabel_2_2 = new JLabel("Students:");
		lblNewLabel_2_2.setBounds(307, 37, 133, 16);
		lblNewLabel_2_2.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_2.add(lblNewLabel_2_2);

		JPanel panel_2_1_2 = new JPanel();
		panel_2_1_2.setBounds(590, 65, 210, 121);
		panel_2_1_2.setLayout(null);
		panel_2_1_2.setBorder(new MatteBorder(15, 1, 1, 1, (Color) new Color(128, 0, 0)));
		panel_2.add(panel_2_1_2);

		 label_issuedbooks = new JLabel("10");
		label_issuedbooks.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-books-50.png")));
		label_issuedbooks.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 25));
		label_issuedbooks.setBounds(50, 38, 115, 57);
		panel_2_1_2.add(label_issuedbooks);

		JLabel lblNewLabel_2_3 = new JLabel("Issued Books:");
		lblNewLabel_2_3.setBounds(588, 37, 152, 16);
		lblNewLabel_2_3.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_2.add(lblNewLabel_2_3);

		JPanel panel_2_1_3 = new JPanel();
		panel_2_1_3.setBounds(863, 65, 210, 121);
		panel_2_1_3.setLayout(null);
		panel_2_1_3.setBorder(new MatteBorder(15, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.add(panel_2_1_3);

		 label_defaulterlist = new JLabel("10");
		label_defaulterlist
				.setIcon(new ImageIcon(HomePage.class.getResource("/MyNewIcoıns/icons8-hire-me-50.png")));
		label_defaulterlist.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 25));
		label_defaulterlist.setBounds(50, 38, 115, 57);
		panel_2_1_3.add(label_defaulterlist);

		JLabel lblNewLabel_2_4 = new JLabel("Defaulter List:");
		lblNewLabel_2_4.setBounds(861, 37, 133, 16);
		lblNewLabel_2_4.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_2.add(lblNewLabel_2_4);

		table_studentdetails = new JTable();
		table_studentdetails.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		table_studentdetails.setFillsViewportHeight(true);
		table_studentdetails.setFont(new Font("Dialog", Font.BOLD, 14));
		table_studentdetails.setBackground(new Color(250, 250, 255));
		table_studentdetails.setToolTipText("");
		table_studentdetails.setName("Student Details");
		table_studentdetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student ID", "Name", "Course", "Branch"
			}
		));
		table_studentdetails.setBounds(44, 264, 1029, 181);
		panel_2.add(table_studentdetails);

		JLabel lblNewLabel_3 = new JLabel("Student Details");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(44, 209, 200, 37);
		panel_2.add(lblNewLabel_3);

		table_bookdetails = new JTable();
		table_bookdetails.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		table_bookdetails.setFont(new Font("Dialog", Font.BOLD, 14));
		table_bookdetails.setBackground(new Color(250, 250, 255));
		table_bookdetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table_bookdetails.setToolTipText("");
		table_bookdetails.setName("Student Details");
		table_bookdetails.setBounds(44, 530, 1029, 181);
		panel_2.add(table_bookdetails);

		JLabel lblNewLabel_3_1 = new JLabel("Book Details");
		lblNewLabel_3_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_3_1.setBackground(Color.WHITE);
		lblNewLabel_3_1.setBounds(44, 472, 200, 37);
		panel_2.add(lblNewLabel_3_1);

		setStudentDetailsToTable();
		setBookDetailsToTable();
		 setDataToCards();
		// showPieChart();
		 

	}
}
