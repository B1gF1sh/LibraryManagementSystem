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
import javax.swing.border.LineBorder;

public class ManageBooks extends JFrame {

	private JPanel contentPane;
	private JTextField textField_bookıd;
	private JTextField textField_bookname;
	private JTextField textField_authorname;
	private JTextField textField_quantity;
	private JTable table_bookdetails;

	// Chart variables
	public String book_name, author;
	public int quantity, book_id;
	public DefaultTableModel model;

	/**
	 * Launch the application from here XD
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBooks frame = new ManageBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	public void cleartable() {

		DefaultTableModel model = (DefaultTableModel) table_bookdetails.getModel();

		model.setRowCount(0);

	}

	// add book button is not working
	public boolean addbook() {

		boolean Isadded = false;

		book_id = Integer.parseInt(textField_bookıd.getText());
		book_name = textField_bookname.getText();
		author = textField_authorname.getText();
		quantity = Integer.parseInt(textField_quantity.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);) {

			String sql = "insert into book_details(book_ıd,book_name,author,quantity) values(?,?,?,?)";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setInt(1, book_id);
			preparedstatement.setString(2, book_name);
			preparedstatement.setString(3, author);
			preparedstatement.setInt(4, quantity);

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

	public boolean updatebook() {

		boolean Isupdate = false;

		book_id = Integer.parseInt(textField_bookıd.getText());
		book_name = textField_bookname.getText();
		author = textField_authorname.getText();
		quantity = Integer.parseInt(textField_quantity.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			String sql = "update book_details set book_name = ?,author = ?,quantity = ? where book_ıd = ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);

			preparedstatement.setString(1, book_name);
			preparedstatement.setString(2, author);
			preparedstatement.setInt(3, quantity);
			preparedstatement.setInt(4, book_id);

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

	public boolean deletebook() {

		boolean Isdeleted = false;

		book_id = Integer.parseInt(textField_bookıd.getText());

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);) {

			String sql = "delete from book_details where book_ıd = ?";

			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, book_id);

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

	public ManageBooks() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 814);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.setBackground(new Color(255, 246, 163));
		panel.setBounds(10, 10, 478, 757);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel SignupPage_label_username = new JLabel("Enter Book ID");
		SignupPage_label_username.setFont(new Font("Dialog", Font.BOLD, 17));
		SignupPage_label_username.setBounds(121, 118, 161, 28);
		panel.add(SignupPage_label_username);

		textField_bookıd = new JTextField();
		textField_bookıd.setSelectionColor(new Color(218, 221, 216));
		textField_bookıd.setOpaque(false);
		textField_bookıd.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_bookıd.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField_bookıd.setDisabledTextColor(new Color(255, 246, 163));
		textField_bookıd.setColumns(10);
		textField_bookıd.setCaretColor(Color.BLACK);
		textField_bookıd.setBackground(new Color(238, 240, 242));
		textField_bookıd.setBounds(120, 145, 297, 33);
		panel.add(textField_bookıd);

		JLabel SignupPage_Icon_username = new JLabel("");
		SignupPage_Icon_username
				.setIcon(new ImageIcon(ManageBooks.class.getResource("/MyNewIcoıns/icons8-records-25.png")));
		SignupPage_Icon_username.setBounds(59, 134, 50, 44);
		panel.add(SignupPage_Icon_username);

		JLabel SignupPage_label_username_1 = new JLabel("Enter Book Name");
		SignupPage_label_username_1.setFont(new Font("Dialog", Font.BOLD, 17));
		SignupPage_label_username_1.setBounds(121, 240, 161, 28);
		panel.add(SignupPage_label_username_1);

		textField_bookname = new JTextField();
		textField_bookname.setSelectionColor(new Color(218, 221, 216));
		textField_bookname.setOpaque(false);
		textField_bookname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_bookname.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField_bookname.setDisabledTextColor(Color.BLACK);
		textField_bookname.setColumns(10);
		textField_bookname.setCaretColor(Color.BLACK);
		textField_bookname.setBackground(new Color(238, 240, 242));
		textField_bookname.setBounds(120, 267, 297, 33);
		panel.add(textField_bookname);

		JLabel SignupPage_Icon_username_1 = new JLabel("");
		SignupPage_Icon_username_1
				.setIcon(new ImageIcon(ManageBooks.class.getResource("/MyNewIcoıns/icons8-book-25.png")));
		SignupPage_Icon_username_1.setBounds(59, 256, 50, 44);
		panel.add(SignupPage_Icon_username_1);

		JLabel SignupPage_label_username_2 = new JLabel("Author Name");
		SignupPage_label_username_2.setFont(new Font("Dialog", Font.BOLD, 17));
		SignupPage_label_username_2.setBounds(121, 365, 161, 28);
		panel.add(SignupPage_label_username_2);

		textField_authorname = new JTextField();
		textField_authorname.setSelectionColor(new Color(218, 221, 216));
		textField_authorname.setOpaque(false);
		textField_authorname.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_authorname.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField_authorname.setDisabledTextColor(Color.BLACK);
		textField_authorname.setColumns(10);
		textField_authorname.setCaretColor(Color.BLACK);
		textField_authorname.setBackground(new Color(0, 128, 128));
		textField_authorname.setBounds(120, 392, 297, 33);
		panel.add(textField_authorname);

		JLabel SignupPage_Icon_username_2 = new JLabel("");
		SignupPage_Icon_username_2.setIcon(
				new ImageIcon(ManageBooks.class.getResource("/MyNewIcoıns/icons8-graduate-25.png")));
		SignupPage_Icon_username_2.setBounds(59, 381, 50, 44);
		panel.add(SignupPage_Icon_username_2);

		JLabel SignupPage_label_username_3 = new JLabel("Quantity");
		SignupPage_label_username_3.setFont(new Font("Dialog", Font.BOLD, 17));
		SignupPage_label_username_3.setBounds(121, 489, 161, 28);
		panel.add(SignupPage_label_username_3);

		textField_quantity = new JTextField();
		textField_quantity.setSelectionColor(new Color(218, 221, 216));
		textField_quantity.setOpaque(false);
		textField_quantity.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_quantity.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField_quantity.setDisabledTextColor(Color.BLACK);
		textField_quantity.setColumns(10);
		textField_quantity.setCaretColor(Color.BLACK);
		textField_quantity.setBackground(new Color(0, 128, 128));
		textField_quantity.setBounds(120, 516, 297, 33);
		panel.add(textField_quantity);

		JLabel SignupPage_Icon_username_3 = new JLabel("");
		SignupPage_Icon_username_3
				.setIcon(new ImageIcon(ManageBooks.class.getResource("/MyNewIcoıns/icons8-books-25.png")));
		SignupPage_Icon_username_3.setBounds(59, 505, 50, 44);
		panel.add(SignupPage_Icon_username_3);

		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(250, 250, 255));
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (addbook() == true) {
					JOptionPane.showMessageDialog(btnNewButton, "Book is added.");
					cleartable();
					setBookDetailsToTable();
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Book addition is failed...");
				}
			}
		});
		btnNewButton.setBounds(49, 619, 94, 33);
		panel.add(btnNewButton);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(new Color(250, 250, 255));
		btnDelete.setBorderPainted(false);
		btnDelete.setBorder(null);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (deletebook() == true) {
					JOptionPane.showMessageDialog(btnNewButton, "Book is deleted.");
					cleartable();
					setBookDetailsToTable();
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Book deleting is failed...");
				}

			}
		});
		btnDelete.setBounds(188, 619, 94, 33);
		panel.add(btnDelete);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(new Color(250, 250, 255));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBorder(null);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (updatebook() == true) {
					JOptionPane.showMessageDialog(btnNewButton, "Book is updated.");
					cleartable();
					setBookDetailsToTable();
				
				} else {
					JOptionPane.showMessageDialog(btnNewButton, "Book updating is failed...");
				}
			}
		});
		btnUpdate.setBounds(323, 619, 94, 33);
		panel.add(btnUpdate);
		
				JLabel lblNewLabel = new JLabel("Back");
				lblNewLabel.setBounds(10, 10, 133, 44);
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
				lblNewLabel.setIcon(new ImageIcon(ManageBooks.class.getResource("/MyNewIcoıns/icons8-back-64.png")));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(238, 240, 242));
		panel_2.setBounds(485, 10, 877, 757);
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

		table_bookdetails = new JTable();
		table_bookdetails.setOpaque(false);
		table_bookdetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int rowNo = table_bookdetails.getSelectedRow();
				TableModel model = table_bookdetails.getModel();

				textField_bookıd.setText(model.getValueAt(rowNo, 0).toString());
				textField_bookname.setText(model.getValueAt(rowNo, 1).toString());
				textField_authorname.setText(model.getValueAt(rowNo, 2).toString());
				textField_quantity.setText(model.getValueAt(rowNo, 3).toString());

			}
		});
		table_bookdetails.setRowHeight(30);
		table_bookdetails.setGridColor(new Color(0, 0, 0));
		table_bookdetails.setForeground(new Color(0, 0, 0));
		table_bookdetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		table_bookdetails.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_bookdetails.setBackground(new Color(238, 240, 242));
		table_bookdetails.setSelectionBackground(new Color(218, 221, 216));
		table_bookdetails.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Book ID", "Name", "Author", "Quantity" }));
		table_bookdetails.setToolTipText("");
		table_bookdetails.setName("Student Details");
		table_bookdetails.setBounds(121, 254, 705, 277);
		panel_2.add(table_bookdetails);

		JLabel lblNewLabel_3 = new JLabel("Manage Books");
		lblNewLabel_3.setIcon(new ImageIcon(ManageBooks.class.getResource("/MyNewIcoıns/icons8-book-100.png")));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblNewLabel_3.setBackground(new Color(0, 0, 0));
		lblNewLabel_3.setBounds(254, 49, 388, 94);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel(
				"Book ID                   Book Name            Author                   Quantity       ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_2.setBackground(new Color(102, 205, 170));
		lblNewLabel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel_2.setBounds(121, 231, 705, 23);
		panel_2.add(lblNewLabel_2);

		setBookDetailsToTable();
	}

}
