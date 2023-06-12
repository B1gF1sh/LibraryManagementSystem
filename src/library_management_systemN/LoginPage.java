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

public class LoginPage extends JFrame {

	private JFrame frame;
	private JTextField LoginPage_txtfield_username;
	private JTextField LoginPage_txtfield_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// create database connector

					LoginPage window = new LoginPage();
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
	public LoginPage() {
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

		JLabel TEDU_label = new JLabel("Welcome To ");
		TEDU_label.setForeground(new Color(0, 0, 0));
		TEDU_label.setFont(new Font("Helvetica Rounded", Font.PLAIN, 18));
		TEDU_label.setBounds(322, 88, 129, 28);
		SignupPage_Panel1.add(TEDU_label);

		JLabel TEDU_label_1 = new JLabel("TED University Library");
		TEDU_label_1.setForeground(new Color(159, 175, 144));
		TEDU_label_1.setFont(new Font("Helvetica Rounded", Font.PLAIN, 25));
		TEDU_label_1.setBounds(248, 112, 285, 52);
		SignupPage_Panel1.add(TEDU_label_1);

		JLabel LoginPage_label_ICON = new JLabel("");
		LoginPage_label_ICON.setIcon(new ImageIcon(LoginPage.class.getResource("/icons/library-3.png.png")));
		LoginPage_label_ICON.setBounds(23, 218, 743, 467);
		SignupPage_Panel1.add(LoginPage_label_ICON);

		JPanel SignupPage_Panel2 = new JPanel();
		SignupPage_Panel2.setBackground(new Color(214, 221, 208));
		SignupPage_Panel2.setBounds(799, 10, 395, 702);
		frame.getContentPane().add(SignupPage_Panel2);
		SignupPage_Panel2.setLayout(null);

		JLabel TEDU_label_2 = new JLabel("Login Page");
		TEDU_label_2.setForeground(new Color(0, 0, 0));
		TEDU_label_2.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 23));
		TEDU_label_2.setBounds(123, 35, 161, 33);
		SignupPage_Panel2.add(TEDU_label_2);

		JLabel TEDU_label_1_1 = new JLabel("Welcome again! Please log in to your account");
		TEDU_label_1_1.setForeground(new Color(0, 0, 0));
		TEDU_label_1_1.setFont(new Font("Helvetica", Font.PLAIN, 15));
		TEDU_label_1_1.setBounds(41, 78, 319, 28);
		SignupPage_Panel2.add(TEDU_label_1_1);

		JLabel SignupPage_label_username = new JLabel("Username");
		SignupPage_label_username.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_label_username.setBounds(110, 222, 161, 28);
		SignupPage_Panel2.add(SignupPage_label_username);

		LoginPage_txtfield_username = new JTextField();
		LoginPage_txtfield_username.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		LoginPage_txtfield_username.setOpaque(false);
		LoginPage_txtfield_username.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		LoginPage_txtfield_username.setCaretColor(new Color(0, 0, 0));
		LoginPage_txtfield_username.setBackground(new Color(255, 140, 0));
		LoginPage_txtfield_username.setDisabledTextColor(new Color(0, 0, 0));
		LoginPage_txtfield_username.setBounds(109, 249, 162, 33);
		SignupPage_Panel2.add(LoginPage_txtfield_username);
		LoginPage_txtfield_username.setColumns(10);

		JLabel SignupPage_label_password = new JLabel("Password");
		SignupPage_label_password.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		SignupPage_label_password.setBounds(111, 333, 161, 28);
		SignupPage_Panel2.add(SignupPage_label_password);

		LoginPage_txtfield_password = new JTextField();
		LoginPage_txtfield_password.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		LoginPage_txtfield_password.setOpaque(false);
		LoginPage_txtfield_password.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		LoginPage_txtfield_password.setDisabledTextColor(Color.BLACK);
		LoginPage_txtfield_password.setColumns(10);
		LoginPage_txtfield_password.setCaretColor(Color.BLACK);
		LoginPage_txtfield_password.setBackground(new Color(255, 140, 0));
		LoginPage_txtfield_password.setBounds(110, 360, 162, 33);
		SignupPage_Panel2.add(LoginPage_txtfield_password);

		JLabel SignupPage_Icon_username = new JLabel("");
		SignupPage_Icon_username.setIcon(new ImageIcon(LoginPage.class.getResource("/MyNewIcoıns/icons8-user-50.png")));
		SignupPage_Icon_username.setBounds(31, 249, 50, 50);
		SignupPage_Panel2.add(SignupPage_Icon_username);

		JLabel SignupPage_Icon_username_1 = new JLabel("");
		SignupPage_Icon_username_1.setIcon(new ImageIcon(LoginPage.class.getResource("/MyNewIcoıns/icons8-lock-50.png")));
		SignupPage_Icon_username_1.setBounds(31, 348, 51, 60);
		SignupPage_Panel2.add(SignupPage_Icon_username_1);

		JButton SignupPage_Button_Signup = new JButton("Sign Up");
		SignupPage_Button_Signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SignupPage window = new SignupPage();
				window.setVisible(true);
				dispose();
			}

		});
		SignupPage_Button_Signup.setBounds(80, 489, 192, 33);
		SignupPage_Panel2.add(SignupPage_Button_Signup);

		JButton SignupPage_Button_Signin = new JButton("Sign In");
		SignupPage_Button_Signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validateloginpage() == true) {

					login();

				}

			}
		});
		SignupPage_Button_Signin.setBounds(78, 444, 194, 33);
		SignupPage_Panel2.add(SignupPage_Button_Signin);

		// Login page close button "X" -*- Kapatma butonu "X"
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

	public boolean validateloginpage() {

		String name = LoginPage_txtfield_username.getText();
		String password = LoginPage_txtfield_password.getText();

		if (name.equals(null)) {
			JOptionPane.showMessageDialog(LoginPage_txtfield_username, "Please enter username!!!");
			return false;
		}
		if (password.equals(null)) {
			JOptionPane.showMessageDialog(LoginPage_txtfield_password, "Please enter password!!!");
			return false;
		}

		return true;

	}

	public void login() {

		String username = LoginPage_txtfield_username.getText();
		String password = LoginPage_txtfield_password.getText();

		DBconnection dbconnection = new DBconnection();

		try (Connection connection = DriverManager.getConnection(dbconnection.DB_URL, dbconnection.USER,
				dbconnection.PASS); Statement statement = connection.createStatement();) {

			PreparedStatement preparedstatement = connection
					.prepareStatement("SELECT *FROM USERS WHERE NAME = ? AND PASSWORD = ?");
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);

			ResultSet resultset = preparedstatement.executeQuery();

			if (resultset.next()) {

				JOptionPane.showMessageDialog(null, "Login succesfull");

				HomePage homepage = new HomePage();

				homepage.setVisible(true);

				// homepage.dispose();

				// SignupPage_Button_Signin.disable();

			} else {

				JOptionPane.showMessageDialog(null, "Incorrect username or password!!!");
			}

		} catch (Exception exception) {

			exception.printStackTrace();
		}

	}

}
