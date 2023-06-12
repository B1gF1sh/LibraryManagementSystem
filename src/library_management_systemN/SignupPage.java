package library_management_systemN;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class SignupPage extends JFrame {

	private JFrame frame;
	private JTextField SignupPage_txtfield_username;
	private JTextField SignupPage_txtfield_password;
	private JTextField SignupPage_txtfield_email;
	private JTextField SignupPage_txtfield_contact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// create database connector

					SignupPage window = new SignupPage();
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
	public SignupPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("EditorPane.inactiveBackground"));
		frame.setBounds(100, 100, 1200, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Panel SignupPage_Panel1 = new Panel();
		SignupPage_Panel1.setBackground(Color.WHITE);
		SignupPage_Panel1.setBounds(10, 10, 789, 702);
		frame.getContentPane().add(SignupPage_Panel1);
		SignupPage_Panel1.setLayout(null);

		JLabel SignupPage_label_ICON = new JLabel("New label");
		SignupPage_label_ICON.setIcon(new ImageIcon(SignupPage.class.getResource("/icons/signup-library-icon.png")));
		SignupPage_label_ICON.setBounds(23, 218, 743, 467);
		SignupPage_Panel1.add(SignupPage_label_ICON);
		
		JLabel TEDU_label_1 = new JLabel("TED University Library");
		TEDU_label_1.setForeground(new Color(159, 175, 144));
		TEDU_label_1.setFont(new Font("Helvetica Rounded", Font.PLAIN, 25));
		TEDU_label_1.setBounds(260, 98, 285, 52);
		SignupPage_Panel1.add(TEDU_label_1);
		
		JLabel TEDU_label = new JLabel("Welcome To ");
		TEDU_label.setForeground(Color.BLACK);
		TEDU_label.setFont(new Font("Helvetica Rounded", Font.PLAIN, 18));
		TEDU_label.setBounds(334, 74, 129, 28);
		SignupPage_Panel1.add(TEDU_label);

		JPanel SignupPage_Panel2 = new JPanel();
		SignupPage_Panel2.setBackground(new Color(214, 221, 208));
		SignupPage_Panel2.setBounds(799, 10, 395, 702);
		frame.getContentPane().add(SignupPage_Panel2);
		SignupPage_Panel2.setLayout(null);

		JLabel TEDU_label_2 = new JLabel("Sign Up Page");
		TEDU_label_2.setForeground(new Color(0, 0, 0));
		TEDU_label_2.setFont(new Font("Helvetica Rounded", Font.BOLD, 23));
		TEDU_label_2.setBounds(111, 41, 161, 33);
		SignupPage_Panel2.add(TEDU_label_2);

		JLabel TEDU_label_1_1 = new JLabel("Now let's create a new account.");
		TEDU_label_1_1.setForeground(new Color(0, 0, 0));
		TEDU_label_1_1.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 15));
		TEDU_label_1_1.setBounds(78, 74, 240, 28);
		SignupPage_Panel2.add(TEDU_label_1_1);

		JLabel SignupPage_label_username = new JLabel("Username");
		SignupPage_label_username.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_label_username.setBounds(78, 151, 161, 28);
		SignupPage_Panel2.add(SignupPage_label_username);

		SignupPage_txtfield_username = new JTextField();
		SignupPage_txtfield_username.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		SignupPage_txtfield_username.setOpaque(false);
		SignupPage_txtfield_username.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_txtfield_username.setCaretColor(new Color(0, 0, 0));
		SignupPage_txtfield_username.setBackground(new Color(255, 140, 0));
		SignupPage_txtfield_username.setDisabledTextColor(new Color(0, 0, 0));
		SignupPage_txtfield_username.setBounds(77, 178, 162, 33);
		SignupPage_Panel2.add(SignupPage_txtfield_username);
		SignupPage_txtfield_username.setColumns(10);

		JLabel SignupPage_label_password = new JLabel("Password");
		SignupPage_label_password.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_label_password.setBounds(79, 262, 161, 28);
		SignupPage_Panel2.add(SignupPage_label_password);

		SignupPage_txtfield_password = new JTextField();
		SignupPage_txtfield_password.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		SignupPage_txtfield_password.setOpaque(false);
		SignupPage_txtfield_password.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_txtfield_password.setDisabledTextColor(Color.BLACK);
		SignupPage_txtfield_password.setColumns(10);
		SignupPage_txtfield_password.setCaretColor(Color.BLACK);
		SignupPage_txtfield_password.setBackground(new Color(255, 140, 0));
		SignupPage_txtfield_password.setBounds(78, 289, 162, 33);
		SignupPage_Panel2.add(SignupPage_txtfield_password);

		JLabel SignupPage_label_email = new JLabel("Email");
		SignupPage_label_email.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_label_email.setBounds(78, 374, 161, 28);
		SignupPage_Panel2.add(SignupPage_label_email);

		SignupPage_txtfield_email = new JTextField();
		SignupPage_txtfield_email.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		SignupPage_txtfield_email.setOpaque(false);
		// Extra memory
		/*
		 * SignupPage_txtfield_email.addFocusListener(new FocusAdapter() {
		 * 
		 * @Override public void focusLost(FocusEvent e) {
		 * 
		 * if(checkduplicateuser() == true) {
		 * JOptionPane.showMessageDialog(SignupPage_label_email,
		 * "Email already exists!!!"); }
		 * 
		 * } });
		 */
		SignupPage_txtfield_email.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_txtfield_email.setDisabledTextColor(Color.BLACK);
		SignupPage_txtfield_email.setColumns(10);
		SignupPage_txtfield_email.setCaretColor(Color.BLACK);
		SignupPage_txtfield_email.setBackground(new Color(255, 140, 0));
		SignupPage_txtfield_email.setBounds(77, 401, 162, 33);
		SignupPage_Panel2.add(SignupPage_txtfield_email);

		JLabel SignupPage_label_contact = new JLabel("Contact");
		SignupPage_label_contact.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_label_contact.setBounds(78, 478, 161, 28);
		SignupPage_Panel2.add(SignupPage_label_contact);

		SignupPage_txtfield_contact = new JTextField();
		SignupPage_txtfield_contact.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		SignupPage_txtfield_contact.setOpaque(false);
		SignupPage_txtfield_contact.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_txtfield_contact.setDisabledTextColor(Color.BLACK);
		SignupPage_txtfield_contact.setColumns(10);
		SignupPage_txtfield_contact.setCaretColor(Color.BLACK);
		SignupPage_txtfield_contact.setBackground(new Color(255, 140, 0));
		SignupPage_txtfield_contact.setBounds(77, 505, 162, 33);
		SignupPage_Panel2.add(SignupPage_txtfield_contact);

		JLabel SignupPage_Icon_username = new JLabel("");
		SignupPage_Icon_username.setIcon(new ImageIcon(SignupPage.class.getResource("/icons/Username.png")));
		SignupPage_Icon_username.setBounds(16, 167, 64, 59);
		SignupPage_Panel2.add(SignupPage_Icon_username);

		JLabel SignupPage_Icon_username_1 = new JLabel("");
		SignupPage_Icon_username_1.setIcon(new ImageIcon(SignupPage.class.getResource("/icons/Password.png")));
		SignupPage_Icon_username_1.setBounds(16, 278, 51, 44);
		SignupPage_Panel2.add(SignupPage_Icon_username_1);

		JLabel SignupPage_Icon_username_1_1 = new JLabel("");
		SignupPage_Icon_username_1_1.setIcon(new ImageIcon(SignupPage.class.getResource("/icons/Email.png")));
		SignupPage_Icon_username_1_1.setBounds(16, 390, 50, 44);
		SignupPage_Panel2.add(SignupPage_Icon_username_1_1);

		JLabel SignupPage_Icon_username_1_1_1 = new JLabel("");
		SignupPage_Icon_username_1_1_1.setIcon(new ImageIcon(SignupPage.class.getResource("/icons/Contact.png")));
		SignupPage_Icon_username_1_1_1.setBounds(16, 494, 50, 44);
		SignupPage_Panel2.add(SignupPage_Icon_username_1_1_1);

		JButton SignupPage_Button_Signup = new JButton("SignUp");
		SignupPage_Button_Signup.setBorder(null);
		SignupPage_Button_Signup.setBounds(78, 566, 192, 33);
		SignupPage_Panel2.add(SignupPage_Button_Signup);
		
		
		
		
		SignupPage_Button_Signup.addActionListener(new ActionListener() {

			// Signup button action listener !!!
			public void actionPerformed(ActionEvent e) {

				String name = SignupPage_txtfield_username.getText();
				String password = SignupPage_txtfield_password.getText();
				String email = SignupPage_txtfield_email.getText();
				String contact = SignupPage_txtfield_contact.getText();

				if (validateSignup() == true) {

					if (checkduplicateuser() == false) {

						insertSignupValues(name, password, email, contact);
						JOptionPane.showMessageDialog(SignupPage_Button_Signup, "Your ınformation saved");

					} else {
						JOptionPane.showMessageDialog(SignupPage_Button_Signup, "Email already exists!!!!");
					}

				}

			}
		});

		// Sign in button cant be balancing
		JButton SignupPage_Button_Signin = new JButton("Sign In");
		SignupPage_Button_Signin.setBorder(null);
		SignupPage_Button_Signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			LoginPage frame = new LoginPage();
			
			frame.setVisible(true);
			dispose();
			}
		});
		SignupPage_Button_Signin.setBounds(78, 611, 194, 33);
		SignupPage_Panel2.add(SignupPage_Button_Signin);

		// Signup page close button "X" -*- Kapatma butonu "X"
		JLabel TEDU_label_2_1 = new JLabel("X");
		TEDU_label_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		TEDU_label_2_1.setForeground(Color.BLACK);
		TEDU_label_2_1.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 23));
		TEDU_label_2_1.setBounds(363, 6, 32, 33);
		SignupPage_Panel2.add(TEDU_label_2_1);

	}

	public void insertSignupValues(String name, String password, String email, String contact) {

		DBconnection cnn = new DBconnection();

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			
			// Database Connection String

			String sql = "INSERT INTO USERS(name,password,email,contact) VALUES ('" + name + "', '" + password + "', '"
					+ email + "', '" + contact + "')";

			statement.executeUpdate(sql);

			connection.close();

		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}

	// Signup properties settings
	public boolean validateSignup() {

		String name = SignupPage_txtfield_username.getText();
		String password = SignupPage_txtfield_password.getText();
		String email = SignupPage_txtfield_email.getText();
		String contact = SignupPage_txtfield_contact.getText();

		if (name.equals("") || password.equals("") || email.equals("") || contact.equals(null)) {
			JOptionPane.showMessageDialog(SignupPage_txtfield_username, "Please fill all the blanks!!!");
			return false;

		}

		return true;
	}

	public boolean checkduplicateuser() {

		DBconnection cnn = new DBconnection();

		String email = SignupPage_txtfield_email.getText();

		boolean isExist = false;

		try (Connection connection = DriverManager.getConnection(cnn.DB_URL, cnn.USER, cnn.PASS);
				Statement statement = connection.createStatement();) {

			PreparedStatement preparestatement = connection.prepareStatement("SELECT *FROM USERS WHERE EMAİL = ?");

			preparestatement.setString(1, email);

			ResultSet resultset = preparestatement.executeQuery();

			if (resultset.next()) {
						
				isExist = true;
			} else {
				isExist = false;
			}
							
		} catch (Exception exception) {

			exception.printStackTrace();

		}
		return isExist;

	}

}
