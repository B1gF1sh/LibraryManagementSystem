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

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtfield_bookid;
	private JTextField txtfield_studentid;

	// private UI label accessors
	private JLabel label_Issueid, label_bookname, label_studentname, label_Issuedate, label_duedate, label_bookerror;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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

	public void updateBookCount() {

		int bookid = Integer.parseInt(txtfield_bookid.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS)) {

			String sql = "update book_details set quantity = quantity + 1 where book_ıd = ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setInt(1, bookid);

			int rowCount = preparedstatement.executeUpdate();

			if (rowCount > 0) {
				JOptionPane.showMessageDialog(contentPane, "Book count updated...");
			} else {
				JOptionPane.showMessageDialog(contentPane, "Book count can not updated!!!");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void getIssueBookDetails() {

		int bookid = Integer.parseInt(txtfield_bookid.getText());
		int studentid = Integer.parseInt(txtfield_studentid.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS)) {

			String sql = "select *from issue_book_details where book_id =? and student_id =? and status =?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setInt(1, bookid);
			preparedstatement.setInt(2, studentid);
			preparedstatement.setString(3, "Pending");

			ResultSet resultset = preparedstatement.executeQuery();

			if (resultset.next()) {

				// issued book olmadığı icin bu bloktaki son 2 satır null değer dondurecek ve
				// calısmayacak
				label_Issueid.setText(resultset.getString("id")); // bu siktiğimin satırıda null

				label_bookname.setText(resultset.getString("book_id"));
				label_studentname.setText(resultset.getString("student_name"));
				label_Issuedate.setText(resultset.getString("issue_date"));
				label_duedate.setText(resultset.getString("due_date"));

			} else {
				label_bookerror.setText("No Record Found!!!");

				label_Issueid.setText("");
				label_bookname.setText("");
				label_studentname.setText("");
				label_Issuedate.setText("");
				label_duedate.setText("");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean returnBook() {

		boolean isReturned = false;

		int bookid = Integer.parseInt(txtfield_bookid.getText());
		int studentid = Integer.parseInt(txtfield_studentid.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);) {

			String sql = "update issue_book_details set status = ? where student_id = ? and book_id = ? and status = ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setString(1, "returned");
			preparedstatement.setInt(2, studentid);
			preparedstatement.setInt(3, bookid);
			preparedstatement.setString(4, "pending");

			int rowCount = preparedstatement.executeUpdate();

			if (rowCount > 0) {

				isReturned = true;

			} else {

				isReturned = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isReturned;
	}

	public ReturnBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1384, 814);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 240, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(228, 242, 227));
		panel.setBounds(0, 0, 494, 798);
		panel.setBackground(new Color(159, 175, 144));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(63, 269, 333, 5);
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setOpaque(true);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Issue ID:");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setBounds(63, 354, 133, 23);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Book Name:");
		lblNewLabel_3_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_1.setBounds(63, 426, 133, 23);
		lblNewLabel_3_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("Student Name:");
		lblNewLabel_3_2.setForeground(Color.BLACK);
		lblNewLabel_3_2.setBounds(63, 504, 133, 23);
		lblNewLabel_3_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_3 = new JLabel("Issue Date:");
		lblNewLabel_3_3.setForeground(Color.BLACK);
		lblNewLabel_3_3.setBounds(63, 580, 133, 23);
		lblNewLabel_3_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.add(lblNewLabel_3_3);

		label_Issueid = new JLabel("");
		label_Issueid.setBackground(new Color(255, 255, 255));
		label_Issueid.setForeground(new Color(0, 0, 0));
		label_Issueid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_Issueid.setBounds(214, 354, 161, 23);
		panel.add(label_Issueid);

		label_bookname = new JLabel("");
		label_bookname.setForeground(new Color(0, 0, 0));
		label_bookname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_bookname.setBounds(214, 426, 161, 23);
		panel.add(label_bookname);

		label_studentname = new JLabel("");
		label_studentname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_studentname.setBounds(214, 504, 161, 23);
		panel.add(label_studentname);

		label_Issuedate = new JLabel("");
		label_Issuedate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_Issuedate.setBounds(214, 580, 161, 23);
		panel.add(label_Issuedate);

		JLabel lblNewLabel_3_3_1 = new JLabel("Due Date:");
		lblNewLabel_3_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_3_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_3_3_1.setBounds(63, 662, 133, 23);
		panel.add(lblNewLabel_3_3_1);

		label_duedate = new JLabel("");
		label_duedate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_duedate.setBounds(206, 662, 161, 23);
		panel.add(label_duedate);

		label_bookerror = new JLabel("");
		label_bookerror.setForeground(Color.BLACK);
		label_bookerror.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_bookerror.setBounds(49, 689, 331, 34);
		panel.add(label_bookerror);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Issue Details");
		lblNewLabel_1_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1_1_1_1_1.setBounds(154, 190, 160, 69);
		panel.add(lblNewLabel_1_1_1_1_1);
		
				JLabel lblNewLabel = new JLabel("Back");
				lblNewLabel.setBounds(10, 10, 161, 48);
				panel.add(lblNewLabel);
				lblNewLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						HomePage homepage = new HomePage();
						homepage.setVisible(true);
						dispose();
					}
				});
				lblNewLabel.setIcon(new ImageIcon(ReturnBook.class.getResource("/MyNewIcoıns/icons8-back-64.png")));
				lblNewLabel.setBackground(new Color(240, 240, 240));
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(214, 221, 208));
		panel_2.setBounds(491, 0, 560, 777);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setBounds(145, 269, 333, 5);
		panel_2.add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setOpaque(true);
		lblNewLabel_2_1_1.setBackground(Color.BLACK);

		txtfield_bookid = new JTextField();
		txtfield_bookid.setBounds(200, 326, 297, 33);
		panel_2.add(txtfield_bookid);
		txtfield_bookid.setOpaque(false);
		txtfield_bookid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}
		});
		txtfield_bookid.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtfield_bookid.setDisabledTextColor(Color.LIGHT_GRAY);
		txtfield_bookid.setColumns(10);
		txtfield_bookid.setCaretColor(Color.BLACK);
		txtfield_bookid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtfield_bookid.setBackground(new Color(0, 139, 139));

		JLabel SignupPage_Icon_username = new JLabel("Book ID:");
		SignupPage_Icon_username.setBounds(75, 335, 96, 36);
		panel_2.add(SignupPage_Icon_username);
		SignupPage_Icon_username.setFont(new Font("Dialog", Font.PLAIN, 18));

		txtfield_studentid = new JTextField();
		txtfield_studentid.setBounds(201, 437, 296, 33);
		panel_2.add(txtfield_studentid);
		txtfield_studentid.setOpaque(false);
		txtfield_studentid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}
		});
		txtfield_studentid.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtfield_studentid.setDisabledTextColor(Color.LIGHT_GRAY);
		txtfield_studentid.setColumns(10);
		txtfield_studentid.setCaretColor(Color.BLACK);
		txtfield_studentid.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtfield_studentid.setBackground(new Color(0, 139, 139));

		JLabel SignupPage_Icon_username_1 = new JLabel("Student ID:");
		SignupPage_Icon_username_1.setBounds(75, 432, 115, 36);
		panel_2.add(SignupPage_Icon_username_1);
		SignupPage_Icon_username_1.setFont(new Font("Dialog", Font.PLAIN, 18));

		JButton btnIssuseBook = new JButton("Find ");
		btnIssuseBook.setBounds(164, 554, 269, 45);
		panel_2.add(btnIssuseBook);
		btnIssuseBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getIssueBookDetails();

			}

		});
		btnIssuseBook.setFont(new Font("Dialog", Font.BOLD, 20));

		JButton btnIssuseBook_1 = new JButton("Return Book");
		btnIssuseBook_1.setBounds(164, 630, 269, 45);
		panel_2.add(btnIssuseBook_1);
		btnIssuseBook_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (returnBook() == true) {
					JOptionPane.showMessageDialog(btnIssuseBook_1, "Book Returned...");
					updateBookCount();
				} else {
					JOptionPane.showMessageDialog(btnIssuseBook_1, "Book Return Failure");
				}
			}
		});
		btnIssuseBook_1.setFont(new Font("Dialog", Font.BOLD, 20));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Search Book");
		lblNewLabel_1_1_1_1.setBounds(235, 189, 160, 69);
		panel_2.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 24));

		JLabel lblNewLabel_1_1_1 = new JLabel("     Return Book");
		lblNewLabel_1_1_1.setBounds(75, 10, 378, 102);
		panel_2.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1
				.setIcon(new ImageIcon(ReturnBook.class.getResource("/MyNewIcoıns/icons8-books-100.png")));
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 24));

		JLabel lblNewLabel_1_1_1_2 = new JLabel("    X");
		lblNewLabel_1_1_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1_1_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_1_1_2.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_1_1_1_2.setBounds(1295, 10, 65, 58);
		contentPane.add(lblNewLabel_1_1_1_2);
	}
}
