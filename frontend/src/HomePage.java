import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		JLabel Logo = new JLabel("");
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Image image = new ImageIcon(this.getClass().getResource("/Ordering system Logo.png")).getImage();
		Logo.setIcon(new ImageIcon(image));
		Logo.setBounds(157, 10, 472, 432);
		contentPane.add(Logo); 
		*/
		
		JButton OrderButton = new JButton("Order Food");
		OrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FoodMenu frame = new FoodMenu();	// To open FoodMenu page
				frame.setVisible(true);
				dispose(); // To remove Home page
			}
		});
		OrderButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		OrderButton.setBounds(335, 465, 115, 27);
		contentPane.add(OrderButton);
	}
}
