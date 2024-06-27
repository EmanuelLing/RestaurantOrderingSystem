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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import foodorderingsystem.CallingAPI;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;


public class Payment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private CallingAPI api = new CallingAPI(); // Instantiate CallingAPI
    private String PaymentType;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment("","",new ArrayList<>(),"",0.0);
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
	public Payment(String previewOrder, String customerID,ArrayList<FoodMenu> orderItems,String orderID,double grandTotal) {
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
		
		JButton btnComfirmPayment = new JButton("Comfirm Payment");
		btnComfirmPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPayment(customerID,orderItems,PaymentType,orderID,grandTotal);
				HomePage homepage = new HomePage(customerID);
                homepage.setVisible(true);
                dispose(); // close login frame
			}
		});
		btnComfirmPayment.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnComfirmPayment.setBounds(77, 52, 163, 21);
		panel_1.add(btnComfirmPayment);
		
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
	
	private void addPayment(String customerID,ArrayList<FoodMenu> orderItems,String PaymentType,String orderID, double grandTotal)
	{
		try {
		String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String total = String.valueOf(grandTotal);
		 // Extract the number part from OrderID
	    String numberPart = orderID.replaceAll("\\D+", "");
	    
	    // Create PaymentID starting with 'P' and appending the extracted number
	    String paymentID = "P" + numberPart;
	    
	    System.out.println(paymentID);
	    System.out.println(total);
	    System.out.println(currentTime);
	    System.out.println(orderID);
	    System.out.println(PaymentType);
	    
		
		api.addPaymentAPI(paymentID, total, currentTime, PaymentType, orderID, null);
		
		JSONObject response = api.getJsonResponse();
        if (response != null && response.has("message") && response.getString("message").equals("payment successfully added")) {
            JOptionPane.showMessageDialog(null, "Payment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
            else {
            JOptionPane.showMessageDialog(null, "Failed to add payment.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
		
	}
}
