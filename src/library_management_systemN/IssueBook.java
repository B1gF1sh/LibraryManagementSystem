package library_management_systemN;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import java.sql.Connection;

import rojeru_san.componentes.RSDateChooser;
import javax.swing.JButton;
import app.bolivia.swing.JCTextField;
import java.sql.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IssueBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtfield_bookid;
	private JTextField txtfield_studentid;

	// private UI label accessors
	private JLabel label_bookid, label_bookname, label_author, label_quantity, label_studentid, label_studentname,
			label_course, label_branch, label_bookerror, label_studenterror;

	private RSDateChooser data_ıssuedate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	// importing values from database (book_details)
	public void getBookDetails() {

		int bookid = Integer.parseInt(txtfield_bookid.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);) {

			PreparedStatement preparedstatement = connection
					.prepareStatement("select * from book_details where book_ıd = ?");
			preparedstatement.setInt(1, bookid);

			ResultSet resultset = preparedstatement.executeQuery();

			if (resultset.next()) {

				label_bookid.setText(resultset.getString("book_ıd"));
				label_bookname.setText(resultset.getString("book_name"));
				label_author.setText(resultset.getString("author"));
				label_quantity.setText(resultset.getString("quantity"));
				label_bookerror.setText("");

			} else {

				label_bookerror.setText("Invalid value!!!");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getStudentDetails() {

		int studentid = Integer.parseInt(txtfield_studentid.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);) {

			PreparedStatement preparedstatement = connection
					.prepareStatement("select * from student_details where student_id = ?");
			preparedstatement.setInt(1, studentid);

			ResultSet resultset = preparedstatement.executeQuery();

			if (resultset.next()) {

				label_studentid.setText(resultset.getString("student_id"));
				label_studentname.setText(resultset.getString("name"));
				label_course.setText(resultset.getString("course"));
				label_branch.setText(resultset.getString("branch"));
				label_studenterror.setText("");

			} else {

				label_studenterror.setText("Invalid value!!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean issueBook() {

		boolean isIssued = false;

		int bookid = Integer.parseInt(txtfield_bookid.getText());
		int studentid = Integer.parseInt(txtfield_studentid.getText());
		String bookname = label_bookname.getText();
		String studentname = label_studentname.getText();

		Date User_IssueDate = data_ıssuedate.getDatoFecha();
		Date User_DueDate = data_ıssuedate.getDatoFecha();

		long x = User_IssueDate.getTime();
		long y = User_DueDate.getTime();

		java.sql.Date System_IssueDate = new java.sql.Date(x);
		java.sql.Date System_DueDate = new java.sql.Date(y);

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS)) {

			String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) values(?,?,?,?,?,?,?)";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			
			preparedstatement.setInt(1, bookid);
			preparedstatement.setString(2, bookname);
			preparedstatement.setInt(3, studentid);
			preparedstatement.setString(4, studentname);
			preparedstatement.setDate(5, System_IssueDate);
			preparedstatement.setDate(6, System_DueDate);
			preparedstatement.setString(7, "Pending");

			int rowCount = preparedstatement.executeUpdate();

			if (rowCount > 0) {
				isIssued = true;
			} else {
				isIssued = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isIssued;
	}

	public void updateBookCount() {

		int bookid = Integer.parseInt(txtfield_bookid.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS)) {

			String sql = "update book_details set quantity = quantity -1 where book_ıd = ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setInt(1, bookid);

			int rowCount = preparedstatement.executeUpdate();

			if (rowCount > 0) {
				JOptionPane.showMessageDialog(contentPane, "Book count updated...");
				int bookcount = Integer.parseInt(label_quantity.getText());
				label_quantity.setText(Integer.toString(bookcount - 1));
			} else {
				JOptionPane.showMessageDialog(contentPane, "Book count can not updated!!!");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public boolean isAlreadyIssued() {

		boolean isAlreadyIssued = false;

		int bookid = Integer.parseInt(txtfield_bookid.getText());
		int studentid = Integer.parseInt(txtfield_studentid.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS)) {

			String sql = "select *from issue_book_details where book_id = ? and student_id = ? and status = ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setInt(1, bookid);
			preparedstatement.setInt(2, studentid);
			preparedstatement.setString(3, "Pending");

			ResultSet resultset = preparedstatement.executeQuery();

			if (resultset.next()) {
				isAlreadyIssued = true;
			} else {
				isAlreadyIssued = false;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return isAlreadyIssued;
	}

	public IssueBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 814);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(214, 221, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 395, 777);
		panel.setBackground(new Color(255, 246, 163));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Book Details");
		lblNewLabel_1.setBounds(38, 86, 331, 122);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1
				.setIcon(new ImageIcon(IssueBook.class.getResource("/MyNewIcoıns/icons8-books-100.png")));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 27));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(36, 224, 333, 5);
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setOpaque(true);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Book ID:");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setBounds(49, 303, 133, 23);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Book Name:");
		lblNewLabel_3_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_1.setBounds(49, 375, 133, 23);
		lblNewLabel_3_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("Author:");
		lblNewLabel_3_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_2.setBounds(49, 453, 133, 23);
		lblNewLabel_3_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("Quantity:");
		lblNewLabel_3_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_3.setBounds(49, 529, 133, 23);
		lblNewLabel_3_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(lblNewLabel_3_3);

		label_bookid = new JLabel("");
		label_bookid.setBackground(new Color(255, 255, 255));
		label_bookid.setForeground(new Color(0, 0, 0));
		label_bookid.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_bookid.setBounds(200, 303, 161, 23);
		panel.add(label_bookid);

		label_bookname = new JLabel("");
		label_bookname.setForeground(new Color(0, 0, 0));
		label_bookname.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_bookname.setBounds(200, 375, 161, 23);
		panel.add(label_bookname);

		label_author = new JLabel("");
		label_author.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_author.setBounds(200, 453, 161, 23);
		panel.add(label_author);

		label_quantity = new JLabel("");
		label_quantity.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_quantity.setBounds(200, 529, 161, 23);
		panel.add(label_quantity);

		label_bookerror = new JLabel("");
		label_bookerror.setForeground(Color.WHITE);
		label_bookerror.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_bookerror.setBounds(49, 604, 312, 23);
		panel.add(label_bookerror);
		
				JLabel lblNewLabel = new JLabel("Back");
				lblNewLabel.setIconTextGap(2);
				lblNewLabel.setBounds(10, 10, 124, 42);
				panel.add(lblNewLabel);
				lblNewLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						HomePage homepage = new HomePage();
						homepage.setVisible(true);
						dispose();
					}
				});
				lblNewLabel.setIcon(new ImageIcon(IssueBook.class.getResource("/MyNewIcoıns/icons8-back-64.png")));
				lblNewLabel.setBackground(new Color(240, 240, 240));
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(394, 0, 395, 777);
		panel_2.setBackground(new Color(159, 175, 144));
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Student Details");
		lblNewLabel_1_1.setIcon(
				new ImageIcon(IssueBook.class.getResource("/MyNewIcoıns/icons8-graduate-100.png")));
		lblNewLabel_1_1.setBounds(31, 91, 364, 122);
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 27));
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBounds(41, 223, 333, 5);
		lblNewLabel_2_1.setOpaque(true);
		lblNewLabel_2_1.setBackground(Color.BLACK);
		panel_2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_3_4 = new JLabel("Student ID:");
		lblNewLabel_3_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_3_4.setBounds(49, 303, 133, 23);
		lblNewLabel_3_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_2.add(lblNewLabel_3_4);

		JLabel lblNewLabel_3_1_1 = new JLabel("Student Name:");
		lblNewLabel_3_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_1_1.setBounds(49, 375, 133, 23);
		lblNewLabel_3_1_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_2.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_2_1 = new JLabel("Course:");
		lblNewLabel_3_2_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_2_1.setBounds(49, 453, 133, 23);
		lblNewLabel_3_2_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_2.add(lblNewLabel_3_2_1);

		JLabel lblNewLabel_3_3_1 = new JLabel("Branch:");
		lblNewLabel_3_3_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_3_1.setBounds(49, 529, 133, 23);
		lblNewLabel_3_3_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_2.add(lblNewLabel_3_3_1);

		label_studentid = new JLabel("");
		label_studentid.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_studentid.setForeground(new Color(0, 0, 0));
		label_studentid.setBounds(224, 303, 161, 23);
		panel_2.add(label_studentid);

		label_studentname = new JLabel("");
		label_studentname.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_studentname.setForeground(new Color(0, 0, 0));
		label_studentname.setBounds(221, 375, 161, 23);
		panel_2.add(label_studentname);

		label_course = new JLabel("");
		label_course.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_course.setBounds(221, 453, 161, 23);
		panel_2.add(label_course);

		label_branch = new JLabel("");
		label_branch.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_branch.setBounds(221, 529, 161, 23);
		panel_2.add(label_branch);

		label_studenterror = new JLabel("");
		label_studenterror.setForeground(Color.WHITE);
		label_studenterror.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_studenterror.setBounds(28, 606, 312, 23);
		panel_2.add(label_studenterror);

		JLabel lblNewLabel_1_1_1 = new JLabel("     Issue Book");
		lblNewLabel_1_1_1.setBounds(933, 95, 326, 95);
		lblNewLabel_1_1_1
				.setIcon(new ImageIcon(IssueBook.class.getResource("/MyNewIcoıns/icons8-books-100.png")));
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 27));
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setBounds(926, 226, 333, 5);
		lblNewLabel_2_1_1.setOpaque(true);
		lblNewLabel_2_1_1.setBackground(Color.BLACK);
		contentPane.add(lblNewLabel_2_1_1);

		txtfield_bookid = new JTextField();
		txtfield_bookid.setOpaque(false);
		txtfield_bookid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!txtfield_bookid.getText().equals("")) {
					getBookDetails();
				}

			}
		});
		txtfield_bookid.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtfield_bookid.setDisabledTextColor(Color.LIGHT_GRAY);
		txtfield_bookid.setColumns(10);
		txtfield_bookid.setCaretColor(Color.BLACK);
		txtfield_bookid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtfield_bookid.setBackground(new Color(0, 139, 139));
		txtfield_bookid.setBounds(962, 275, 297, 33);
		contentPane.add(txtfield_bookid);

		JLabel SignupPage_Icon_username = new JLabel("Book ID:");
		SignupPage_Icon_username.setFont(new Font("Dialog", Font.PLAIN, 18));
		SignupPage_Icon_username.setBounds(820, 271, 96, 36);
		contentPane.add(SignupPage_Icon_username);

		txtfield_studentid = new JTextField();
		txtfield_studentid.setOpaque(false);
		txtfield_studentid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if (!txtfield_studentid.getText().equals("")) {

					getStudentDetails();
				}

			}
		});
		txtfield_studentid.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtfield_studentid.setDisabledTextColor(Color.LIGHT_GRAY);
		txtfield_studentid.setColumns(10);
		txtfield_studentid.setCaretColor(Color.BLACK);
		txtfield_studentid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtfield_studentid.setBackground(new Color(0, 139, 139));
		txtfield_studentid.setBounds(963, 381, 296, 33);
		contentPane.add(txtfield_studentid);

		JLabel SignupPage_Icon_username_1 = new JLabel("Student ID:");
		SignupPage_Icon_username_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		SignupPage_Icon_username_1.setBounds(820, 377, 115, 36);
		contentPane.add(SignupPage_Icon_username_1);

		data_ıssuedate = new RSDateChooser();
		data_ıssuedate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				getBookDetails();

			}
		});
		data_ıssuedate.setPlaceholder("Select Issue Date");
		data_ıssuedate.setBounds(963, 472, 296, 45);
		contentPane.add(data_ıssuedate);

		JLabel SignupPage_Icon_username_1_1 = new JLabel("Issue Date");
		SignupPage_Icon_username_1_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		SignupPage_Icon_username_1_1.setBounds(820, 472, 132, 36);
		contentPane.add(SignupPage_Icon_username_1_1);

		RSDateChooser date_duedate = new RSDateChooser();
		date_duedate.setPlaceholder("Select Due Date");
		date_duedate.setBounds(962, 554, 297, 45);
		contentPane.add(date_duedate);

		JLabel SignupPage_Icon_username_1_1_1 = new JLabel("Due Date");
		SignupPage_Icon_username_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		SignupPage_Icon_username_1_1_1.setBounds(820, 554, 132, 36);
		contentPane.add(SignupPage_Icon_username_1_1_1);

		JButton btnIssuseBook = new JButton("Issue Book");
		btnIssuseBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (label_quantity.getText().equals("0")) {
					JOptionPane.showMessageDialog(btnIssuseBook, "book is not avaible");
				} else {
					if (isAlreadyIssued() == false) {

						if (issueBook() == true) {
							JOptionPane.showMessageDialog(btnIssuseBook, "Book Issued...");
							updateBookCount();
						} else {
							JOptionPane.showMessageDialog(btnIssuseBook, "Can not Issue the book!!!");
						}

					} else {
						JOptionPane.showMessageDialog(btnIssuseBook, "this student already has this book");

					}
				}

			}

		});
		btnIssuseBook.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnIssuseBook.setBounds(962, 663, 269, 45);
		contentPane.add(btnIssuseBook);
	}
}
