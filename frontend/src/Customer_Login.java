import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Customer_Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameTextField;
	private JPasswordField PasswordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Login frame = new Customer_Login();
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
	public Customer_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LoginTitleLabel = new JLabel("Login Account");
		LoginTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LoginTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 45));
		LoginTitleLabel.setBounds(223, 56, 320, 53);
		contentPane.add(LoginTitleLabel);
		
		JLabel UsernameLabel = new JLabel("Username:");
		UsernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		UsernameLabel.setBounds(269, 167, 112, 13);
		contentPane.add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password:");
		PasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		PasswordLabel.setBounds(269, 219, 93, 13);
		contentPane.add(PasswordLabel);
		
		UsernameTextField = new JTextField();
		UsernameTextField.setBounds(269, 190, 187, 19);
		contentPane.add(UsernameTextField);
		UsernameTextField.setColumns(10);
		
		PasswordTextField = new JPasswordField();
		PasswordTextField.setBounds(269, 239, 187, 19);
		contentPane.add(PasswordTextField);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HomePage frame = new HomePage();	// To open Homepage page
				frame.setVisible(true);
				dispose();	// To close previous page
			}
		});
		
		LoginButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		LoginButton.setBounds(316, 327, 85, 21);
		contentPane.add(LoginButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox.setBounds(269, 285, 187, 21);
		contentPane.add(comboBox);
		comboBox.addItem("Customer");
		comboBox.addItem("Staff");
		
		JLabel lblAccountType = new JLabel("Account Type:");
		lblAccountType.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblAccountType.setBounds(269, 268, 112, 13);
		contentPane.add(lblAccountType);
		
	}
}
