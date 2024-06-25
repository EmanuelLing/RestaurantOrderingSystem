import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Payment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField OrderStatusTextField;
	private static String previewOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment(previewOrder);
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
	public Payment(String previewOrder) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLACK, 2));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(113, 61, 318, 320);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea PreviewReceiptTextArea = new JTextArea();
		PreviewReceiptTextArea.setBounds(1, 0, 314, 320);
		panel.add(PreviewReceiptTextArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.BLACK, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(113, 380, 318, 125);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedOption = (String) comboBox.getSelectedItem();
		        if ("Cash".equals(selectedOption)) {
		        	OrderStatusTextField.setText("UnPaid");
		        }
			}
		});
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cash", "e-Wallet", "Online Banking"}));
		comboBox.setBounds(157, 44, 135, 21);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Payment Method:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 48, 111, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Order Status:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(39, 78, 98, 13);
		panel_1.add(lblNewLabel_1_1);
		
		OrderStatusTextField = new JTextField();
		OrderStatusTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(OrderStatusTextField.getText()== "Completed") 
				{
					JOptionPane.showMessageDialog(null," Your order is Completed");
				}else {
					JOptionPane.showMessageDialog(null," Your order is Canceled");
				}
				System.exit(0);
					
			}
		});
		OrderStatusTextField.setFont(new Font("Times New Roman", Font.BOLD, 14));
		OrderStatusTextField.setBounds(157, 75, 135, 19);
		panel_1.add(OrderStatusTextField);
		OrderStatusTextField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(113, 21, 318, 41);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment Gateway");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(59, 10, 194, 21);
		panel_2.add(lblNewLabel);
		
		PreviewReceiptTextArea.setText(previewOrder);
	}
}
